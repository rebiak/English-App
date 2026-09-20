package com.example.service

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import java.util.Calendar

class ReminderBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action

        when (action) {
            DailyReminderManager.ACTION_SNOOZE_10_MIN -> {
                // Cancel notification
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                notificationManager?.cancel(DailyReminderManager.NOTIFICATION_ID)

                // Schedule snooze for 10 minutes later
                DailyReminderManager.scheduleSnooze(context, 10)
                try {
                    Toast.makeText(context, "⏰ Recordatorio pospuesto por 10 minutos", Toast.LENGTH_SHORT).show()
                } catch (_: Exception) {}
            }
            Intent.ACTION_BOOT_COMPLETED,
            "android.intent.action.LOCKED_BOOT_COMPLETED",
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED -> {
                // Reschedule saved reminder on system restart or time change
                DailyReminderManager.rescheduleFromPreferences(context)
            }
            else -> {
                // Standard Daily Alarm triggered
                DailyReminderManager.showReminderNotification(context, isTest = false)

                // Reschedule for next day so the alarm runs continuously every day
                val (hour, minute, enabled) = DailyReminderManager.getSavedReminder(context)
                if (enabled) {
                    DailyReminderManager.scheduleDailyReminder(context, hour, minute, enabled = true)
                }
            }
        }
    }
}

object DailyReminderManager {
    const val CHANNEL_ID = "daily_study_reminder_channel_v2"
    const val NOTIFICATION_ID = 2001
    private const val ALARM_REQUEST_CODE = 3001
    private const val SNOOZE_REQUEST_CODE = 3002
    const val ACTION_SNOOZE_10_MIN = "com.example.ACTION_SNOOZE_10_MIN"
    const val EXTRA_NAV_TAB = "extra_nav_tab"

    private const val PREFS_NAME = "reminder_notification_prefs"
    private const val KEY_ENABLED = "reminder_enabled"
    private const val KEY_HOUR = "reminder_hour"
    private const val KEY_MINUTE = "reminder_minute"
    private const val KEY_SCHEDULE_DATE = "reminder_schedule_date"
    private const val KEY_SCHEDULE_TYPE = "reminder_schedule_type"

    const val SCHEDULE_TYPE_EVERYDAY = "EVERYDAY"
    const val SCHEDULE_TYPE_WEEKDAYS = "WEEKDAYS"
    const val SCHEDULE_TYPE_SPECIFIC_DATE = "SPECIFIC_DATE"

