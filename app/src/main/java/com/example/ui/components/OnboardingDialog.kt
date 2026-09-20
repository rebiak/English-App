package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Sync
import androidx.compose.ui.platform.LocalContext
import android.app.TimePickerDialog
import java.util.Calendar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.Strings
import com.example.ui.util.formatTime12h
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun OnboardingDialog(
    initialMinutes: Int = 20,
    initialCards: Int = 15,
    initialReminderEnabled: Boolean = true,
    initialReminderHour: Int = 20,
    initialReminderMinute: Int = 0,
    initialScheduleDate: String = "",
    initialScheduleType: String = com.example.service.DailyReminderManager.SCHEDULE_TYPE_EVERYDAY,
    isFirstOnboarding: Boolean = false,
    appLanguage: AppLanguage = AppLanguage.SPANISH,
    onDismiss: (() -> Unit)? = null,
    onComplete: (cards: Int, minutes: Int, reminderEnabled: Boolean, reminderHour: Int, reminderMinute: Int, scheduleDate: String, scheduleType: String) -> Unit
) {
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val context = androidx.compose.ui.platform.LocalContext.current

    // State for Session Practice Minutes
    var practiceMinutes by remember { mutableIntStateOf(if (initialMinutes > 0) initialMinutes else 20) }
    var isCustomMinutes by remember { mutableStateOf(practiceMinutes !in listOf(10, 20, 30, 45)) }

    // State for Daily Reminder Schedule
    val deviceCal = remember { Calendar.getInstance() }
    val startingHour = if (isFirstOnboarding && initialReminderHour == 20 && initialReminderMinute == 0) {
        deviceCal.get(Calendar.HOUR_OF_DAY)
    } else initialReminderHour
    val startingMinute = if (isFirstOnboarding && initialReminderHour == 20 && initialReminderMinute == 0) {
        deviceCal.get(Calendar.MINUTE)
    } else initialReminderMinute

    var reminderEnabled by remember { mutableStateOf(initialReminderEnabled) }
    var reminderHour by remember { mutableIntStateOf(startingHour) }
    var reminderMinute by remember { mutableIntStateOf(startingMinute) }
    var scheduleDate by remember { mutableStateOf(initialScheduleDate) }
    var scheduleType by remember { mutableStateOf(initialScheduleType) }

    Dialog(
        onDismissRequest = {
            onDismiss?.invoke()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("onboarding_session_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                // Top Header Row with Dismiss Button on the Right
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryIndigo.copy(alpha = 0.15f),
                        modifier = Modifier.size(50.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "⏱️", fontSize = 26.sp)
                        }
                    }

                    if (onDismiss != null) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f))
                                .testTag("close_daily_goal_dialog")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = if (isSpanish) "Cerrar" else "Close",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isFirstOnboarding) {
                        if (isSpanish) "¡Configura tu Sesión de Estudio!" else "Set Up Your Study Session!"
                    } else {
                        Strings.get("daily_goal_settings_title", appLanguage)
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = if (isSpanish) {
                        "Programa cuántos minutos practicarás hoy y el recordatorio diario."
                    } else {
                        "Schedule how many minutes you'll practice today and your daily alert."
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = ElectricCyan,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // SECTION 1: PROGRAMAR TIEMPO DE LA SESIÓN
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = PrimaryIndigo,
                                modifier = Modifier.size(22.dp)
                            )
                            Column {
                                Text(
                                    text = if (isSpanish) "¿Cuánto tiempo vas a practicar?" else "Session Practice Duration",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (isSpanish) "El temporizador comenzará al iniciar la app" else "Timer will start as you practice",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Quick Presets: 10m, 20m, 30m, 45m
                        val presets = listOf(
                            Pair(10, if (isSpanish) "10 min • Rápido" else "10 min • Quick"),
                            Pair(20, if (isSpanish) "20 min • Ideal ⭐" else "20 min • Ideal ⭐"),
                            Pair(30, if (isSpanish) "30 min • Enfocado" else "30 min • Focused"),
                            Pair(45, if (isSpanish) "45 min • Intensivo" else "45 min • Intensive")
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            presets.chunked(2).forEach { rowPresets ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    rowPresets.forEach { (mins, title) ->
                                        val isSelected = !isCustomMinutes && practiceMinutes == mins
                                        Surface(
                                            shape = RoundedCornerShape(14.dp),
                                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surface,
                                            border = BorderStroke(
                                                1.5.dp,
                                                if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                            ),
                                            modifier = Modifier
                                                .weight(1f)
                                                .clip(RoundedCornerShape(14.dp))
                                                .clickable {
                                                    practiceMinutes = mins
                                                    isCustomMinutes = false
                                                }
                                                .testTag("preset_minutes_${mins}")
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = title,
                                                    fontSize = 12.sp,
                                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Custom minutes toggle option
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isCustomMinutes) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
                            border = BorderStroke(
                                1.dp,
                                if (isCustomMinutes) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable {
                                    isCustomMinutes = !isCustomMinutes
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Tune,
                                        contentDescription = null,
                                        tint = if (isCustomMinutes) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = Strings.get("custom_goal_option", appLanguage),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isCustomMinutes) PrimaryIndigo else MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                Text(
                                    text = "$practiceMinutes min",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = PrimaryIndigo
                                )
                            }
                        }

                        // Custom Slider and Fine-tune Controls
                        AnimatedVisibility(
                            visible = isCustomMinutes,
                            enter = expandVertically() + fadeIn(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "1 min",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "⏱️ $practiceMinutes ${if (isSpanish) "minutos" else "minutes"}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = PrimaryIndigo
                                    )
                                    Text(
                                        text = "120 min",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Slider(
                                    value = practiceMinutes.toFloat(),
                                    onValueChange = { practiceMinutes = it.toInt().coerceIn(1, 120) },
                                    valueRange = 1f..120f,
                                    colors = SliderDefaults.colors(
                                        thumbColor = PrimaryIndigo,
                                        activeTrackColor = PrimaryIndigo
                                    ),
                                    modifier = Modifier.testTag("custom_minutes_slider")
                                )

                                // Direct Adjustment Steppers
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    listOf(-5, -1, 1, 5).forEach { step ->
                                        val isPositive = step > 0
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = if (isPositive) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clip(RoundedCornerShape(10.dp))
                                                .clickable {
                                                    practiceMinutes = (practiceMinutes + step).coerceIn(1, 180)
                                                }
                                                .padding(vertical = 8.dp)
                                        ) {
                                            Text(
                                                text = if (step > 0) "+${step}m" else "${step}m",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isPositive) PrimaryIndigo else MaterialTheme.colorScheme.onSurface,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Info banner
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = EmeraldGreen.copy(alpha = 0.12f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(text = "💡", fontSize = 14.sp)
                                Text(
                                    text = if (isSpanish) {
                                        "Al terminar los $practiceMinutes min, te avisaremos para que decidas si quieres seguir o descansar."
                                    } else {
                                        "When the $practiceMinutes min timer finishes, an alert will ask if you want to keep practicing."
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // SECTION 2: RECORDATORIO DIARIO Y HORARIO
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .padding(16.dp)
                    ) {
                        // Title & Switch Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.NotificationsActive,
                                    contentDescription = null,
                                    tint = PrimaryIndigo,
                                    modifier = Modifier.size(22.dp)
                                )
                                Column {
                                    Text(
                                        text = Strings.get("daily_reminder_title", appLanguage),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = Strings.get("daily_reminder_subtitle", appLanguage),
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Switch(
                                checked = reminderEnabled,
                                onCheckedChange = { reminderEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PrimaryIndigo
                                ),
                                modifier = Modifier.testTag("reminder_toggle_switch")
                            )
                        }

                        AnimatedVisibility(
                            visible = reminderEnabled,
                            enter = expandVertically() + fadeIn(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 14.dp)
                            ) {
                                // Section: Schedule & Date Selection ("Ajustes de fecha o programar")
                                Text(
                                    text = if (isSpanish) "Frecuencia y Fecha de Alerta:" else "Alert Frequency & Date:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                ConfigurableSchedulePicker(
                                    scheduleType = scheduleType,
                                    scheduleDate = scheduleDate,
                                    isSpanish = isSpanish,
                                    onScheduleTypeChange = { scheduleType = it },
                                    onScheduleDateChange = { scheduleDate = it }
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = Strings.get("reminder_time_picker", appLanguage),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Configurable Interactive Clock Widget
                                ConfigurableTimePicker(
                                    reminderHour = reminderHour,
                                    reminderMinute = reminderMinute,
                                    isSpanish = isSpanish,
                                    appLanguage = appLanguage,
                                    onTimeChange = { newHour, newMinute ->
                                        reminderHour = newHour
                                        reminderMinute = newMinute
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                val timeFormatted = formatTime12h(reminderHour, reminderMinute)
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = StarAmber.copy(alpha = 0.12f),
                                    border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.25f)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Alarm,
                                            contentDescription = null,
                                            tint = StarAmber,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Text(
                                            text = when (scheduleType) {
                                                com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE -> {
                                                    val dateLabel = formatDisplayScheduleDate(scheduleDate, isSpanish)
                                                    if (isSpanish) "Te notificaremos con alerta fuerte el $dateLabel a las $timeFormatted."
                                                    else "We will notify you with high-priority alert on $dateLabel at $timeFormatted."
                                                }
                                                com.example.service.DailyReminderManager.SCHEDULE_TYPE_WEEKDAYS -> {
                                                    if (isSpanish) "Te notificaremos con alerta fuerte de lunes a viernes a las $timeFormatted."
                                                    else "We will notify you with high-priority alert Monday to Friday at $timeFormatted."
                                                }
                                                else -> {
                                                    if (isSpanish) "Te notificaremos con alerta fuerte a las $timeFormatted todos los días."
                                                    else "We will notify you with high-priority alert at $timeFormatted every day."
                                                }
                                            },
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                // Botón para probar la notificación fuerte
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = PrimaryIndigo.copy(alpha = 0.1f),
                                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable {
                                            com.example.service.DailyReminderManager.showReminderNotification(context, isTest = true)
                                            try {
                                                android.widget.Toast.makeText(
                                                    context,
                                                    if (isSpanish) "🔔 ¡Notificación de prueba enviada con sonido y vibración fuerte!" else "🔔 Test loud notification sent!",
                                                    android.widget.Toast.LENGTH_SHORT
                                                ).show()
                                            } catch (_: Exception) {}
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.NotificationsActive,
                                            contentDescription = null,
                                            tint = PrimaryIndigo,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = if (isSpanish) "Probar Notificación Fuerte Ahora 🔊" else "Test Loud Alert Now 🔊",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryIndigo
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Confirm Button
                Button(
                    onClick = {
                        onComplete(
                            initialCards,
                            practiceMinutes,
                            reminderEnabled,
                            reminderHour,
                            reminderMinute,
                            scheduleDate,
                            scheduleType
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("onboarding_finish_button")
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isFirstOnboarding) {
                            if (isSpanish) "¡Comenzar Sesión de $practiceMinutes min!" else "Start $practiceMinutes min Session!"
                        } else {
                            if (isSpanish) "Guardar y Comenzar ($practiceMinutes min)" else "Save & Start ($practiceMinutes min)"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
}

@Composable
fun ConfigurableTimePicker(
    reminderHour: Int,
    reminderMinute: Int,
    isSpanish: Boolean,
    appLanguage: AppLanguage,
    onTimeChange: (hour: Int, minute: Int) -> Unit
) {
    val context = LocalContext.current
    val isAm = reminderHour < 12
    val hour12 = if (reminderHour % 12 == 0) 12 else reminderHour % 12
    val periodStr = if (isAm) "AM" else "PM"
    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hour12, reminderMinute)

    // Sincronización instantánea con la hora exacta del celular
    val syncWithPhoneTime: () -> Unit = {
        val cal = Calendar.getInstance()
        val currentHour = cal.get(Calendar.HOUR_OF_DAY)
        val currentMinute = cal.get(Calendar.MINUTE)
        onTimeChange(currentHour, currentMinute)
    }

    // Abrir diálogo de reloj nativo de Android
    val openSystemTimePicker: () -> Unit = {
        try {
            val timePicker = TimePickerDialog(
                context,
                { _, selectedHour, selectedMinute ->
                    onTimeChange(selectedHour, selectedMinute)
                },
                reminderHour,
                reminderMinute,
                false
            )
            timePicker.show()
        } catch (_: Exception) {}
    }

    // Quick presets
    val presets = listOf(
        Triple(8, 0, if (isSpanish) "🌅 8 AM" else "🌅 8 AM"),
        Triple(13, 0, if (isSpanish) "☀️ 1 PM" else "☀️ 1 PM"),
        Triple(18, 0, if (isSpanish) "🌆 6 PM" else "🌆 6 PM"),
        Triple(21, 0, if (isSpanish) "🌙 9 PM" else "🌙 9 PM")
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Phone Clock Sync & Native System Clock Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Sincronizar con hora del celular
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = StarAmber.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.5f)),
                modifier = Modifier
                    .weight(1.3f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { syncWithPhoneTime() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = null,
                        tint = StarAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = if (isSpanish) "Hora actual del celular" else "Sync with phone time",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                }
            }

            // Reloj nativo de Android
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = PrimaryIndigo.copy(alpha = 0.12f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                modifier = Modifier
                    .weight(0.9f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { openSystemTimePicker() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = if (isSpanish) "Reloj Android" else "System Clock",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo,
                        maxLines = 1
                    )
                }
            }
        }

        // Quick Presets Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            presets.forEach { (h, m, label) ->
                val isSelected = reminderHour == h && reminderMinute == m
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(
                        1.dp,
                        if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onTimeChange(h, m) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp)
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        // Main Interactive Clock Box with AM / PM Switcher
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Top Row: Big Digital Display + AM / PM Switcher
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Digital Time Display
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isAm) StarAmber.copy(alpha = 0.15f) else PrimaryIndigo.copy(alpha = 0.15f),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isAm) Icons.Default.WbSunny else Icons.Default.NightsStay,
                                    contentDescription = null,
                                    tint = if (isAm) StarAmber else PrimaryIndigo,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = formattedTime,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Black,
                                    color = PrimaryIndigo,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = periodStr,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isAm) StarAmber else PrimaryIndigo,
                                    modifier = Modifier.padding(bottom = 3.dp)
                                )
                            }
                            Text(
                                text = when {
                                    reminderHour in 5..11 -> if (isSpanish) "☀️ Mañana" else "☀️ Morning"
                                    reminderHour in 12..13 -> if (isSpanish) "☀️ Mediodía" else "☀️ Midday"
                                    reminderHour in 14..19 -> if (isSpanish) "🌆 Tarde" else "🌆 Afternoon"
                                    else -> if (isSpanish) "🌙 Noche" else "🌙 Night"
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // AM / PM Segmented Selector (Equal width, guaranteed visible)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(3.dp),
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // AM Button
                            Surface(
                                shape = RoundedCornerShape(9.dp),
                                color = if (isAm) PrimaryIndigo else Color.Transparent,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9.dp))
                                    .clickable {
                                        if (!isAm) {
                                            val newHour = (reminderHour - 12).coerceIn(0, 11)
                                            onTimeChange(newHour, reminderMinute)
                                        }
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                                ) {
                                    Text(
                                        text = "AM",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (isAm) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            // PM Button
                            Surface(
                                shape = RoundedCornerShape(9.dp),
                                color = if (!isAm) PrimaryIndigo else Color.Transparent,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9.dp))
                                    .clickable {
                                        if (isAm) {
                                            val newHour = (reminderHour + 12).coerceIn(12, 23)
                                            onTimeChange(newHour, reminderMinute)
                                        }
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                                ) {
                                    Text(
                                        text = "PM",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (!isAm) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                // Divider line for clean visual separation
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                    thickness = 1.dp
                )

                // Middle Section: Direct 1-12 Hour Selector
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = if (isSpanish) "Seleccionar hora (1 - 12):" else "Select hour (1 - 12):",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // 12-Hour Buttons Grid (6 per row, perfectly weighted)
                    val row1 = (1..6).toList()
                    val row2 = (7..12).toList()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        row1.forEach { h ->
                            val isSelectedHour = hour12 == h
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelectedHour) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSelectedHour) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        val new24Hour = if (isAm) {
                                            if (h == 12) 0 else h
                                        } else {
                                            if (h == 12) 12 else h + 12
                                        }
                                        onTimeChange(new24Hour, reminderMinute)
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 7.dp)
                                ) {
                                    Text(
                                        text = "$h",
                                        fontSize = 13.sp,
                                        fontWeight = if (isSelectedHour) FontWeight.ExtraBold else FontWeight.Medium,
                                        color = if (isSelectedHour) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        row2.forEach { h ->
                            val isSelectedHour = hour12 == h
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelectedHour) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSelectedHour) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        val new24Hour = if (isAm) {
                                            if (h == 12) 0 else h
                                        } else {
                                            if (h == 12) 12 else h + 12
                                        }
                                        onTimeChange(new24Hour, reminderMinute)
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 7.dp)
                                ) {
                                    Text(
                                        text = "$h",
                                        fontSize = 13.sp,
                                        fontWeight = if (isSelectedHour) FontWeight.ExtraBold else FontWeight.Medium,
                                        color = if (isSelectedHour) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }

                // Divider line
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                    thickness = 1.dp
                )

                // Bottom Section Row 1: Minute Selector Chips (:00, :15, :30, :45)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = if (isSpanish) "Minutos:" else "Minutes:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(0, 15, 30, 45).forEach { m ->
                            val isSelected = reminderMinute == m
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onTimeChange(reminderHour, m) }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 7.dp)
                                ) {
                                    Text(
                                        text = String.format(Locale.getDefault(), ":%02d", m),
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }

                // Bottom Section Row 2: Fine Step Adjusters (-1h, +1h, -5m, +5m)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // -1h Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onTimeChange((reminderHour - 1 + 24) % 24, reminderMinute)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 6.dp)) {
                            Text(text = "-1h", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    // +1h Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onTimeChange((reminderHour + 1) % 24, reminderMinute)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 6.dp)) {
                            Text(text = "+1h", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    // -5m Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onTimeChange(reminderHour, (reminderMinute - 5 + 60) % 60)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 6.dp)) {
                            Text(text = "-5m", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryIndigo)
                        }
                    }

                    // +5m Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onTimeChange(reminderHour, (reminderMinute + 5) % 60)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 6.dp)) {
                            Text(text = "+5m", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryIndigo)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfigurableSchedulePicker(
    scheduleType: String,
    scheduleDate: String,
    isSpanish: Boolean,
    onScheduleTypeChange: (String) -> Unit,
    onScheduleDateChange: (String) -> Unit
) {
    val context = LocalContext.current

    val openNativeDatePicker: () -> Unit = {
        try {
            val cal = Calendar.getInstance()
            if (scheduleDate.isNotBlank()) {
                val parts = scheduleDate.split("-")
                if (parts.size == 3) {
                    cal.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
                }
            }
            val datePicker = android.app.DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val formatted = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    onScheduleDateChange(formatted)
                    onScheduleTypeChange(com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
            datePicker.show()
        } catch (_: Exception) {}
    }

    val todayStr = remember {
        val c = Calendar.getInstance()
        String.format(Locale.US, "%04d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
    }
    val tomorrowStr = remember {
        val c = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
        String.format(Locale.US, "%04d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
    }
    val inThreeDaysStr = remember {
        val c = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 3) }
        String.format(Locale.US, "%04d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
    }

    val displayDateFormatted = remember(scheduleDate, isSpanish) {
        formatDisplayScheduleDate(scheduleDate, isSpanish)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Frequency Selector Tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val options = listOf(
                Pair(com.example.service.DailyReminderManager.SCHEDULE_TYPE_EVERYDAY, if (isSpanish) "🔁 Diario" else "🔁 Daily"),
                Pair(com.example.service.DailyReminderManager.SCHEDULE_TYPE_WEEKDAYS, if (isSpanish) "💼 Lun-Vie" else "💼 Weekdays"),
                Pair(com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE, if (isSpanish) "📅 Fecha Fija" else "📅 Date")
            )

            options.forEach { (typeKey, label) ->
                val isSelected = scheduleType == typeKey
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onScheduleTypeChange(typeKey)
                            if (typeKey == com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE && scheduleDate.isBlank()) {
                                onScheduleDateChange(todayStr)
                            }
                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp)
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        // When Specific Date is selected (or quick date selection)
        if (scheduleType == com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isSpanish) "Fecha de la alerta:" else "Alert Date:",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = displayDateFormatted,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo
                            )
                        }

                        // Button to open Native DatePicker
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = PrimaryIndigo.copy(alpha = 0.12f),
                            border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { openNativeDatePicker() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    contentDescription = null,
                                    tint = PrimaryIndigo,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = if (isSpanish) "Calendario 📅" else "Calendar 📅",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                            }
                        }
                    }

                    // Quick Date Chips: Hoy, Mañana, En 3 días
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val chips = listOf(
                            Triple(todayStr, if (isSpanish) "Hoy" else "Today", "⚡"),
                            Triple(tomorrowStr, if (isSpanish) "Mañana" else "Tomorrow", "🌅"),
                            Triple(inThreeDaysStr, if (isSpanish) "+3 días" else "+3 days", "🗓️")
                        )

                        chips.forEach { (dateVal, name, iconEmoji) ->
                            val isChipSelected = scheduleDate == dateVal
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isChipSelected) StarAmber.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                border = BorderStroke(
                                    1.dp,
                                    if (isChipSelected) StarAmber else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        onScheduleDateChange(dateVal)
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "$iconEmoji $name",
                                        fontSize = 11.sp,
                                        fontWeight = if (isChipSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isChipSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun formatDisplayScheduleDate(dateStr: String, isSpanish: Boolean): String {
    if (dateStr.isBlank()) return if (isSpanish) "Hoy" else "Today"
    return try {
        val parts = dateStr.split("-")
        if (parts.size == 3) {
            val year = parts[0].toInt()
            val month = parts[1].toInt() - 1
            val day = parts[2].toInt()
            val cal = Calendar.getInstance().apply { set(year, month, day) }
            val sdf = SimpleDateFormat("EEEE, d 'de' MMMM", if (isSpanish) Locale("es", "ES") else Locale.ENGLISH)
            val res = sdf.format(cal.time)
            res.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        } else {
            dateStr
        }
    } catch (_: Exception) {
        dateStr
    }
}

