package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ai.AiConfiguration
import com.example.ai.AiProvider
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiConfigCard(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false
) {
    val aiConfig by viewModel.aiConfiguration.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH
    var isExpanded by remember { mutableStateOf(initiallyExpanded) }

    var selectedProviderTab by remember(aiConfig.selectedProvider) {
        mutableStateOf(aiConfig.selectedProvider)
    }

    var apiKeyInput by remember(selectedProviderTab, aiConfig) {
        mutableStateOf(
            when (selectedProviderTab) {
                AiProvider.GOOGLE_AI -> aiConfig.googleApiKey
                AiProvider.OPENAI -> aiConfig.openAiApiKey
                AiProvider.DEEPSEEK -> aiConfig.deepSeekApiKey
            }
        )
    }

    var selectedModel by remember(selectedProviderTab, aiConfig) {
        mutableStateOf(
            when (selectedProviderTab) {
                AiProvider.GOOGLE_AI -> aiConfig.googleModel
                AiProvider.OPENAI -> aiConfig.openAiModel
                AiProvider.DEEPSEEK -> aiConfig.deepSeekModel
            }
        )
    }

    var isKeyVisible by remember { mutableStateOf(false) }
    var isTestingConnection by remember { mutableStateOf(false) }
    var testResultMessage by remember { mutableStateOf<String?>(null) }
    var isTestSuccess by remember { mutableStateOf<Boolean?>(null) }
    var saveSuccessFeedback by remember { mutableStateOf(false) }

    val clipboardManager = LocalClipboardManager.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(selectedProviderTab) {
        testResultMessage = null
        isTestSuccess = null
        saveSuccessFeedback = false
        isKeyVisible = false
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (aiConfig.isConfigured()) PrimaryIndigo.copy(alpha = 0.4f) else StarAmber.copy(alpha = 0.4f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .testTag("ai_config_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Clickable to expand/collapse
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { isExpanded = !isExpanded }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryIndigo.copy(alpha = 0.15f),
                        modifier = Modifier.size(38.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(text = aiConfig.selectedProvider.iconEmoji, fontSize = 18.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isSpanish) "Configurar IA:" else "Configure AI:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = aiConfig.selectedProvider.displayName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = PrimaryIndigo
                            )
                        }

                        val activeKey = aiConfig.getActiveApiKey()
                        val maskedKeyPreview = if (activeKey.isNotBlank()) {
                            "••••" + activeKey.takeLast(4)
                        } else {
                            if (isSpanish) "Sin clave (usando fallback)" else "No key (using fallback)"
                        }

                        Text(
                            text = "${if (isSpanish) "Modelo" else "Model"}: ${aiConfig.getActiveModel()} | $maskedKeyPreview",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (aiConfig.isConfigured()) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MasteredGreen.copy(alpha = 0.15f),
                            modifier = Modifier.padding(end = 6.dp)
                        ) {
                            Text(
                                text = if (isSpanish) "Activo" else "Active",
                                color = MasteredGreen,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (isExpanded) "Colapsar" else "Desplegar",
                        tint = PrimaryIndigo
                    )
                }
            }

            // Expanded AI Configurator Body
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    // Provider Switcher Tabs (Google AI / OpenAI / DeepSeek)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isSpanish) "Proveedores de IA:" else "AI Providers:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = if (isSpanish) "1 Activa a la vez" else "1 Active at a time",
                            fontSize = 11.sp,
                            color = PrimaryIndigo,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AiProvider.values().forEach { provider ->
                            val isTabSelected = selectedProviderTab == provider
                            val isCurrentlyActive = aiConfig.selectedProvider == provider

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isTabSelected) PrimaryIndigo else MaterialTheme.colorScheme.surface,
                                border = if (isCurrentlyActive && !isTabSelected) {
                                    androidx.compose.foundation.BorderStroke(1.5.dp, MasteredGreen)
                                } else null,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        selectedProviderTab = provider
                                    }
                                    .testTag("ai_provider_tab_${provider.id}")
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = provider.iconEmoji, fontSize = 18.sp)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = when (provider) {
                                            AiProvider.GOOGLE_AI -> "Google AI"
                                            AiProvider.OPENAI -> "OpenAI"
                                            AiProvider.DEEPSEEK -> "DeepSeek"
                                        },
                                        fontSize = 11.sp,
                                        fontWeight = if (isTabSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                        color = if (isTabSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    // Status Badge: ACTIVA vs Inactiva
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = if (isCurrentlyActive) {
                                            if (isTabSelected) Color.White.copy(alpha = 0.25f) else MasteredGreen.copy(alpha = 0.18f)
                                        } else {
                                            if (isTabSelected) Color.White.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ) {
                                        Text(
                                            text = if (isCurrentlyActive) {
                                                if (isSpanish) "🟢 Activa" else "🟢 Active"
                                            } else {
                                                if (isSpanish) "Inactiva" else "Inactive"
                                            },
                                            fontSize = 9.sp,
                                            fontWeight = if (isCurrentlyActive) FontWeight.ExtraBold else FontWeight.Normal,
                                            color = if (isCurrentlyActive) {
                                                if (isTabSelected) Color.White else MasteredGreen
                                            } else {
                                                if (isTabSelected) Color.White.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onSurfaceVariant
                                            },
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.5.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Notice explaining permanent persistence
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = PrimaryIndigo.copy(alpha = 0.08f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "💡", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isSpanish) {
                                    "La IA que guardes quedará configurada permanentemente al abrir la app. Las demás quedan inactivas."
                                } else {
                                    "The AI you save will stay permanently configured when launching the app. Others remain inactive."
                                },
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // If tab is not currently active, but already has a key saved, offer quick switch
                    val tabHasSavedKey = when (selectedProviderTab) {
                        AiProvider.GOOGLE_AI -> aiConfig.googleApiKey.isNotBlank()
                        AiProvider.OPENAI -> aiConfig.openAiApiKey.isNotBlank()
                        AiProvider.DEEPSEEK -> aiConfig.deepSeekApiKey.isNotBlank()
                    }
                    if (aiConfig.selectedProvider != selectedProviderTab && tabHasSavedKey) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MasteredGreen.copy(alpha = 0.12f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MasteredGreen.copy(alpha = 0.4f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    viewModel.setAiProvider(selectedProviderTab)
                                    saveSuccessFeedback = true
                                }
                                .padding(bottom = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isSpanish) "⚡ Esta IA ya tiene clave guardada" else "⚡ This AI already has a saved key",
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MasteredGreen
                                    )
                                    Text(
                                        text = if (isSpanish) "Toca para activarla como tu IA principal permanente." else "Tap to activate it as your permanent primary AI.",
                                        fontSize = 10.5.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MasteredGreen
                                ) {
                                    Text(
                                        text = if (isSpanish) "Activar" else "Activate",
                                        color = Color.White,
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    // API Key Input with Password Masking and Show/Hide toggle
                    Text(
                        text = "${if (isSpanish) "Clave de API" else "API Key"} (${selectedProviderTab.displayName}):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        value = apiKeyInput,
                        onValueChange = {
                            apiKeyInput = it
                            saveSuccessFeedback = false
                            testResultMessage = null
                        },
                        label = { Text("${if (isSpanish) "API Key de" else "API Key for"} ${selectedProviderTab.displayName}") },
                        placeholder = { Text(selectedProviderTab.keyPlaceholder) },
                        leadingIcon = {
                            Icon(Icons.Default.Key, contentDescription = null, tint = PrimaryIndigo)
                        },
                        trailingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Paste from clipboard button
                                IconButton(
                                    onClick = {
                                        val clipText = clipboardManager.getText()?.text
                                        if (!clipText.isNullOrBlank()) {
                                            apiKeyInput = clipText.trim()
                                            saveSuccessFeedback = false
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ContentPaste,
                                        contentDescription = if (isSpanish) "Pegar clave" else "Paste key",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                // Eye toggle to show/hide API key
                                IconButton(
                                    onClick = { isKeyVisible = !isKeyVisible }
                                ) {
                                    Icon(
                                        imageVector = if (isKeyVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = if (isKeyVisible) "Ocultar clave" else "Mostrar clave",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        },
                        visualTransformation = if (isKeyVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("ai_api_key_input")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Model Selection
                    var modelMenuExpanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = modelMenuExpanded,
                        onExpandedChange = { modelMenuExpanded = it },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedModel,
                            onValueChange = { selectedModel = it },
                            label = { Text(if (isSpanish) "Modelo Recomendado" else "Recommended Model") },
                            leadingIcon = {
                                Icon(Icons.Default.Tune, contentDescription = null, tint = ElectricCyan)
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = modelMenuExpanded)
                            },
                            readOnly = false,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                                .fillMaxWidth()
                                .testTag("ai_model_selector")
                        )

                        ExposedDropdownMenu(
                            expanded = modelMenuExpanded,
                            onDismissRequest = { modelMenuExpanded = false }
                        ) {
                            selectedProviderTab.availableModels.forEach { modelOption ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(modelOption, fontWeight = if (selectedModel == modelOption) FontWeight.Bold else FontWeight.Normal)
                                            if (modelOption == selectedProviderTab.defaultModel) {
                                                Text(
                                                    if (isSpanish) "Recomendado" else "Recommended",
                                                    fontSize = 11.sp,
                                                    color = PrimaryIndigo,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                    },
                                    onClick = {
                                        selectedModel = modelOption
                                        modelMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action Buttons: Save & Connect / Test Connection
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Test connection button
                        OutlinedButton(
                            onClick = {
                                focusManager.clearFocus()
                                isTestingConnection = true
                                testResultMessage = null
                                viewModel.testAiConnection(
                                    provider = selectedProviderTab,
                                    apiKey = apiKeyInput,
                                    model = selectedModel
                                ) { success, msg ->
                                    isTestingConnection = false
                                    isTestSuccess = success
                                    testResultMessage = msg
                                    if (success) {
                                        // Auto-save on successful test
                                        viewModel.saveAiConfig(selectedProviderTab, apiKeyInput, selectedModel)
                                        saveSuccessFeedback = true
                                    }
                                }
                            },
                            enabled = !isTestingConnection,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("test_ai_connection_button")
                        ) {
                            if (isTestingConnection) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp,
                                    color = PrimaryIndigo
                                )
                            } else {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isSpanish) "Probar" else "Test", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        // Save and connect button
                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                viewModel.saveAiConfig(selectedProviderTab, apiKeyInput, selectedModel)
                                saveSuccessFeedback = true
                                testResultMessage = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1.3f)
                                .height(46.dp)
                                .testTag("save_ai_connection_button")
                        ) {
                            Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isSpanish) "Guardar y Usar" else "Save & Use",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Test result / Save feedback banner
                    AnimatedVisibility(
                        visible = testResultMessage != null || saveSuccessFeedback,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            if (saveSuccessFeedback && testResultMessage == null) {
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = MasteredGreen.copy(alpha = 0.15f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = MasteredGreen,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (isSpanish) {
                                                "✅ Configuración de ${selectedProviderTab.displayName} guardada correctamente."
                                            } else {
                                                "✅ ${selectedProviderTab.displayName} configuration saved successfully."
                                            },
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MasteredGreen
                                        )
                                    }
                                }
                            }

                            testResultMessage?.let { msg ->
                                val success = isTestSuccess == true
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (success) MasteredGreen.copy(alpha = 0.15f) else PracticeCoral.copy(alpha = 0.15f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = if (success) Icons.Default.CheckCircle else Icons.Default.ErrorOutline,
                                            contentDescription = null,
                                            tint = if (success) MasteredGreen else PracticeCoral,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = msg,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (success) MasteredGreen else PracticeCoral
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
}
