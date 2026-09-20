package com.example.ui.screens

import androidx.activity.compose.BackHandler
import com.example.ui.components.AiConfigCard
import com.example.ui.components.DestinationFolderAndCategorySelector
import com.example.ui.components.StandardTopBar
import com.example.ui.components.getCategoryIcon
import com.example.ui.util.SpanishPhoneticUtil
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.platform.LocalClipboardManager
import com.example.ui.theme.MasteredGreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ai.LanguageDirection
import com.example.data.model.Flashcard
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.Strings
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateCardScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val tabs = if (appLanguage == AppLanguage.SPANISH) {
        listOf("✨ Generador IA", "➕ Manual", "📥 Importar Texto")
    } else {
        listOf("✨ AI Generator", "➕ Manual", "📥 Import Text")
    }

    // Return to first sub-tab if Create tab is reselected from bottom bar
    LaunchedEffect(Unit) {
        viewModel.tabReselectedEvents.collect { tabIndex ->
            if (tabIndex == 3 && selectedTab != 0) {
                selectedTab = 0
            }
        }
    }

    // If on a sub-tab, pressing Back returns to the first tab (AI Generator)
    BackHandler(enabled = selectedTab != 0) {
        selectedTab = 0
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Standard Top Bar and Mode Tabs (Tutorial Step 0: Modalidades de Creación y Barra Superior)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                    }
                }
        ) {
            StandardTopBar(
                viewModel = viewModel,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp, bottom = 6.dp)
            )

            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = PrimaryIndigo,
                edgePadding = 16.dp,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
        }

        when (selectedTab) {
            0 -> AiGeneratorView(viewModel)
            1 -> ManualAddCardView(viewModel)
            2 -> ImportVocabularyView(viewModel)
        }
    }
}