    // Strong vibration pattern: wait 0ms, vibrate 600ms, pause 200ms, vibrate 600ms, pause 200ms, vibrate 800ms
    val STRONG_VIBRATION_PATTERN = longArrayOf(0, 600, 200, 600, 200, 800)

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Recordatorio de Práctica Diaria (Fuerte)"
            val descriptionText = "Avisos de alta prioridad con sonido y vibración fuerte para tus sesiones de inglés"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                .build()

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableVibration(true)
                vibrationPattern = STRONG_VIBRATION_PATTERN
                enableLights(true)
                lightColor = Color.parseColor("#6366F1")
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                setBypassDnd(true)
                setSound(soundUri, audioAttributes)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun saveReminderPreferences(
        context: Context,
        hour: Int,
        minute: Int,
        enabled: Boolean,
        scheduleDate: String = "",
        scheduleType: String = SCHEDULE_TYPE_EVERYDAY
    ) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(KEY_ENABLED, enabled)
            .putInt(KEY_HOUR, hour)
            .putInt(KEY_MINUTE, minute)
            .putString(KEY_SCHEDULE_DATE, scheduleDate)
            .putString(KEY_SCHEDULE_TYPE, scheduleType)
            .apply()
    }

    fun getSavedReminder(context: Context): Triple<Int, Int, Boolean> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val enabled = prefs.getBoolean(KEY_ENABLED, true)
        val hour = prefs.getInt(KEY_HOUR, 20)
        val minute = prefs.getInt(KEY_MINUTE, 0)
        return Triple(hour, minute, enabled)
    }

    fun getSavedScheduleDate(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_SCHEDULE_DATE, "") ?: ""
    }

    fun getSavedScheduleType(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_SCHEDULE_TYPE, SCHEDULE_TYPE_EVERYDAY) ?: SCHEDULE_TYPE_EVERYDAY
    }

    fun rescheduleFromPreferences(context: Context) {
        val (hour, minute, enabled) = getSavedReminder(context)
        val scheduleDate = getSavedScheduleDate(context)
        val scheduleType = getSavedScheduleType(context)
        if (enabled) {
            scheduleDailyReminder(context, hour, minute, enabled = true, scheduleDate = scheduleDate, scheduleType = scheduleType)
        }
    }

    fun scheduleDailyReminder(
        context: Context,
        hour: Int,
        minute: Int,
        enabled: Boolean,
        scheduleDate: String = "",
        scheduleType: String = SCHEDULE_TYPE_EVERYDAY
    ) {
        saveReminderPreferences(context, hour, minute, enabled, scheduleDate, scheduleType)
        createNotificationChannel(context)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, ReminderBroadcastReceiver::class.java)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, flags)

        if (!enabled) {
            alarmManager.cancel(pendingIntent)
            return
        }

        // Calculate occurrence based on scheduleType
        val now = Calendar.getInstance()
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (scheduleType == SCHEDULE_TYPE_SPECIFIC_DATE && scheduleDate.isNotBlank()) {
                try {
                    val parts = scheduleDate.split("-")
                    if (parts.size == 3) {
                        val year = parts[0].toInt()
                        val month = parts[1].toInt() - 1
                        val day = parts[2].toInt()
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, day)
                    }
                } catch (_: Exception) {}
            } else if (scheduleType == SCHEDULE_TYPE_WEEKDAYS) {
                if (timeInMillis <= now.timeInMillis) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
                while (get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            } else {
                if (timeInMillis <= now.timeInMillis) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }
        }

        val triggerTime = calendar.timeInMillis

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // AlarmClockInfo gives the highest priority on Android, waking through Doze mode reliably
                val clockIntent = Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    putExtra(EXTRA_NAV_TAB, 2) // Open Swipe Feed
                }
                val clockPendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    clockIntent,
                    flags
                )
                val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerTime, clockPendingIntent)
                alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        } catch (e: Exception) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        triggerTime,
                        pendingIntent
                    )
                } else {
                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        triggerTime,
                        pendingIntent
                    )
                }
            } catch (_: Exception) {}
        }
    }

    fun scheduleSnooze(context: Context, minutes: Int = 10) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, ReminderBroadcastReceiver::class.java)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getBroadcast(context, SNOOZE_REQUEST_CODE, intent, flags)

        val triggerTime = System.currentTimeMillis() + (minutes * 60 * 1000L)

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            }
        } catch (_: Exception) {}
    }

    fun showReminderNotification(context: Context, isTest: Boolean = false) {
        createNotificationChannel(context)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Main action -> Open MainActivity directly to Practice / Swipe Feed
        val mainIntent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            putExtra(EXTRA_NAV_TAB, 2) // Open Swipe Feed
        }
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, flags)

        // Action 1: "🚀 ¡Practicar Ahora!"
        val practiceIntent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(EXTRA_NAV_TAB, 2)
        }
        val practicePendingIntent = PendingIntent.getActivity(context, 1, practiceIntent, flags)

        // Action 2: "⏰ Posponer 10 min"
        val snoozeIntent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
            action = ACTION_SNOOZE_10_MIN
        }
        val snoozePendingIntent = PendingIntent.getBroadcast(context, 2, snoozeIntent, flags)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val title = if (isTest) {
            "🔔 ¡Prueba de Notificación de Práctica!"
        } else {
            "⏰ ¡Momento de tu práctica en EnglishSwipe!"
        }

        val message = if (isTest) {
            "✅ La alerta funciona con sonido y vibración fuerte. ¡Todo listo para avisarte en tu horario programado!"
        } else {
            "🔥 Tu sesión de estudio está lista. Desliza tus tarjetas y mantén activa tu racha diaria de vocabulario."
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("$message\n\n🎯 Recuerda que 5 minutos al día hacen una gran diferencia.")
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setVibrate(STRONG_VIBRATION_PATTERN)
            .setSound(soundUri)
            .setLights(Color.parseColor("#6366F1"), 1000, 500)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(mainPendingIntent)
            .setFullScreenIntent(mainPendingIntent, false) // Heads-up display
            .addAction(
                android.R.drawable.ic_media_play,
                "🚀 Practicar Ahora",
                practicePendingIntent
            )
            .addAction(
                android.R.drawable.ic_popup_sync,
                "⏱️ Posponer 10 min",
                snoozePendingIntent
            )

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}