// -----------------------------------------------------------------------------------------
// ✨ AI GENERATOR WITH UNIFIED VOCABULARY & TOPIC MODES
// -----------------------------------------------------------------------------------------
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun AiGeneratorView(viewModel: MainViewModel) {
    var subTab by remember { mutableIntStateOf(0) }
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val subTabs = if (appLanguage == AppLanguage.SPANISH) {
        listOf("✨ Palabras o Lista", "💡 Por Tema")
    } else {
        listOf("✨ Word(s) or List", "💡 By Topic")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Sub-tabs for AI Generation Modes
        SecondaryTabRow(
            selectedTabIndex = subTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = PrimaryIndigo,
            divider = {}
        ) {
            subTabs.forEachIndexed { index, title ->
                Tab(
                    selected = subTab == index,
                    onClick = { subTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (subTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        when (subTab) {
            0 -> AiVocabularySubView(viewModel)
            1 -> AiTopicSubView(viewModel)
        }
    }
}

// --- UNIFIED SUB-VIEW: GENERATE SINGLE WORD, MULTIPLE WORDS, OR FULL LIST ---
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AiVocabularySubView(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    var inputWords by remember { mutableStateOf("") }
    var selectedDirection by remember { mutableStateOf(LanguageDirection.AUTO) }
    var selectedCategory by remember { mutableStateOf("Everyday & Social") }
    var selectedTargetCategory by remember { mutableStateOf<String?>(null) }
    var savedSuccess by remember { mutableStateOf(false) }

    val isGenerating by viewModel.isAiGenerating.collectAsStateWithLifecycle()
    val singleCardPreview by viewModel.singleCardPreview.collectAsStateWithLifecycle()
    val previewCards by viewModel.aiGeneratedPreview.collectAsStateWithLifecycle()
    val errorMessage by viewModel.aiErrorMessage.collectAsStateWithLifecycle()
    val allCategories by viewModel.categories.collectAsStateWithLifecycle()
    val suggestedWords by viewModel.suggestedWords.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val isSpanishLearning = learningMode == com.example.ui.util.LearningMode.EN_TO_ES

    val parsedWords = remember(inputWords) {
        inputWords
            .split(Regex("[,\n\r]+"))
            .map { it.trim().replace(Regex("""^(\d+[\.\)\-:]\s*|[\-\*•>]\s*)"""), "").trim() }
            .filter { it.isNotBlank() && !it.startsWith("//") && !it.startsWith("#") }
    }
    val wordCount = parsedWords.size

    LaunchedEffect(singleCardPreview, previewCards.size) {
        if (singleCardPreview != null || previewCards.isNotEmpty()) {
            scrollState.animateScrollTo(0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = if (isSpanish) "✨ Generar con IA (Una o Varias Palabras)" else "✨ AI Vocabulary Generator (Single or Multiple Words)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = if (isSpanish)
                if (isSpanishLearning)
                    "Escribe una palabra en español o inglés, o ingresa una lista. La IA generará tarjetas con fonética, definiciones claras y ejemplos sencillos con palabras básicas fáciles de entender."
                else
                    "Escribe una sola palabra/frase, o ingresa varias separadas por comas o líneas. La IA generará traducciones, fonética, definiciones claras y ejemplos sencillos con palabras básicas fáciles de entender."
            else
                if (isSpanishLearning)
                    "Type a word in Spanish or English, or enter a list. AI will generate cards with clear definitions and simple, easy-to-understand everyday examples."
                else
                    "Type a single word/phrase, or enter a list. AI will generate cards with clear definitions and simple, easy-to-understand everyday examples.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Language Direction Selector Chips (Tutorial Step 1: Modalidad y Dirección de Idioma)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(1, coords.boundsInRoot())
                    }
                }
        ) {
            Text(
                text = if (isSpanish) "Dirección de traducción:" else "Translation direction:",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LanguageDirection.values().forEach { direction ->
                    val isSelected = selectedDirection == direction
                    val label = when (direction) {
                        LanguageDirection.AUTO -> if (isSpanish) "Auto-detectar 🔄" else "Auto Detect 🔄"
                        LanguageDirection.EN_TO_ES -> if (isSpanish) "Inglés ➡️ Español" else "EN ➡️ ES"
                        LanguageDirection.ES_TO_EN -> if (isSpanish) "Español ➡️ Inglés" else "ES ➡️ EN"
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { selectedDirection = direction }
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Input Field & Quick Suggestion Chips (Tutorial Step 2: Entrada de Texto y Chips Rápidos)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                    }
                }
        ) {
            OutlinedTextField(
                value = inputWords,
                onValueChange = {
                    inputWords = it
                    savedSuccess = false
                },
                label = { 
                    Text(
                        if (wordCount > 1) 
                            (if (isSpanish) "Palabras a generar ($wordCount)" else "Words to generate ($wordCount)")
                        else 
                            (if (isSpanish) "Palabra, frase o lista" else "Word, phrase, or list")
                    ) 
                },
                placeholder = {
                    Text(
                        if (isSpanishLearning)
                            if (isSpanish) "ej. 'sobremesa' o varias palabras:\nmadrugar, dar en el clavo, ponerse las pilas"
                            else "e.g. 'sobremesa' or multiple words:\nmadrugar, dar en el clavo, ponerse las pilas"
                        else
                            if (isSpanish) "ej. 'resilience' o varias palabras:\nserendipity, bite the bullet, touch base"
                            else "e.g. 'resilience' or multiple words:\nserendipity, bite the bullet, touch base"
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Translate, contentDescription = null, tint = PrimaryIndigo)
                },
                trailingIcon = {
                    if (inputWords.isNotEmpty()) {
                        IconButton(onClick = {
                            inputWords = ""
                            viewModel.clearSingleWordPreview()
                            savedSuccess = false
                        }) {
                            Icon(Icons.Default.Close, contentDescription = if (isSpanish) "Limpiar" else "Clear")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp, max = 150.dp)
                    .testTag("ai_single_word_input"),
                shape = RoundedCornerShape(16.dp),
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Quick Suggestion Chips
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                suggestedWords.take(7).forEach { word ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.clickable {
                            inputWords = word
                            savedSuccess = false
                            viewModel.generateWordWithAi(word, selectedDirection)
                        }
                    ) {
                        Text(
                            text = word,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Generate Button (Tutorial Step 3: Generación Inteligente con IA)
        Button(
            onClick = {
                if (parsedWords.isNotEmpty() && !isGenerating) {
                    savedSuccess = false
                    if (wordCount == 1) {
                        viewModel.generateWordWithAi(parsedWords[0], selectedDirection)
                    } else {
                        viewModel.generateListWithAi(parsedWords, selectedDirection)
                    }
                }
            },
            enabled = !isGenerating && wordCount > 0,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("ai_single_word_generate_button")
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(3, coords.boundsInRoot())
                    }
                }
        ) {
            if (isGenerating) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (wordCount > 1) 
                        (if (isSpanish) "Generando $wordCount tarjetas con IA..." else "Generating $wordCount AI cards...")
                    else 
                        (if (isSpanish) "Generando con IA..." else "Generating with AI...")
                )
            } else {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when {
                        wordCount > 1 -> if (isSpanish) "Generar $wordCount Tarjetas con IA" else "Generate $wordCount AI Cards"
                        wordCount == 1 -> if (isSpanish) "Generar Tarjeta con IA" else "Generate Card with AI"
                        else -> if (isSpanish) "Generar con IA" else "Generate with AI"
                    },
                    fontWeight = FontWeight.Bold
                )
            }
        }

        errorMessage?.let { msg ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if (isSpanish) "Aviso: $msg" else "Notice: $msg", color = StarAmber, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 1. GENERATED SINGLE CARD PREVIEW
        singleCardPreview?.let { card ->
            val primaryTerm = if (isSpanishLearning) card.spanish else card.english
            val secondaryTerm = if (isSpanishLearning) card.english else card.spanish
            val primaryAudioText = if (isSpanishLearning) card.spanish else card.english
            val secondaryLabel = if (isSpanishLearning) "🇬🇧 English / Inglés:" else "🇪🇸 Spanish / Español:"

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("single_word_preview_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = PrimaryIndigo.copy(alpha = 0.15f),
                                modifier = Modifier.size(52.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(text = card.emoji, fontSize = 28.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = primaryTerm,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = PrimaryIndigo
                                )
                                val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
                                if (displayPhonetic.isNotBlank()) {
                                    Text(
                                        text = displayPhonetic,
                                        fontSize = 13.sp,
                                        color = ElectricCyan,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        IconButton(
                            onClick = { viewModel.playAudio(primaryAudioText) },
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(PrimaryIndigo.copy(alpha = 0.12f))
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = if (isSpanish) "Escuchar" else "Listen",
                                tint = PrimaryIndigo
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = secondaryLabel,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = secondaryTerm,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (card.definition.isNotBlank()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = if (isSpanish) "📖 Definición:" else "📖 Definition:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = card.definition,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    if (card.example.isNotBlank()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = PrimaryIndigo.copy(alpha = 0.08f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (isSpanish) "💬 Ejemplo:" else "💬 Example:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                                Text(
                                    text = "\"${card.example}\"",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (card.exampleTranslation.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "\"${card.exampleTranslation}\"",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    val currentCategory = selectedTargetCategory ?: card.category.ifBlank { "Everyday & Social" }

                    DestinationFolderAndCategorySelector(
                        viewModel = viewModel,
                        selectedCategory = currentCategory,
                        onCategorySelected = { selectedTargetCategory = it },
                        title = if (isSpanish) "📂 Carpeta & Lista de Destino:" else "📂 Target Folder & List:"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action: Add to Deck
                    Button(
                        onClick = {
                            viewModel.saveSingleGeneratedCard(currentCategory)
                            savedSuccess = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MasteredGreen),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            if (isSpanish) "Agregar a '$currentCategory'" else "Add to '$currentCategory'",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // 2. GENERATED MULTIPLE CARDS LIST PREVIEW
        if (previewCards.isNotEmpty()) {
            DestinationFolderAndCategorySelector(
                viewModel = viewModel,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                title = if (isSpanish) "📂 Carpeta & Lista de Destino para Baraja:" else "📂 Target Folder & List for Deck:"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isSpanish) "Tarjetas generadas (${previewCards.size})" else "Generated Cards (${previewCards.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Button(
                    onClick = { viewModel.saveAiGeneratedCards(selectedCategory) },
                    colors = ButtonDefaults.buttonColors(containerColor = MasteredGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("save_ai_list_button")
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (isSpanish) "Guardar (${previewCards.size})" else "Save (${previewCards.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                previewCards.forEach { card ->
                    val primaryTerm = if (isSpanishLearning) card.spanish else card.english
                    val secondaryTerm = if (isSpanishLearning) "🇬🇧 ${card.english}" else "🇪🇸 ${card.spanish}"
                    val primaryAudio = if (isSpanishLearning) card.spanish else card.english

                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = card.emoji, fontSize = 18.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = primaryTerm,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = PrimaryIndigo
                                    )
                                    val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
                                    if (displayPhonetic.isNotBlank()) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = displayPhonetic,
                                            fontSize = 11.sp,
                                            color = ElectricCyan
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = PrimaryIndigo.copy(alpha = 0.12f)
                                    ) {
                                        Text(
                                            text = card.type,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryIndigo,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = StarAmber.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = card.cefrLevel,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = StarAmber,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = secondaryTerm,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                )

                                if (card.definition.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "📖 ${card.definition}",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                if (card.example.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "💬 \"${card.example}\"",
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = PrimaryIndigo
                                    )
                                    if (card.exampleTranslation.isNotBlank()) {
                                        Text(
                                            text = "    \"${card.exampleTranslation}\"",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { viewModel.playAudio(primaryAudio) },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = if (isSpanish) "Escuchar" else "Listen",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { viewModel.removeCardFromPreview(card) },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = if (isSpanish) "Descartar" else "Discard",
                                        tint = PracticeCoral,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (savedSuccess) {
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MasteredGreen.copy(alpha = 0.15f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isSpanish) "🎉 ¡Tarjeta guardada exitosamente en tu baraja de estudio!" else "🎉 Card successfully saved to your study deck!",
                    color = MasteredGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ⚙️ Cuadro de Configuración y Conexión de IA
        AiConfigCard(viewModel = viewModel)

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// --- SUB-VIEW 2: TOPIC / PROMPT FREE-FORM GENERATOR ---
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AiTopicSubView(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val isSpanishLearning = learningMode == com.example.ui.util.LearningMode.EN_TO_ES

    var prompt by remember(isSpanishLearning) { 
        mutableStateOf(
            if (isSpanishLearning) 
                "Expresiones esenciales en español para conversación diaria"
            else 
                "Phrasal verbs y vocabulario esencial para conversación diaria"
        ) 
    }
    var cardCount by remember { mutableIntStateOf(10) }
    var selectedCategory by remember { mutableStateOf("Everyday & Social") }
    val isGenerating by viewModel.isAiGenerating.collectAsStateWithLifecycle()
    val previewCards by viewModel.aiGeneratedPreview.collectAsStateWithLifecycle()
    val errorMessage by viewModel.aiErrorMessage.collectAsStateWithLifecycle()
    val allCategories by viewModel.categories.collectAsStateWithLifecycle()

    val countOptions = listOf(5, 8, 10, 15, 20)

    val presets = remember(isSpanishLearning, isSpanish) {
        if (isSpanishLearning) {
            listOf(
                if (isSpanish) "✈️ Viajes y Aeropuerto" else "✈️ Spanish Travel & Airport",
                if (isSpanish) "💼 Trabajo y Negocios" else "💼 Spanish Business & Work",
                if (isSpanish) "⚡ Verbos y Conectores esenciales" else "⚡ Essential Spanish Verbs",
                if (isSpanish) "🤖 Tecnología e IA" else "🤖 Spanish Tech & AI Terms",
                if (isSpanish) "🍔 Restaurante y Comida" else "🍔 Spanish Food & Dining",
                if (isSpanish) "🎯 Modismos y Frases cotidianas" else "🎯 Spanish Idioms & Slang",
                if (isSpanish) "🏥 Salud y Emergencias" else "🏥 Health & Emergencies",
                if (isSpanish) "🗣️ Conversación y Saludos" else "🗣️ Daily Conversations"
            )
        } else {
            listOf(
                if (isSpanish) "✈️ Aeropuerto y Viajes" else "✈️ Airport & Travel",
                if (isSpanish) "💼 Entrevistas y Trabajo" else "💼 Job Interviews & Business",
                if (isSpanish) "⚡ Phrasal Verbs más comunes" else "⚡ Common Phrasal Verbs",
                if (isSpanish) "🤖 Términos de IA y Tecnología" else "🤖 AI & Tech Terms",
                if (isSpanish) "🍔 Frases en Restaurante" else "🍔 Useful Restaurant Phrases",
                if (isSpanish) "🎯 Modismos y Slang" else "🎯 Popular Idioms & Slang",
                if (isSpanish) "🏥 Salud y Vocabulario Médico" else "🏥 Health & Medical",
                if (isSpanish) "🗣️ Conversación Diaria" else "🗣️ Everyday Social Phrases"
            )
        }
    }

    LaunchedEffect(previewCards.size) {
        if (previewCards.isNotEmpty()) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = if (isSpanish) "💡 Generar por Tema o Prompt Libre" else "💡 Generate by Topic or Free Prompt",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = if (isSpanish)
                if (isSpanishLearning)
                    "Pídele a la Inteligencia Artificial cualquier tema de vocabulario en español para generar una baraja completa con traducciones y ejemplos."
                else
                    "Pídele a la Inteligencia Artificial cualquier tema de vocabulario en inglés para generar una baraja completa con traducciones y ejemplos."
            else
                if (isSpanishLearning)
                    "Ask AI for any Spanish vocabulary topic to generate full flashcards with English translations, phonetics, and examples."
                else
                    "Ask AI for any English vocabulary topic to generate full flashcards with translations, phonetics, and examples.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Preset Chips
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            presets.forEach { preset ->
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable {
                        prompt = preset.substring(preset.indexOf(" ") + 1)
                    }
                ) {
                    Text(
                        text = preset,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { 
                Text(
                    if (isSpanish)
                        if (isSpanishLearning) "¿Qué vocabulario en español quieres aprender?" else "¿Qué vocabulario en inglés quieres aprender?"
                    else
                        if (isSpanishLearning) "What Spanish vocabulary do you want to learn?" else "What English vocabulary do you want to learn?"
                ) 
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "ej. Frases sobre comida, viajes, modismos..." else "e.g. Food phrases, travel words, idioms..."
                    else
                        if (isSpanish) "ej. Vocabulario de viajes, tecnología, phrasal verbs..." else "e.g. Travel words, tech terms, phrasal verbs..."
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_prompt_input"),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Selector de cantidad de tarjetas
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (isSpanish) "🔢 Cantidad de tarjetas:" else "🔢 Number of cards:",
                fontSize = 12.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                countOptions.forEach { count ->
                    val isSelected = cardCount == count
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isSelected) BorderStroke(1.dp, ElectricCyan) else null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { cardCount = count }
                    ) {
                        Text(
                            text = "$count",
                            fontSize = 11.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (prompt.isNotBlank() && !isGenerating) {
                    viewModel.generateCardsWithAi(prompt, count = cardCount)
                }
            },
            enabled = !isGenerating && prompt.isNotBlank(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("ai_generate_button")
        ) {
            if (isGenerating) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isSpanish) "Generando con IA ($cardCount)..." else "Generating with AI ($cardCount)...")
            } else {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isSpanish) "Generar $cardCount Tarjetas con IA" else "Generate $cardCount Cards with AI",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        errorMessage?.let { msg ->
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = if (isSpanish) "Aviso: $msg" else "Notice: $msg", color = StarAmber, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (previewCards.isNotEmpty()) {
            DestinationFolderAndCategorySelector(
                viewModel = viewModel,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                title = if (isSpanish) "📂 Carpeta & Lista de Destino:" else "📂 Target Folder & List:"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isSpanish) "Tarjetas (${previewCards.size})" else "Cards (${previewCards.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Button(
                    onClick = { viewModel.saveAiGeneratedCards(selectedCategory) },
                    colors = ButtonDefaults.buttonColors(containerColor = MasteredGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("save_ai_cards_button")
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (isSpanish) "Guardar (${previewCards.size})" else "Save (${previewCards.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                previewCards.forEach { card ->
                    val primaryTerm = if (isSpanishLearning) card.spanish else card.english
                    val secondaryTerm = if (isSpanishLearning) "🇬🇧 ${card.english}" else "🇪🇸 ${card.spanish}"
                    val primaryAudio = if (isSpanishLearning) card.spanish else card.english

                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = card.emoji, fontSize = 18.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = primaryTerm,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = PrimaryIndigo
                                    )
                                    val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
                                    if (displayPhonetic.isNotBlank()) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = displayPhonetic,
                                            fontSize = 12.sp,
                                            color = ElectricCyan
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = PrimaryIndigo.copy(alpha = 0.12f)
                                    ) {
                                        Text(
                                            text = card.type,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryIndigo,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = StarAmber.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = card.cefrLevel,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = StarAmber,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = secondaryTerm,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                )

                                if (card.definition.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "📖 ${card.definition}",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                if (card.example.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "💬 \"${card.example}\"",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = PrimaryIndigo
                                    )
                                    if (card.exampleTranslation.isNotBlank()) {
                                        Text(
                                            text = "    \"${card.exampleTranslation}\"",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { viewModel.playAudio(primaryAudio) },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = if (isSpanish) "Escuchar" else "Listen",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { viewModel.removeCardFromPreview(card) },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = if (isSpanish) "Descartar" else "Discard",
                                        tint = PracticeCoral,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ⚙️ Cuadro de Configuración y Conexión de IA
        AiConfigCard(viewModel = viewModel)

        Spacer(modifier = Modifier.height(50.dp))
    }
}

// -----------------------------------------------------------------------------------------
// ➕ MANUAL ADD CARD VIEW
// -----------------------------------------------------------------------------------------
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ManualAddCardView(viewModel: MainViewModel) {
    var primaryInput by remember { mutableStateOf("") }
    var secondaryInput by remember { mutableStateOf("") }
    var phonetic by remember { mutableStateOf("") }
    var example by remember { mutableStateOf("") }
    var exampleTranslation by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Everyday & Social") }
    var selectedType by remember { mutableStateOf("Word") }
    var selectedEmoji by remember { mutableStateOf("💡") }

    var isSaved by remember { mutableStateOf(false) }
    var showAddCategoryDialog by remember { mutableStateOf(false) }
    var newCategoryText by remember { mutableStateOf("") }

    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val isSpanishLearning = learningMode == com.example.ui.util.LearningMode.EN_TO_ES

    val typeOptions = listOf(
        CardTypeItem("Word", if (isSpanish) "Palabra" else "Word", "📝"),
        CardTypeItem("Phrase", if (isSpanish) "Frase" else "Phrase", "💬"),
        CardTypeItem("Phrasal Verb", if (isSpanish) "Verbo Frasal / Repasar" else "Phrasal Verb", "🔄"),
        CardTypeItem("Expression", if (isSpanish) "Expresión" else "Expression", "💡"),
        CardTypeItem("Idiom", if (isSpanish) "Modismo / Idiom" else "Idiom", "🎭"),
        CardTypeItem("Slang", if (isSpanish) "Jerga / Slang" else "Slang", "😎"),
        CardTypeItem("Grammar", if (isSpanish) "Gramática" else "Grammar", "📖"),
        CardTypeItem("Sentence", if (isSpanish) "Oración" else "Sentence", "📄")
    )
    val emojis = listOf("💡", "⚡", "✈️", "💼", "🤖", "🍔", "🎯", "🧠", "🔥", "🌟", "📚", "❤️")

    if (showAddCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showAddCategoryDialog = false },
            title = { Text(if (isSpanish) "Nueva Categoría" else "New Category", fontWeight = FontWeight.Bold) },
            text = {
                OutlinedTextField(
                    value = newCategoryText,
                    onValueChange = { newCategoryText = it },
                    label = { Text(if (isSpanish) "Nombre de la categoría" else "Category Name") },
                    placeholder = { Text(if (isSpanish) "ej. Medicina, Slang, Gym..." else "e.g. Medicine, Slang, Gym...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newCategoryText.isNotBlank()) {
                            viewModel.addCategory(newCategoryText.trim())
                            selectedCategory = newCategoryText.trim()
                            newCategoryText = ""
                            showAddCategoryDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                ) {
                    Text(if (isSpanish) "Agregar" else "Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddCategoryDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = if (isSpanish) "➕ Crear Tarjeta Manual" else "➕ Create Card Manually",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(14.dp))

        // First Field: Target Learning Term (Spanish if learning Spanish, English if learning English)
        OutlinedTextField(
            value = primaryInput,
            onValueChange = { primaryInput = it; isSaved = false },
            label = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "Término en Español *" else "Spanish Term *"
                    else
                        if (isSpanish) "Término en Inglés *" else "English Term *"
                ) 
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "ej. sobremesa / madrugar" else "e.g. sobremesa / madrugar"
                    else
                        if (isSpanish) "ej. resilient" else "e.g. resilient"
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_input_english"),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Second Field: Translation
        OutlinedTextField(
            value = secondaryInput,
            onValueChange = { secondaryInput = it; isSaved = false },
            label = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "Traducción en Inglés *" else "English Translation *"
                    else
                        if (isSpanish) "Traducción en Español *" else "Spanish Translation *"
                ) 
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "ej. after-dinner conversation" else "e.g. after-dinner conversation"
                    else
                        if (isSpanish) "ej. resiliente / con capacidad de superación" else "e.g. resilient / persevering"
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_input_spanish"),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Phonetic Pronunciation
        OutlinedTextField(
            value = phonetic,
            onValueChange = { phonetic = it; isSaved = false },
            label = { Text(if (isSpanish) "Pronunciación Fonética IPA (Opcional)" else "IPA Phonetics (Optional)") },
            placeholder = { 
                Text(
                    if (isSpanishLearning) "ej. /so.βɾeˈme.sa/" else "ej. /rɪˈzɪl.jənt/"
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_input_phonetic"),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Example Sentence
        OutlinedTextField(
            value = example,
            onValueChange = { example = it; isSaved = false },
            label = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "Frase de Ejemplo en Español" else "Spanish Example Sentence"
                    else
                        if (isSpanish) "Frase de Ejemplo en Inglés" else "English Example Sentence"
                ) 
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "ej. Nos quedamos en la sobremesa durante horas." else "e.g. Nos quedamos en la sobremesa durante horas."
                    else
                        if (isSpanish) "ej. She is remarkably resilient in tough times." else "e.g. She is remarkably resilient in tough times."
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_input_example"),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Example Sentence Translation
        OutlinedTextField(
            value = exampleTranslation,
            onValueChange = { exampleTranslation = it; isSaved = false },
            label = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "Traducción de la Frase en Inglés" else "English Translation of Example"
                    else
                        if (isSpanish) "Traducción de la Frase en Español" else "Spanish Translation of Example"
                ) 
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning)
                        if (isSpanish) "ej. We stayed chatting at the table for hours." else "e.g. We stayed chatting at the table for hours."
                    else
                        if (isSpanish) "ej. Ella es notablemente resiliente en tiempos difíciles." else "e.g. She is remarkably resilient in tough times."
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_input_example_translation"),
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Card Type Selector (Word, Phrase, Phrasal Verb, Idiom, etc.)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("manual_type_selector_card")
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isSpanish) "🏷️ Tipo de Contenido (Frase, Verbo, Palabra...):" else "🏷️ Content Type (Phrase, Verb, Word...):",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(typeOptions) { typeOpt ->
                        val isSelected = selectedType.equals(typeOpt.id, ignoreCase = true)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surface,
                            border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, ElectricCyan) else androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { selectedType = typeOpt.id }
                                .testTag("type_chip_${typeOpt.id}")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(text = typeOpt.emoji, fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = typeOpt.label,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 12.5.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Hierarchical Destination Selector (Folder > List)
        DestinationFolderAndCategorySelector(
            viewModel = viewModel,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            title = if (isSpanish) "📁 Carpeta & Lista de Destino:" else "📁 Target Folder & List:"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Emoji Selector
        Text(
            text = if (isSpanish) "Ícono / Emoji:" else "Icon / Emoji:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            emojis.forEach { emo ->
                val isSelected = selectedEmoji == emo
                Surface(
                    shape = CircleShape,
                    color = if (isSelected) PrimaryIndigo.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, PrimaryIndigo) else null,
                    modifier = Modifier.clickable { selectedEmoji = emo }
                ) {
                    Text(
                        text = emo,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Submit Button
        Button(
            onClick = {
                if (primaryInput.isNotBlank() && secondaryInput.isNotBlank()) {
                    val finalEnglish = if (isSpanishLearning) secondaryInput.trim() else primaryInput.trim()
                    val finalSpanish = if (isSpanishLearning) primaryInput.trim() else secondaryInput.trim()
                    val finalExampleEnglish = if (isSpanishLearning) exampleTranslation.trim() else example.trim()
                    val finalExampleSpanish = if (isSpanishLearning) example.trim() else exampleTranslation.trim()

                    viewModel.addNewCard(
                        english = finalEnglish,
                        spanish = finalSpanish,
                        phonetic = phonetic,
                        example = finalExampleEnglish,
                        exampleTranslation = finalExampleSpanish,
                        category = selectedCategory,
                        type = selectedType,
                        cefrLevel = "",
                        emoji = selectedEmoji
                    )
                    primaryInput = ""
                    secondaryInput = ""
                    phonetic = ""
                    example = ""
                    exampleTranslation = ""
                    isSaved = true
                }
            },
            enabled = primaryInput.isNotBlank() && secondaryInput.isNotBlank(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("manual_submit_button")
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (isSpanish) "Guardar Tarjeta" else "Save Card", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        if (isSaved) {
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MasteredGreen.copy(alpha = 0.15f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isSpanish) "✅ ¡Tarjeta guardada exitosamente en tu baraja!" else "✅ Card successfully saved to your deck!",
                    color = MasteredGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

// -----------------------------------------------------------------------------------------
// 📥 IMPORT VOCABULARY VIEW
// -----------------------------------------------------------------------------------------
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImportVocabularyView(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    var rawText by remember { mutableStateOf("") }
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val activeCategoryFilter by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val isSpanishLearning = learningMode == com.example.ui.util.LearningMode.EN_TO_ES

    var selectedCategory by remember {
        mutableStateOf(
            if (activeCategoryFilter != "All" && activeCategoryFilter.isNotBlank()) activeCategoryFilter else "Everyday & Social"
        )
    }
    var selectedDefaultType by remember { mutableStateOf("Word") }
    var showNewCategoryDialog by remember { mutableStateOf(false) }
    var newCategoryName by remember { mutableStateOf("") }
    var importedCount by remember { mutableStateOf<Int?>(null) }
    var showEmptyError by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current

    val sampleTemplateText = if (isSpanishLearning) {
        "🚀 | dar en el clavo | hit the nail on the head | Acertaste por completo | You hit the nail on the head | Idiom\n" +
        "💡 | ponerse las pilas | get one's act together | Tenemos que apurarnos | We need to get our act together | Expression\n" +
        "☕ | sobremesa | after-dinner conversation | Charlamos un rato largo | We talked for a long time after dinner | Word\n" +
        "🌅 | madrugar | wake up early | Madrugo todos los lunes | I wake up early every Monday | Word"
    } else {
        "🚀 | breakthrough | avance crucial | This discovery is a breakthrough | Este descubrimiento es un gran avance | Word\n" +
        "☕ | catch up | ponerse al día | Let's catch up over coffee | Pongámonos al día con un café | Phrasal Verb\n" +
        "🥊 | bite the bullet | hacer de tripas corazón | I had to bite the bullet and do it | Tuve que hacer de tripas corazón y hacerlo | Idiom\n" +
        "💡 | brainstorm | lluvia de ideas | We need to brainstorm new solutions | Necesitamos generar nuevas soluciones | Word"
    }

    val availableTypes = listOf("Word", "Idiom", "Phrasal Verb", "Expression", "Slang", "Grammar")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = if (isSpanish) "📥 Importar Vocabulario en Bloque" else "📥 Batch Import Vocabulary",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = if (isSpanish)
                "Pega múltiples palabras o frases con emojis y barras (|) o guiones. Todo lo que pegues se guardará en la lista seleccionada:"
            else
                "Paste multiple words or phrases with emojis and pipes (|) or dashes. Everything imported will be saved into the selected list:",
            fontSize = 12.5.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // --- Hierarchical Destination Selector (Folder > List) for Import ---
        DestinationFolderAndCategorySelector(
            viewModel = viewModel,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            title = if (isSpanish) "📁 Carpeta & Lista de Destino:" else "📁 Target Folder & List:"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Quick helper action chips: Load Template, Paste, Clear
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Load Example Button
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = PrimaryIndigo.copy(alpha = 0.12f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                modifier = Modifier
                    .weight(1.3f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        rawText = sampleTemplateText
                        importedCount = null
                        showEmptyError = false
                    }
                    .testTag("btn_load_sample_template")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpanish) "Cargar Ejemplo" else "Load Example",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo
                    )
                }
            }

            // 2. Paste Clipboard Button
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        val clip = clipboardManager.getText()?.text
                        if (!clip.isNullOrBlank()) {
                            rawText = if (rawText.isBlank()) clip else "$rawText\n$clip"
                            importedCount = null
                            showEmptyError = false
                        }
                    }
                    .testTag("btn_paste_clipboard")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentPaste,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpanish) "Pegar" else "Paste",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // 3. Clear Text Button
            if (rawText.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.35f)),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            rawText = ""
                            importedCount = null
                            showEmptyError = false
                        }
                        .testTag("btn_clear_import_text")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSpanish) "Limpiar" else "Clear",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = rawText,
            onValueChange = { 
                rawText = it
                importedCount = null
                showEmptyError = false
            },
            placeholder = { 
                Text(
                    if (isSpanishLearning) {
                        if (isSpanish)
                            "// 1. Con iconos y barras (Emoji | Español | Inglés | Ejemplo):\n" +
                            "🚀 | dar en el clavo | hit the nail on the head | Acertaste por completo | Idiom\n\n" +
                            "// 2. Con guiones o enumeradas:\n" +
                            "💡 | 1. ponerse las pilas - get one's act together - Tenemos que apurarnos - Expression\n\n" +
                            "// 3. Formato simple:\n" +
                            "☕ | sobremesa | after-dinner conversation | Charlamos un rato largo"
                        else
                            "// 1. With icons & pipes (Emoji | Spanish | English | Example):\n" +
                            "🚀 | dar en el clavo | hit the nail on the head | You got it right | Idiom\n\n" +
                            "// 2. With dashes or numbered:\n" +
                            "💡 | 1. ponerse las pilas - get one's act together - We need to hurry - Expression\n\n" +
                            "// 3. Simple format:\n" +
                            "☕ | sobremesa | after-dinner conversation | We chatted for hours"
                    } else {
                        if (isSpanish)
                            "// 1. Con iconos y barras (Emoji | Inglés | Español | Ejemplo | Tipo):\n" +
                            "🚀 | breakthrough | avance crucial | This discovery is a breakthrough | Word\n" +
                            "☕ | catch up | ponerse al día | Let's catch up over coffee | Phrasal Verb\n" +
                            "🥊 | bite the bullet | hacer de tripas corazón | I had to bite the bullet | Idiom\n\n" +
                            "// 2. Con emoji incluido en el texto:\n" +
                            "💡 brainstorm | lluvia de ideas | We need to brainstorm new solutions | Word"
                        else
                            "// 1. With icons & pipes (Emoji | English | Spanish | Example | Type):\n" +
                            "🚀 | breakthrough | crucial advance | This discovery is a breakthrough | Word\n" +
                            "☕ | catch up | meet and exchange news | Let's catch up over coffee | Phrasal Verb\n" +
                            "🥊 | bite the bullet | endure pain | I had to bite the bullet | Idiom\n\n" +
                            "// 2. With emoji inside text:\n" +
                            "💡 brainstorm | generate ideas | We need to brainstorm new solutions | Word"
                    },
                    fontSize = 12.sp,
                    lineHeight = 16.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)
                ) 
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .testTag("import_text_field"),
            shape = RoundedCornerShape(16.dp)
        )

        if (showEmptyError) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚠️", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpanish)
                            "El cuadro de texto está vacío. Escribe o pega tus palabras, o pulsa '📋 Cargar Ejemplo' para probar."
                        else
                            "Text field is empty. Type, paste your words, or click '📋 Load Example' to test.",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                if (rawText.isBlank()) {
                    showEmptyError = true
                    importedCount = null
                } else {
                    showEmptyError = false
                    val count = viewModel.importPastedVocabulary(rawText, selectedCategory, selectedDefaultType)
                    importedCount = count
                    if (count > 0) {
                        rawText = "" // Automatically clear input field upon successful import
                    }
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("import_submit_button")
        ) {
            Icon(Icons.Default.FileUpload, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isSpanish) "Importar a '${selectedCategory}'" else "Import into '${selectedCategory}'",
                fontWeight = FontWeight.Bold,
                fontSize = 14.5.sp
            )
        }

        importedCount?.let { count ->
            Spacer(modifier = Modifier.height(14.dp))
            if (count > 0) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MasteredGreen.copy(alpha = 0.15f),
                    border = BorderStroke(1.5.dp, MasteredGreen.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🎉", fontSize = 22.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = if (isSpanish) "¡Importación Exitosa!" else "Import Successful!",
                                    color = MasteredGreen,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = if (isSpanish)
                                        "Se guardaron $count tarjetas en la lista '${selectedCategory}'."
                                    else
                                        "Saved $count cards into '${selectedCategory}'.",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    viewModel.setCategoryFilter(selectedCategory)
                                    viewModel.setNavIndex(0) // "Palabras"
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MasteredGreen),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.CollectionsBookmark, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isSpanish) "Ver Palabras" else "View Words",
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Button(
                                onClick = {
                                    viewModel.navigateToQuiz(category = selectedCategory, autoStart = true)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.School, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isSpanish) "Practicar" else "Practice",
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            } else {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.6f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("⚠️", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isSpanish) "No se detectaron tarjetas válidas" else "No valid cards detected",
                                color = MaterialTheme.colorScheme.error,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isSpanish)
                                "Asegúrate de escribir al menos una palabra por línea con formato como 'palabra | traducción' o pulsa '📋 Cargar Ejemplo' para ver el formato."
                            else
                                "Make sure to write at least one term per line with format like 'word | translation' or click '📋 Load Example' to see the format.",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }

    // New Category Dialog
    if (showNewCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showNewCategoryDialog = false },
            title = { Text(if (isSpanish) "Nueva Categoría" else "New Category") },
            text = {
                OutlinedTextField(
                    value = newCategoryName,
                    onValueChange = { newCategoryName = it },
                    label = { Text(if (isSpanish) "Nombre de la categoría" else "Category Name") },
                    placeholder = { Text(if (isSpanish) "ej. Medicina, Finanzas, Cocina..." else "e.g. Medicine, Finance, Cooking...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newCategoryName.isNotBlank()) {
                            val trimmed = newCategoryName.trim()
                            viewModel.addCategory(trimmed)
                            selectedCategory = trimmed
                            newCategoryName = ""
                            showNewCategoryDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                ) {
                    Text(if (isSpanish) "Agregar" else "Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNewCategoryDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }
}

private data class CardTypeItem(
    val id: String,
    val label: String,
    val emoji: String
)
