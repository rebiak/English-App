package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.OpenWith
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import kotlin.math.roundToInt

data class CursorTutorialStep(
    val stepIndex: Int,
    val stepNumberTextEs: String,
    val stepNumberTextEn: String,
    val titleEs: String,
    val titleEn: String,
    val targetNameEs: String,
    val targetNameEn: String,
    val whatIsItForEs: String,
    val whatIsItForEn: String,
    val bulletPointsEs: List<String>,
    val bulletPointsEn: List<String>,
    val icon: ImageVector,
    val accentColor: Color
)

private val HUB_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "UNO (1)",
            stepNumberTextEn = "ONE (1)",
            titleEs = "1. Seguir aprendiendo",
            titleEn = "1. Continue Learning",
            targetNameEs = "Tarjeta: Seguir aprendiendo",
            targetNameEn = "Card: Continue Learning",
            whatIsItForEs = "¿Para qué sirve esta parte?",
            whatIsItForEn = "What is this part for?",
            bulletPointsEs = listOf(
                "Te lleva directamente a la última tarjeta que estuviste practicando.",
                "Desliza hacia arriba o abajo para avanzar de palabra.",
                "Toca la tarjeta para voltearla y ver traducción y oraciones de ejemplo.",
                "Activa el avance automático para practicar sin tocar la pantalla."
            ),
            bulletPointsEn = listOf(
                "Takes you directly to the last card you were studying.",
                "Swipe up or down to move through words smoothly.",
                "Tap the card to flip it and view translation & sentence examples.",
                "Enable auto-scroll to study hands-free."
            ),
            icon = Icons.Filled.SwipeVertical,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "DOS (2)",
            stepNumberTextEn = "TWO (2)",
            titleEs = "2. Listas en Estudio",
            titleEn = "2. Lists in Study",
            targetNameEs = "Tarjeta: Listas en Estudio",
            targetNameEn = "Card: Lists in Study",
            whatIsItForEs = "¿Para qué sirve esta parte?",
            whatIsItForEn = "What is this part for?",
            bulletPointsEs = listOf(
                "Muestra únicamente las colecciones y cuadernos que estás repasando activamente.",
                "Organizadas por fecha de estudio y porcentaje de dominio.",
                "Al alcanzar el 100% de dominio en una lista, se retira automáticamente como completada."
            ),
            bulletPointsEn = listOf(
                "Displays only the collections and booklets you are actively reviewing.",
                "Sorted by recent study activity and mastery percentage.",
                "Reaching 100% mastery on a list automatically marks it as completed."
            ),
            icon = Icons.Filled.TrackChanges,
            accentColor = ElectricCyanDark
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "TRES (3)",
            stepNumberTextEn = "THREE (3)",
            titleEs = "3. Colecciones de Vocabulario",
            titleEn = "3. Vocabulary Collections",
            targetNameEs = "Tarjeta: Vocabulario",
            targetNameEn = "Card: Vocabulary",
            whatIsItForEs = "¿Para qué sirve esta parte?",
            whatIsItForEn = "What is this part for?",
            bulletPointsEs = listOf(
                "Acceso a la biblioteca completa con más de 2500 palabras y oraciones.",
                "Clasificadas por cuadernos oficiales, categorías temáticas y niveles CEFR (A1 a C2).",
                "Crea tus propias listas personalizadas y genera tarjetas con Inteligencia Artificial."
            ),
            bulletPointsEn = listOf(
                "Access to the complete library with over 2,500 words and sentences.",
                "Categorized by official booklets, themes, and CEFR levels (A1 to C2).",
                "Create custom lists and generate flashcards with Artificial Intelligence."
            ),
            icon = Icons.Filled.Folder,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "CUATRO (4)",
            stepNumberTextEn = "FOUR (4)",
            titleEs = "4. Progreso y Estadísticas",
            titleEn = "4. Progress & Statistics",
            targetNameEs = "Tarjeta: Progreso",
            targetNameEn = "Card: Progress",
            whatIsItForEs = "¿Para qué sirve esta parte?",
            whatIsItForEn = "What is this part for?",
            bulletPointsEs = listOf(
                "Revisa tus rachas de días seguidos y tiempo acumulado de estudio.",
                "Gráficos de palabras dominadas frente a las que están en aprendizaje.",
                "Configura y monitorea tu temporizador diario de estudio."
            ),
            bulletPointsEn = listOf(
                "Review your streak days and total accumulated study time.",
                "Charts comparing mastered words versus words in progress.",
                "Set and track your daily session study timer."
            ),
            icon = Icons.Filled.BarChart,
            accentColor = Color(0xFF8B5CF6)
        ),
        CursorTutorialStep(
            stepIndex = 4,
            stepNumberTextEs = "CINCO (5)",
            stepNumberTextEn = "FIVE (5)",
            titleEs = "5. Ajustes y Controles Rápidos",
            titleEn = "5. Quick Controls & Settings",
            targetNameEs = "Barra superior: Ajustes",
            targetNameEn = "Top bar: Settings",
            whatIsItForEs = "¿Para qué sirve esta parte?",
            whatIsItForEn = "What is this part for?",
            bulletPointsEs = listOf(
                "Bandera 🇪🇸/🇺🇸: Cambia el idioma de la aplicación entre Español e Inglés.",
                "Ícono Sol/Luna: Alterna el tema oscuro y claro.",
                "Ícono Filtros: Configura tu meta diaria de minutos de estudio.",
                "Ícono Ayuda (?): Vuelve a abrir este tutorial interactivo con cursor cuando quieras."
            ),
            bulletPointsEn = listOf(
                "Flag 🇪🇸/🇺🇸: Switch the app language between Spanish and English.",
                "Sun/Moon: Toggle between dark and light themes.",
                "Tune icon: Configure your daily study goal in minutes.",
                "Help icon (?): Reopen this interactive cursor tutorial anytime."
            ),
            icon = Icons.Filled.Tune,
            accentColor = EmeraldGreen
        )
    )
}

fun getHubTutorialSteps(): List<CursorTutorialStep> = HUB_TUTORIAL_STEPS

private val VOCABULARY_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "PARTE 1 DE 5",
            stepNumberTextEn = "PART 1 OF 5",
            titleEs = "1. Barra Superior de Control y Ajustes",
            titleEn = "1. Top Control & Settings Bar",
            targetNameEs = "Área Superior: Controles y Herramientas",
            targetNameEn = "Top Area: Controls & Tools",
            whatIsItForEs = "¿Para qué sirve cada componente de esta barra?",
            whatIsItForEn = "What does each component in this bar do?",
            bulletPointsEs = listOf(
                "🔥 Racha Diaria: Monitorea tus días consecutivos de estudio continuo para consolidar tu hábito.",
                "⏱️ Temporizador de Sesión: Cronómetro en tiempo real; registra tus minutos activos con opción de pausar o reanudar.",
                "🇪🇸/🇺🇸 Selector de Idioma: Alterna al instante toda la interfaz de la aplicación entre Español e Inglés.",
                "☀️/🌙 Modo Claro / Oscuro: Cambia el esquema visual para estudiar cómodamente durante el día o la noche.",
                "🎨 Selector de Temas: Personaliza la paleta de colores activa entre Índigo, Esmeralda, Ámbar o Amatista.",
                "❓ Botón de Ayuda (?): Vuelve a abrir este tutorial interactivo explicativo en cualquier momento."
            ),
            bulletPointsEn = listOf(
                "🔥 Daily Streak: Tracks your consecutive study days to build a consistent learning habit.",
                "⏱️ Session Timer: Live stopwatch recording your active study minutes with pause and resume.",
                "🇪🇸/🇺🇸 Language Switcher: Instantly toggles the entire app interface between Spanish and English.",
                "☀️/🌙 Light / Dark Mode: Switches visual themes comfortably for day or nighttime study.",
                "🎨 Theme Palette: Choose your preferred accent color (Indigo, Emerald, Amber, or Amethyst).",
                "❓ Help Button (?): Reopen this interactive walkthrough whenever you need guidance."
            ),
            icon = Icons.Filled.Tune,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "PARTE 2 DE 5",
            stepNumberTextEn = "PART 2 OF 5",
            titleEs = "2. Encabezado del Catálogo y Gestión de Contenido",
            titleEn = "2. Catalog Header & Content Management",
            targetNameEs = "Área de Encabezado: Volumen y Acciones de Creación",
            targetNameEn = "Header Area: Volume & Creation Actions",
            whatIsItForEs = "¿Qué componentes y opciones encuentras en el encabezado?",
            whatIsItForEn = "What components and options are in the header?",
            bulletPointsEs = listOf(
                "📚 Contador de Biblioteca: Muestra las más de 14,500 palabras y oraciones integradas con fonética IPA y audios.",
                "📁 Botón 'Nueva Carpeta': Crea y nombra carpetas personalizadas para organizar tus listas por temas, trabajo o metas.",
                "➕ Botón 'Nueva Lista': Crea un nuevo mazo de tarjetas desde cero para agregar vocabulario a medida."
            ),
            bulletPointsEn = listOf(
                "📚 Library Counter: Displays over 14,500 built-in words and phrases with IPA phonetics and native audio.",
                "📁 'New Folder' Button: Create and name custom folders to group decks by travel, work, or exam goals.",
                "➕ 'New List' Button: Create a brand new flashcard deck to store your custom vocabulary terms."
            ),
            icon = Icons.Filled.CollectionsBookmark,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "PARTE 3 DE 5",
            stepNumberTextEn = "PART 3 OF 5",
            titleEs = "3. Pestañas de Vista, Buscador y Chips de Filtro",
            titleEn = "3. View Tabs, Search & Filter Chips",
            targetNameEs = "Chips de Filtro: Todas, Creadas por mí, Basics...",
            targetNameEn = "Filter Chips: All, Custom, Basics, Vocab...",
            whatIsItForEs = "¿Para qué sirven estos filtros y pestañas?",
            whatIsItForEn = "What are these filters and tabs used for?",
            bulletPointsEs = listOf(
                "🎯 Filtro 'Todas': Visualiza el catálogo completo de tus cuadernos, carpetas y listas.",
                "⭐ Filtro 'Creadas por mí': Muestra exclusivamente el contenido y las listas personalizadas que tú has creado.",
                "🌱 Filtro 'Basics': Accede a los cuadernos fundamentales para principiantes con vocabulario elemental.",
                "📚 Filtro 'Vocabulario': Explora todos los cuadernos temáticos oficiales categorizados por nivel.",
                "💬 Filtro 'Traducciones': Practica con colecciones de oraciones reales, modismos y diálogos bilingües.",
                "🔍 Buscador Inteligente: Escribe cualquier término para filtrar palabras y listas al instante."
            ),
            bulletPointsEn = listOf(
                "🎯 'All' Filter: Displays your complete catalog of booklets, folders, and study lists.",
                "⭐ 'Created by me' Filter: Shows exclusively the custom lists and folders you have created.",
                "🌱 'Basics' Filter: Access foundational beginner booklets with essential vocabulary.",
                "📚 'Vocabulary' Filter: Browse all official thematic booklets organized by CEFR level.",
                "💬 'Translations' Filter: Practice real-world sentence collections, idioms, and bilingual dialogues.",
                "🔍 Smart Search: Type any term to filter vocabulary and decks instantly."
            ),
            icon = Icons.Filled.Search,
            accentColor = EmeraldGreen
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "PARTE 4 DE 5",
            stepNumberTextEn = "PART 4 OF 5",
            titleEs = "4. Catálogo Central de Cuadernos y Listas de Estudio",
            titleEn = "4. Central Booklet Catalog (Study Lists)",
            targetNameEs = "Área Central: Tarjetas de Cuadernos y Listas",
            targetNameEn = "Central Area: Booklet Cards & Study Lists",
            whatIsItForEs = "¿Qué componentes contiene cada tarjeta de cuaderno?",
            whatIsItForEn = "What components are inside each booklet card?",
            bulletPointsEs = listOf(
                "📘 Título y Nivel Oficial: Indica el nombre de la colección y su nivel del Marco Europeo (A1 Principiante a C2 Maestría).",
                "📊 Barra de Dominio y Avance: Refleja tu porcentaje de retención y la cantidad de palabras completadas al 100%.",
                "📁 Botón 'Abrir Listas': Despliega las sub-listas temáticas de 25 palabras que componen el cuaderno.",
                "▶ Botón 'Estudiar': Inicia inmediatamente la sesión interactiva de tarjetas swipe en la pantalla Aprender."
            ),
            bulletPointsEn = listOf(
                "📘 Title & CEFR Level: Shows the booklet name and its official proficiency level (A1 Beginner to C2 Mastery).",
                "📊 Mastery & Progress Bar: Reflects your retention percentage and words fully mastered at 100%.",
                "📁 'Open Lists' Button: Expands the thematic 25-word sub-decks that compose the booklet.",
                "▶ 'Study' Button: Immediately launches the interactive swipe session in the Learn screen."
            ),
            icon = Icons.Filled.Folder,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 4,
            stepNumberTextEs = "PARTE 5 DE 5",
            stepNumberTextEn = "PART 5 OF 5",
            titleEs = "5. Barra de Navegación Global y Retorno Rápido",
            titleEn = "5. Bottom Navigation Bar & Quick Return",
            targetNameEs = "Área Inferior: Navegación Global y Botón Flotante",
            targetNameEn = "Bottom Area: Global Navigation & Floating Shortcut",
            whatIsItForEs = "¿Cómo navegar y saltar a tu sesión activa?",
            whatIsItForEn = "How do you navigate and jump to your active session?",
            bulletPointsEs = listOf(
                "🧭 Pestañas Globales: Desplázate directamente entre Vocabulario, Práctica, Aprender, Crear y Progreso.",
                "🚀 Botón 'Volver a Aprender': Regresa en un solo toque a la tarjeta exacta que estabas practicando en el reel."
            ),
            bulletPointsEn = listOf(
                "🧭 Global Tabs: Jump seamlessly between Vocabulary, Practice, Learn, Create, and Progress.",
                "🚀 'Back to Learn' Button: Returns with one tap directly to the last card you were studying in the reel."
            ),
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            accentColor = ElectricCyanDark
        )
    )
}

fun getVocabularyTutorialSteps(): List<CursorTutorialStep> = VOCABULARY_TUTORIAL_STEPS

private val PRACTICE_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "PARTE 1 DE 5",
            stepNumberTextEn = "PART 1 OF 5",
            titleEs = "1. Selector de Modalidad y Barra Superior",
            titleEn = "1. Practice Modes & Top Bar",
            targetNameEs = "Área Superior: Selector Quiz vs Escuchar",
            targetNameEn = "Top Area: Quiz vs Listening Selector",
            whatIsItForEs = "¿Qué modalidades de práctica tienes a tu disposición?",
            whatIsItForEn = "What practice modes are available?",
            bulletPointsEs = listOf(
                "📝 Modo Cuestionario (Quiz): Evaluaciones interactivas de opción múltiple con retroalimentación instantánea.",
                "🎧 Modo Comprensión Auditiva (Listening): Dictados y ejercicios de audio con voz nativa en inglés para afinar el oído.",
                "⚙️ Barra Superior: Controles de racha diaria continua, temporizador de sesión, idioma y temas visuales."
            ),
            bulletPointsEn = listOf(
                "📝 Quiz Mode: Interactive multiple-choice evaluations with instant corrective feedback.",
                "🎧 Listening Mode: Dictation and native English voice listening drills to sharpen your ear.",
                "⚙️ Top Bar: Live controls for daily streak, session timer, language toggle, and visual themes."
            ),
            icon = Icons.Filled.Quiz,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "PARTE 2 DE 5",
            stepNumberTextEn = "PART 2 OF 5",
            titleEs = "2. Paso 1: Selección de Colección o Carpeta",
            titleEn = "2. Step 1: Collection or Folder Selection",
            targetNameEs = "Área de Colección: Selección de Cuaderno Base",
            targetNameEn = "Collection Area: Base Booklet Selection",
            whatIsItForEs = "¿Cómo seleccionar el material de estudio para evaluar?",
            whatIsItForEn = "How do you pick study material to evaluate?",
            bulletPointsEs = listOf(
                "📚 Colecciones Oficiales: Elige entre Todas, Basics 1 a 6, Vocabulario o Frases comunes.",
                "📁 Carpetas Personales: Selecciona cualquiera de tus carpetas personalizadas guardadas.",
                "🔢 Contador de Preguntas: Indica cuántas palabras y preguntas potenciales contiene cada opción."
            ),
            bulletPointsEn = listOf(
                "📚 Official Collections: Choose from All, Basics 1 through 6, Vocabulary, or Common Phrases.",
                "📁 Personal Folders: Select any of your custom saved folders.",
                "🔢 Word Counter: Shows exactly how many words and potential questions each set contains."
            ),
            icon = Icons.Filled.Folder,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "PARTE 3 DE 5",
            stepNumberTextEn = "PART 3 OF 5",
            titleEs = "3. Paso 2: Selección de Lista y Muestreo Dinámico",
            titleEn = "3. Step 2: Specific List & Dynamic Sampling",
            targetNameEs = "Área de Lista: Sub-listas y Muestra Dinámica",
            targetNameEn = "List Area: Sub-decks & Dynamic Sample",
            whatIsItForEs = "¿Cómo funciona la selección de listas y el muestreo?",
            whatIsItForEn = "How does list selection and sampling work?",
            bulletPointsEs = listOf(
                "📋 Sub-listas Temáticas: Enfoca tu evaluación en una lista puntual de 25 palabras para un repaso rápido.",
                "🌐 Colección Completa: Evalúa el contenido global de todo el cuaderno para un desafío de mayor alcance.",
                "⚡ Muestreo Inteligente: En listas extensas, el sistema extrae automáticamente 25 preguntas clave con distractores de nivel similar."
            ),
            bulletPointsEn = listOf(
                "📋 Topic Sub-decks: Focus your evaluation on a specific 25-word list for quick, agile practice.",
                "🌐 Entire Collection: Test the complete booklet content for a broad mastery challenge.",
                "⚡ Smart Sampling: For large sets, the app automatically extracts a balanced 25-question quiz with calibrated distractors."
            ),
            icon = Icons.Filled.TrackChanges,
            accentColor = EmeraldGreen
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "PARTE 4 DE 5",
            stepNumberTextEn = "PART 4 OF 5",
            titleEs = "4. Motores de Inicio: Quiz Instantáneo vs Con IA",
            titleEn = "4. Launch Engines: Instant vs AI Quiz",
            targetNameEs = "Área de Acciones: Botones de Inicio de Evaluación",
            targetNameEn = "Action Area: Evaluation Launch Buttons",
            whatIsItForEs = "¿Qué diferencia hay entre los dos botones de inicio?",
            whatIsItForEn = "What is the difference between both start buttons?",
            bulletPointsEs = listOf(
                "⚡ Iniciar Quiz Aleatorio (Sin IA): Funciona 100% offline sin esperas ni internet, con distractores calculados de la base de datos local.",
                "✨ Generar Quiz con IA: Conecta con Gemini para formular preguntas contextuales desafiantes y análisis de uso en la vida real.",
                "📊 Puntuación y Dominio: Cada acierto consolida el nivel de retención de las palabras en tu perfil."
            ),
            bulletPointsEn = listOf(
                "⚡ Start Random Quiz (Offline): 100% offline with zero latency, utilizing smart distractors from the local database.",
                "✨ Generate Quiz with AI: Connects to Gemini to create challenging contextual questions and real-world nuance checks.",
                "📊 Score & Retention: Every correct answer increases vocabulary mastery in your user profile."
            ),
            icon = Icons.Filled.PlayArrow,
            accentColor = ElectricCyanDark
        ),
        CursorTutorialStep(
            stepIndex = 4,
            stepNumberTextEs = "PARTE 5 DE 5",
            stepNumberTextEn = "PART 5 OF 5",
            titleEs = "5. Barra de Navegación Global",
            titleEn = "5. Global Bottom Navigation Bar",
            targetNameEs = "Área Inferior: Navegación entre Secciones",
            targetNameEn = "Bottom Area: Navigation Across Sections",
            whatIsItForEs = "¿Cómo desplazarte por las demás áreas de la app?",
            whatIsItForEn = "How do you move to other app sections?",
            bulletPointsEs = listOf(
                "🧭 Pestañas Globales: Acceso directo a Vocabulario, Práctica, Aprender, Crear y Progreso.",
                "↩️ Botón Volver: Regresa de inmediato a la sección de la que venías."
            ),
            bulletPointsEn = listOf(
                "🧭 Global Tabs: Direct access to Vocabulary, Practice, Learn, Create, and Progress.",
                "↩️ Back Button: Return instantly to the previous section."
            ),
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            accentColor = PracticeCoral
        )
    )
}

fun getPracticeTutorialSteps(): List<CursorTutorialStep> = PRACTICE_TUTORIAL_STEPS

private val LEARN_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "PARTE 1 DE 4",
            stepNumberTextEn = "PART 1 OF 4",
            titleEs = "1. Barra Superior de Control y Enfoque",
            titleEn = "1. Top Control & Focus Bar",
            targetNameEs = "Área Superior: Barra Completa de Enfoque",
            targetNameEn = "Top Area: Complete Focus Header Bar",
            whatIsItForEs = "¿Para qué sirve cada control de la barra superior?",
            whatIsItForEn = "What does each control in the top bar do?",
            bulletPointsEs = listOf(
                "🔥 Racha Diaria (Streak): Días consecutivos de estudio continuo para afianzar tu constancia.",
                "⏱️ Temporizador de Sesión: Cronómetro en tiempo real; toca para pausar o reanudar tus minutos de enfoque.",
                "🇪🇸/🇺🇸 Selector de Idioma: Alterna al instante toda la aplicación entre Español e Inglés.",
                "☀️/🌙 Modo Claro / Oscuro: Ajusta el contraste visual para estudiar cómodamente de día o de noche.",
                "🎨 Selector de Temas: Cambia la paleta de colores activa (Índigo, Esmeralda, Ámbar, Amatista).",
                "❓ Botón de Ayuda (?): Vuelve a abrir este tutorial interactivo para repasar cualquier componente."
            ),
            bulletPointsEn = listOf(
                "🔥 Daily Streak: Tracks your consecutive study days to maintain continuous momentum.",
                "⏱️ Session Timer: Live stopwatch; tap to pause or resume your active focus minutes.",
                "🇪🇸/🇺🇸 Language Toggle: Instantly switch the entire app between Spanish and English.",
                "☀️/🌙 Light / Dark Mode: Adjust contrast comfortably for day or night reading.",
                "🎨 Theme Switcher: Customize the accent palette (Indigo, Emerald, Amber, Amethyst).",
                "❓ Help Button (?): Reopen this interactive walkthrough anytime to review any feature."
            ),
            icon = Icons.Filled.Tune,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "PARTE 2 DE 4",
            stepNumberTextEn = "PART 2 OF 4",
            titleEs = "2. Tarjeta Central Rectangular (Área de Estudio)",
            titleEn = "2. Central Rectangular Card (Study Area)",
            targetNameEs = "Área Central: Tarjeta Flashcard Rectangular",
            targetNameEn = "Central Area: Rectangular Flashcard Card",
            whatIsItForEs = "¿Qué componentes integran la tarjeta rectangular de estudio?",
            whatIsItForEn = "What components make up the rectangular flashcard?",
            bulletPointsEs = listOf(
                "🏷️ Encabezado y Cuaderno: Indica la lista actual en estudio (ej. Basics 1 - List 1) y la categoría temática activa.",
                "⏱️ Insignia 'Auto': Activa la reproducción manos libres; las tarjetas van pasando solas con pronunciación continua en audio.",
                "🔤 Clasificación de Término: Etiqueta que clasifica la entrada como Palabra ([WORD]), Frase o Gramática.",
                "✏️ Palabra Principal e Ilustración: Gran tipografía en inglés acompañada de un emoji ilustrativo para anclaje visual.",
                "🔊 Botón 'Escuchar' (Azul): Pronunciación en inglés con voz nativa a velocidad estándar de conversación.",
                "🐢 Botón 'Lento' (Gris/Verde): Pronunciación pausada para captar cada vocal, consonante y acento con claridad.",
                "📊 Barra de Dominio y Estado: Porcentaje de aprendizaje (0% a 100%) y estado actual ('Nueva', 'En Repaso', 'Dominada').",
                "🔄 Indicador de Volteo y Reel: Toca la tarjeta en cualquier parte para voltearla al reverso, o desliza ↑ para la siguiente tarjeta.",
                "⛶ Modo Pantalla Completa: Amplía la tarjeta para concentración máxima ocultando distracciones."
            ),
            bulletPointsEn = listOf(
                "🏷️ Header & Booklet: Displays active study deck (e.g. Basics 1 - List 1) and active thematic category.",
                "⏱️ 'Auto' Badge: Enables hands-free autoplay; cards advance automatically with continuous audio.",
                "🔤 Term Classification: Tag identifying the card as a Word ([WORD]), Phrase, or Grammar item.",
                "✏️ Word & Visual Illustration: Large English typography paired with an illustrative emoji for visual anchoring.",
                "🔊 'Listen' Button (Blue): Native English pronunciation at standard conversational speed.",
                "🐢 'Slow' Button (Gray/Green): Slow-speed pronunciation to hear every consonant and phoneme clearly.",
                "📊 Mastery & State Bar: Learning percentage (0% to 100%) and current state ('New', 'In Review', 'Mastered').",
                "🔄 Flip & Reel Cue: Tap anywhere on the card to flip it over, or swipe ↑ for the next flashcard in the reel.",
                "⛶ Fullscreen Mode: Expands the card for distraction-free deep focus study."
            ),
            icon = Icons.Filled.School,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "PARTE 3 DE 4",
            stepNumberTextEn = "PART 3 OF 4",
            titleEs = "3. Barra de Acciones Inferiores (Repasar, Voltear y Lo sé)",
            titleEn = "3. Bottom Action Buttons (Review, Flip & I Know It)",
            targetNameEs = "Área Inferior: Fila de Botones de Evaluación",
            targetNameEn = "Bottom Area: Evaluation Action Buttons Row",
            whatIsItForEs = "¿Para qué sirve cada uno de los tres botones de acción?",
            whatIsItForEn = "What does each of the three action buttons do?",
            bulletPointsEs = listOf(
                "❌ Botón 'Repasar' (Rojo / Swipe Izquierda 👈): Si no recuerdas bien la palabra, disminuye el dominio y el algoritmo la programará para repasos frecuentes.",
                "🔄 Botón 'Voltear' (Azul Central): Gira la tarjeta 3D para revelar la traducción en español, pronunciación fonética IPA y oraciones de ejemplo reales.",
                "✓ Botón 'Lo sé' (Verde / Swipe Derecha 👉): Suma +25% de dominio reconociendo que ya vas aprendiendo la palabra hasta alcanzar el 100% (Dominada)."
            ),
            bulletPointsEn = listOf(
                "❌ 'Review' Button (Red / Swipe Left 👈): If you struggle with the word, reduces mastery and schedules frequent reviews.",
                "🔄 'Flip' Button (Center Blue): Rotates card in 3D to reveal Spanish translation, IPA phonetics, and real example sentences.",
                "✓ 'I Know It' Button (Green / Swipe Right 👉): Adds +25% mastery acknowledging your learning progress until reaching 100% (Mastered)."
            ),
            icon = Icons.Filled.TouchApp,
            accentColor = EmeraldGreen
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "PARTE 4 DE 4",
            stepNumberTextEn = "PART 4 OF 4",
            titleEs = "4. Barra de Navegación Global (Pestañas de la App)",
            titleEn = "4. Bottom Navigation Bar (Global Tabs)",
            targetNameEs = "Área Inferior: Barra de Pestañas Globales",
            targetNameEn = "Bottom Area: Global Navigation Tabs Bar",
            whatIsItForEs = "¿Cómo desplazarte entre las diferentes áreas de la app?",
            whatIsItForEn = "How do you navigate across the app sections?",
            bulletPointsEs = listOf(
                "📚 Vocabulario: Catálogo completo de cuadernos oficiales (Basics 1-6, Vocabulario, Frases) y tus carpetas.",
                "📝 Práctica: Cuestionarios interactivos de opción múltiple (Quiz) y ejercicios de comprensión auditiva (Listening).",
                "🗣️ Aprender: Tu sesión activa de tarjetas interactivas (esta pantalla).",
                "➕ Crear: Diseña tarjetas con Inteligencia Artificial (Gemini), modo manual o importando textos.",
                "📊 Progreso: Resumen de avance, palabras dominadas, racha diaria y temporizador de concentración."
            ),
            bulletPointsEn = listOf(
                "📚 Vocabulary: Complete catalog of official booklets (Basics 1-6, Vocabulary, Phrases) and your folders.",
                "📝 Practice: Interactive multiple-choice quizzes and listening comprehension drills.",
                "🗣️ Learn: Your active flashcard swipe session (this screen).",
                "➕ Create: Design cards with Artificial Intelligence (Gemini), manual mode, or text import.",
                "📊 Progress: Summary of retention, mastered terms, daily streaks, and study timers."
            ),
            icon = Icons.Filled.FolderOpen,
            accentColor = ElectricCyanDark
        )
    )
}

fun getLearnTutorialSteps(): List<CursorTutorialStep> = LEARN_TUTORIAL_STEPS

private val CREATE_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "PARTE 1 DE 5",
            stepNumberTextEn = "PART 1 OF 5",
            titleEs = "1. Selector de Modos de Creación",
            titleEn = "1. Creation Modes Selector",
            targetNameEs = "Área Superior: Pestañas de Modos de Creación",
            targetNameEn = "Top Area: Creation Mode Tabs",
            whatIsItForEs = "¿Cuáles son las formas disponibles para crear nuevas tarjetas?",
            whatIsItForEn = "What are the available ways to create new cards?",
            bulletPointsEs = listOf(
                "✨ Generador con IA: Crea tarjetas inteligentes completas con definición, pronunciación IPA y oraciones de ejemplo en un toque.",
                "✏️ Modo Manual: Introduce tus propios términos, traducciones personalizadas y notas de estudio a mano.",
                "📄 Importar Texto: Pega artículos o párrafos completos para que el sistema extraiga vocabulario relevante automáticamente."
            ),
            bulletPointsEn = listOf(
                "✨ AI Generator: Create smart comprehensive cards with definitions, IPA phonetics, and examples in one tap.",
                "✏️ Manual Mode: Manually enter your own custom terms, translations, and personal study notes.",
                "📄 Import Text: Paste entire articles or paragraphs to automatically extract key vocabulary terms."
            ),
            icon = Icons.Filled.AutoAwesome,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "PARTE 2 DE 5",
            stepNumberTextEn = "PART 2 OF 5",
            titleEs = "2. Modalidad por Palabras / Tema y Dirección de Idioma",
            titleEn = "2. Words vs Topic Mode & Language Direction",
            targetNameEs = "Área de Configuración: Formato y Dirección de Idioma",
            targetNameEn = "Config Area: Format & Language Direction",
            whatIsItForEs = "¿Cómo estructurar los términos que deseas generar?",
            whatIsItForEn = "How do you structure the terms you want to generate?",
            bulletPointsEs = listOf(
                "🔤 Por Palabras / Lista: Escribe palabras individuales o separadas por comas (ej. mindset, break down).",
                "💡 Por Tema o Situación: Escribe una temática (ej. 'Aeropuerto', 'Entrevista de trabajo') y la IA creará un mazo temático completo.",
                "🔄 Selector de Dirección: Elige entre Inglés ➔ Español, Español ➔ Inglés o Detección Automática."
            ),
            bulletPointsEn = listOf(
                "🔤 By Words / List: Type individual or comma-separated terms (e.g. mindset, break down).",
                "💡 By Topic / Situation: Enter a theme (e.g. 'Airport', 'Job Interview') and AI creates an entire thematic deck.",
                "🔄 Direction Selector: Choose English ➔ Spanish, Spanish ➔ English, or Automatic Detection."
            ),
            icon = Icons.Filled.Translate,
            accentColor = ElectricCyanDark
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "PARTE 3 DE 5",
            stepNumberTextEn = "PART 3 OF 5",
            titleEs = "3. Campo de Entrada de Texto y Sugerencias Rápidas",
            titleEn = "3. Text Input Field & Quick Suggestions",
            targetNameEs = "Área de Entrada: Campo de Escritura y Chips Sugeridos",
            targetNameEn = "Input Area: Writing Field & Suggested Chips",
            whatIsItForEs = "¿Qué puedes ingresar en este campo?",
            whatIsItForEn = "What can you enter in this field?",
            bulletPointsEs = listOf(
                "📝 Campo de Escritura: Escribe las palabras, modismos o la temática que deseas aprender.",
                "🏷️ Chips de Sugerencia Rápida: Toca ejemplos listos (ej. resilience, bite the bullet, serendipity) para probar la IA al instante.",
                "📂 Lista de Destino: Selecciona en qué cuaderno o carpeta se guardarán tus tarjetas creadas."
            ),
            bulletPointsEn = listOf(
                "📝 Writing Field: Type the vocabulary words, idioms, or topic you want to learn.",
                "🏷️ Quick Suggestion Chips: Tap ready examples (e.g. resilience, bite the bullet, serendipity) to test AI immediately.",
                "📂 Target List: Select which booklet or folder your created cards will be saved into."
            ),
            icon = Icons.Filled.Search,
            accentColor = EmeraldGreen
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "PARTE 4 DE 5",
            stepNumberTextEn = "PART 4 OF 5",
            titleEs = "4. Botón 'Generar con IA' y Estado de Gemini",
            titleEn = "4. 'Generate with AI' Button & Gemini Status",
            targetNameEs = "Área de Generación: Botón Principal y Panel del Asistente",
            targetNameEn = "Generation Area: Main Button & Assistant Panel",
            whatIsItForEs = "¿Qué ocurre al pulsar el botón de generación?",
            whatIsItForEn = "What happens when you tap generate?",
            bulletPointsEs = listOf(
                "✨ Botón 'Generar con IA': Procesa los términos y genera las tarjetas enriquecidas con fonética, traducción y ejemplos.",
                "🤖 Estado de Gemini (Google AI Studio): Muestra la conexión activa con los modelos Gemini y su disponibilidad.",
                "📚 Guardado Inmediato: Añade automáticamente las tarjetas a tu colección para que puedas estudiarlas en Aprender."
            ),
            bulletPointsEn = listOf(
                "✨ 'Generate with AI' Button: Processes the terms and creates flashcards enriched with phonetics, translation, and examples.",
                "🤖 Gemini Status (Google AI Studio): Shows live connection status and availability with Gemini models.",
                "📚 Immediate Save: Automatically appends generated cards to your study deck for swiping in Learn."
            ),
            icon = Icons.Filled.Psychology,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 4,
            stepNumberTextEs = "PARTE 5 DE 5",
            stepNumberTextEn = "PART 5 OF 5",
            titleEs = "5. Barra de Navegación Global",
            titleEn = "5. Global Bottom Navigation Bar",
            targetNameEs = "Área Inferior: Navegación entre Pantallas",
            targetNameEn = "Bottom Area: Navigation Across Screens",
            whatIsItForEs = "¿Cómo desplazarte a las demás secciones?",
            whatIsItForEn = "How do you navigate to other sections?",
            bulletPointsEs = listOf(
                "🧭 Pestañas Globales: Acceso directo a Vocabulario, Práctica, Aprender, Crear y Progreso."
            ),
            bulletPointsEn = listOf(
                "🧭 Global Tabs: Direct access to Vocabulary, Practice, Learn, Create, and Progress."
            ),
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            accentColor = PrimaryIndigo
        )
    )
}

fun getCreateTutorialSteps(): List<CursorTutorialStep> = CREATE_TUTORIAL_STEPS

private val PROGRESS_TUTORIAL_STEPS: List<CursorTutorialStep> by lazy {
    listOf(
        CursorTutorialStep(
            stepIndex = 0,
            stepNumberTextEs = "PARTE 1 DE 5",
            stepNumberTextEn = "PART 1 OF 5",
            titleEs = "1. Tarjeta Principal de Avance y Nivel de Dominio",
            titleEn = "1. Main Progress Card & Mastery Level",
            targetNameEs = "Área Superior: Resumen de Avance y Dominio Global",
            targetNameEn = "Top Area: Global Progress & Mastery Summary",
            whatIsItForEs = "¿Qué información encuentras en el panel de avance general?",
            whatIsItForEn = "What information is inside the main progress panel?",
            bulletPointsEs = listOf(
                "🎯 Anillo de Dominio Total: Porcentaje global de retención calculado a partir de tus sesiones de swipe y quizzes.",
                "📚 Palabras Completadas: Conteo exacto de palabras dominadas frente al total de la biblioteca.",
                "📈 Barra de Progreso y Rango: Muestra tu evolución y nivel actual como estudiante de inglés."
            ),
            bulletPointsEn = listOf(
                "🎯 Total Mastery Ring: Global retention percentage computed from your swipe sessions and quizzes.",
                "📚 Completed Words: Exact count of fully mastered words versus the complete vocabulary library.",
                "📈 Progress Bar & Rank: Shows your learning journey and current English proficiency rank."
            ),
            icon = Icons.Filled.BarChart,
            accentColor = PrimaryIndigo
        ),
        CursorTutorialStep(
            stepIndex = 1,
            stepNumberTextEs = "PARTE 2 DE 5",
            stepNumberTextEn = "PART 2 OF 5",
            titleEs = "2. Indicadores Clave de Desempeño (KPIs)",
            titleEn = "2. Key Performance Indicators (KPIs)",
            targetNameEs = "Área de Métricas: Racha, Estudio Diario y Biblioteca",
            targetNameEn = "Metrics Area: Streak, Daily Study & Library",
            whatIsItForEs = "¿Para qué sirven estas tres métricas clave?",
            whatIsItForEn = "What are these three key metrics for?",
            bulletPointsEs = listOf(
                "🔥 Racha Actual: Días consecutivos de estudio continuo sin interrupciones.",
                "⚡ Palabras Estudiadas Hoy: Cantidad de tarjetas revisadas durante la jornada de hoy.",
                "📚 Total en Biblioteca: Tamaño completo de tu vocabulario disponible para estudiar."
            ),
            bulletPointsEn = listOf(
                "🔥 Active Streak: Consecutive uninterrupted study days.",
                "⚡ Words Studied Today: Number of flashcards reviewed during today's session.",
                "📚 Library Total: Full volume of vocabulary available in your catalog."
            ),
            icon = Icons.Filled.BarChart,
            accentColor = StarAmber
        ),
        CursorTutorialStep(
            stepIndex = 2,
            stepNumberTextEs = "PARTE 3 DE 5",
            stepNumberTextEn = "PART 3 OF 5",
            titleEs = "3. Cronómetro de Sesión y Recordatorio Diario",
            titleEn = "3. Session Stopwatch & Daily Reminder",
            targetNameEs = "Área de Tiempo: Temporizador y Alarma de Enfoque",
            targetNameEn = "Time Area: Focus Timer & Study Alarm",
            whatIsItForEs = "¿Cómo gestionar tu tiempo y constancia de estudio?",
            whatIsItForEn = "How do you manage study time and consistency?",
            bulletPointsEs = listOf(
                "⏱️ Cronómetro de Enfoque: Registra los minutos de estudio activo con controles de pausa, reanudación y reinicio.",
                "⏳ Botones Rápidos (-5m / +5m): Ajusta de forma ágil tu meta de minutos diarios de concentración.",
                "📅 Programar Alarma Diaria: Configura la hora exacta en que la app te avisará para estudiar.",
                "🔔 Probar Alerta: Escucha el timbre de recordatorio para verificar las notificaciones."
            ),
            bulletPointsEn = listOf(
                "⏱️ Focus Stopwatch: Tracks active study minutes with pause, resume, and reset controls.",
                "⏳ Quick Buttons (-5m / +5m): Easily adjust your daily concentration target minutes.",
                "📅 Schedule Daily Alarm: Configure the exact time the app reminds you to study.",
                "🔔 Test Alert: Listen to the chime sound to verify reminder notifications."
            ),
            icon = Icons.Filled.Timer,
            accentColor = ElectricCyanDark
        ),
        CursorTutorialStep(
            stepIndex = 3,
            stepNumberTextEs = "PARTE 4 DE 5",
            stepNumberTextEn = "PART 4 OF 5",
            titleEs = "4. Gráfica de Distribución de Estados del Vocabulario",
            titleEn = "4. Vocabulary Status Distribution Chart",
            targetNameEs = "Área de Gráfica: Distribución del Nivel de Dominio",
            targetNameEn = "Chart Area: Mastery Level Distribution",
            whatIsItForEs = "¿Cómo interpretar las fases de tu vocabulario?",
            whatIsItForEn = "How do you interpret your vocabulary statuses?",
            bulletPointsEs = listOf(
                "🟢 Dominadas (100%): Palabras consolidadas en tu memoria a largo plazo tras acertar en repetición espaciada.",
                "🟡 En Repaso (50-75%): Palabras en proceso de asimilación que necesitan práctica ocasional.",
                "🔵 En Estudio (25%): Palabras vistas recientemente que requieren repasar su pronunciación y ejemplos.",
                "⚪ Nuevas (0%): Términos aún no practicados esperando en tus cuadernos."
            ),
            bulletPointsEn = listOf(
                "🟢 Mastered (100%): Words solidified in long-term memory via spaced repetition successes.",
                "🟡 In Review (50-75%): Words in the assimilation stage that need occasional practice.",
                "🔵 In Study (25%): Recently seen words requiring review of pronunciation and examples.",
                "⚪ New (0%): Untouched terms waiting in your booklets."
            ),
            icon = Icons.Filled.TrackChanges,
            accentColor = EmeraldGreen
        ),
        CursorTutorialStep(
            stepIndex = 4,
            stepNumberTextEs = "PARTE 5 DE 5",
            stepNumberTextEn = "PART 5 OF 5",
            titleEs = "5. Barra de Navegación Global",
            titleEn = "5. Global Bottom Navigation Bar",
            targetNameEs = "Área Inferior: Navegación entre Secciones",
            targetNameEn = "Bottom Area: Section Navigation",
            whatIsItForEs = "¿Cómo desplazarte a las demás áreas de la aplicación?",
            whatIsItForEn = "How do you navigate to other app areas?",
            bulletPointsEs = listOf(
                "🧭 Pestañas Globales: Acceso directo a Vocabulario, Práctica, Aprender, Crear y Progreso."
            ),
            bulletPointsEn = listOf(
                "🧭 Global Tabs: Direct access to Vocabulary, Practice, Learn, Create, and Progress."
            ),
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            accentColor = PrimaryIndigo
        )
    )
}

fun getProgressTutorialSteps(): List<CursorTutorialStep> = PROGRESS_TUTORIAL_STEPS

fun getInAppTutorialSteps(): List<CursorTutorialStep> = getVocabularyTutorialSteps()

fun getTutorialStepsForTab(tabIndex: Int): List<CursorTutorialStep> {
    return when (tabIndex) {
        0 -> getVocabularyTutorialSteps()
        1 -> getPracticeTutorialSteps()
        2 -> getLearnTutorialSteps()
        3 -> getCreateTutorialSteps()
        4 -> getProgressTutorialSteps()
        else -> getVocabularyTutorialSteps()
    }
}

@Composable
fun InAppCursorWalkthroughOverlay(
    appLanguage: AppLanguage,
    activeTab: Int = 0,
    targetBounds: Map<Int, Rect> = emptyMap(),
    onTabSelected: (Int) -> Unit = {},
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val steps = remember(activeTab) { getTutorialStepsForTab(activeTab) }
    InteractiveCursorWalkthroughOverlay(
        appLanguage = appLanguage,
        activeTab = activeTab,
        targetBounds = targetBounds,
        steps = steps,
        isHubMode = false,
        onTabSelected = onTabSelected,
        onDismiss = onDismiss,
        modifier = modifier
    )
}

@Composable
fun InteractiveCursorWalkthroughOverlay(
    appLanguage: AppLanguage,
    activeTab: Int = 0,
    targetBounds: Map<Int, Rect> = emptyMap(),
    isHubMode: Boolean = true,
    steps: List<CursorTutorialStep>? = null,
    onOpenInAppTutorial: (() -> Unit)? = null,
    onTabSelected: (Int) -> Unit = {},
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSpanish = appLanguage == AppLanguage.SPANISH

    val activeSteps = steps ?: remember(activeTab, isHubMode) {
        if (isHubMode) getHubTutorialSteps() else getTutorialStepsForTab(activeTab)
    }
    val steps = activeSteps

    var currentStepIndex by remember(activeTab) { mutableIntStateOf(0) }
    // Map tracking whether each step has been tapped/checked by the user
    val checkedSteps = remember(activeTab) { mutableStateMapOf<Int, Boolean>() }
    var isCurrentPointChecked by remember(currentStepIndex, activeTab) {
        mutableStateOf(checkedSteps[currentStepIndex] == true)
    }
    var isFinished by remember(activeTab) { mutableStateOf(false) }

    // Emergent Modal Expansion state ("Presiono Escalar de clic, se pone emergente y más grande")
    var isEmergentExpanded by remember(activeTab) { mutableStateOf(false) }
    // Interactive Scale Level: 0 = 1.0x (Normal), 1 = 1.25x (Grande), 2 = 1.5x (Extra Grande)
    var scaleLevel by remember { mutableIntStateOf(1) }
    val textScale = when (scaleLevel) {
        0 -> 1.0f
        1 -> 1.22f
        else -> 1.45f
    }

    val coroutineScope = rememberCoroutineScope()

    // Free dragging offset with finger for the docked HUD panel ("mover con el dedo para ver abajo")
    var hudDragOffsetX by remember(activeTab) { mutableFloatStateOf(0f) }
    var hudDragOffsetY by remember(activeTab) { mutableFloatStateOf(0f) }
    var isHudDragging by remember { mutableStateOf(false) }

    // Free dragging offset with finger for the emergent modal
    var emergentDragOffsetX by remember(activeTab) { mutableFloatStateOf(0f) }
    var emergentDragOffsetY by remember(activeTab) { mutableFloatStateOf(0f) }
    var isEmergentDragging by remember { mutableStateOf(false) }

    fun resetHudPosition(isTargetInTop: Boolean = false) {
        coroutineScope.launch {
            val startX = hudDragOffsetX
            val startY = hudDragOffsetY
            if (startX != 0f || startY != 0f) {
                animate(0f, 1f, animationSpec = tween(240, easing = FastOutSlowInEasing)) { fraction, _ ->
                    hudDragOffsetX = startX * (1f - fraction)
                    hudDragOffsetY = startY * (1f - fraction)
                }
                hudDragOffsetX = 0f
                hudDragOffsetY = 0f
            } else {
                // Quick toggle to opposite half
                val targetY = if (isTargetInTop) -360f else 360f
                animate(0f, targetY, animationSpec = tween(240, easing = FastOutSlowInEasing)) { value, _ ->
                    hudDragOffsetY = value
                }
            }
        }
    }

    fun resetEmergentPosition() {
        coroutineScope.launch {
            val startX = emergentDragOffsetX
            val startY = emergentDragOffsetY
            if (startX != 0f || startY != 0f) {
                animate(0f, 1f, animationSpec = tween(240, easing = FastOutSlowInEasing)) { fraction, _ ->
                    emergentDragOffsetX = startX * (1f - fraction)
                    emergentDragOffsetY = startY * (1f - fraction)
                }
                emergentDragOffsetX = 0f
                emergentDragOffsetY = 0f
            }
        }
    }

    // Reset emergent position when opened or closed
    LaunchedEffect(isEmergentExpanded) {
        if (isEmergentExpanded) {
            emergentDragOffsetX = 0f
            emergentDragOffsetY = 0f
        }
    }

    // Quick peek transparency toggle to see what is underneath without moving
    var isHudTransparent by remember { mutableStateOf(false) }
    val hudAlpha = if (isHudTransparent) 0.38f else 0.98f

    val tabLabels = remember(isSpanish) {
        listOf(
            Pair(Icons.Filled.Folder, if (isSpanish) "Vocabulario" else "Vocabulary"),
            Pair(Icons.Filled.Quiz, if (isSpanish) "Práctica" else "Practice"),
            Pair(Icons.Filled.School, if (isSpanish) "Aprender" else "Learn"),
            Pair(Icons.Filled.AddCircle, if (isSpanish) "Crear" else "Create"),
            Pair(Icons.Filled.BarChart, if (isSpanish) "Progreso" else "Progress")
        )
    }

    LaunchedEffect(activeTab) {
        currentStepIndex = 0
        checkedSteps.clear()
        isFinished = false
    }

    val currentStep = steps.getOrElse(currentStepIndex) { steps.first() }

    // Cursor animations: bouncing hand/cursor and pulsing radar ring
    val infiniteTransition = rememberInfiniteTransition(label = "CursorAnimation")

    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -14f,
        animationSpec = infiniteRepeatable(
            animation = tween(520, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BounceOffset"
    )

    val radarScale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 2.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RadarScale"
    )

    val radarAlpha by infiniteTransition.animateFloat(
        initialValue = 0.65f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RadarAlpha"
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .testTag("interactive_cursor_walkthrough_overlay")
    ) {
        val screenWidthPx = constraints.maxWidth.toFloat()
        val screenHeightPx = constraints.maxHeight.toFloat()
        val density = LocalDensity.current

        // Calculate target rect with graceful default fallbacks based on the mode and activeTab
        val targetRect = remember(currentStepIndex, activeTab, isHubMode, screenWidthPx, screenHeightPx, targetBounds[currentStepIndex]) {
            targetBounds[currentStepIndex] ?: run {
                if (isHubMode) {
                    when (currentStepIndex) {
                        0 -> Rect(screenWidthPx * 0.04f, screenHeightPx * 0.20f, screenWidthPx * 0.48f, screenHeightPx * 0.46f)
                        1 -> Rect(screenWidthPx * 0.52f, screenHeightPx * 0.20f, screenWidthPx * 0.96f, screenHeightPx * 0.46f)
                        2 -> Rect(screenWidthPx * 0.04f, screenHeightPx * 0.48f, screenWidthPx * 0.48f, screenHeightPx * 0.74f)
                        3 -> Rect(screenWidthPx * 0.52f, screenHeightPx * 0.48f, screenWidthPx * 0.96f, screenHeightPx * 0.74f)
                        4 -> Rect(screenWidthPx * 0.42f, screenHeightPx * 0.015f, screenWidthPx * 0.98f, screenHeightPx * 0.085f)
                        else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                    }
                } else {
                    when (activeTab) {
                        // TAB 0: Vocabulario (5 partes bien definidas)
                        0 -> when (currentStepIndex) {
                            0 -> Rect(screenWidthPx * 0.02f, screenHeightPx * 0.012f, screenWidthPx * 0.98f, screenHeightPx * 0.080f) // 1. Barra Superior de Control
                            1 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.082f, screenWidthPx * 0.965f, screenHeightPx * 0.155f) // 2. Encabezado del Catálogo
                            2 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.158f, screenWidthPx * 0.965f, screenHeightPx * 0.300f) // 3. Pestañas, Buscador y Filtros
                            3 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.305f, screenWidthPx * 0.965f, screenHeightPx * 0.820f) // 4. Catálogo Central de Cuadernos
                            4 -> Rect(0f, screenHeightPx * 0.830f, screenWidthPx, screenHeightPx) // 5. Barra de Navegación Global
                            else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                        }
                        // TAB 1: Práctica (5 partes bien definidas)
                        1 -> when (currentStepIndex) {
                            0 -> Rect(screenWidthPx * 0.02f, screenHeightPx * 0.012f, screenWidthPx * 0.98f, screenHeightPx * 0.150f) // 1. Selector de Modalidad y Barra Superior
                            1 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.155f, screenWidthPx * 0.965f, screenHeightPx * 0.350f) // 2. Paso 1: Selección de Colección
                            2 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.355f, screenWidthPx * 0.965f, screenHeightPx * 0.550f) // 3. Paso 2: Selección de Lista
                            3 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.555f, screenWidthPx * 0.965f, screenHeightPx * 0.820f) // 4. Motores de Inicio Quiz
                            4 -> Rect(0f, screenHeightPx * 0.830f, screenWidthPx, screenHeightPx) // 5. Barra de Navegación Global
                            else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                        }
                        // TAB 2: Aprender (4 partes bien definidas: Barra Superior, Tarjeta Rectangular Completa, Botones Inferiores, Pestañas)
                        2 -> when (currentStepIndex) {
                            0 -> Rect(screenWidthPx * 0.02f, screenHeightPx * 0.012f, screenWidthPx * 0.98f, screenHeightPx * 0.155f) // 1. Barra Superior de Control y Enfoque
                            1 -> Rect(screenWidthPx * 0.04f, screenHeightPx * 0.165f, screenWidthPx * 0.96f, screenHeightPx * 0.770f) // 2. Tarjeta Central Rectangular (Área de Estudio Completa)
                            2 -> Rect(screenWidthPx * 0.03f, screenHeightPx * 0.780f, screenWidthPx * 0.97f, screenHeightPx * 0.875f) // 3. Barra de Acciones Inferiores (Repasar, Voltear, Lo sé)
                            3 -> Rect(0f, screenHeightPx * 0.880f, screenWidthPx, screenHeightPx) // 4. Barra de Navegación Global
                            else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                        }
                        // TAB 3: Crear (5 partes bien definidas)
                        3 -> when (currentStepIndex) {
                            0 -> Rect(screenWidthPx * 0.02f, screenHeightPx * 0.012f, screenWidthPx * 0.98f, screenHeightPx * 0.150f) // 1. Selector de Modos de Creación
                            1 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.155f, screenWidthPx * 0.965f, screenHeightPx * 0.290f) // 2. Palabras vs Tema y Dirección
                            2 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.295f, screenWidthPx * 0.965f, screenHeightPx * 0.520f) // 3. Entrada de Texto y Chips
                            3 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.525f, screenWidthPx * 0.965f, screenHeightPx * 0.820f) // 4. Botón Generar con IA
                            4 -> Rect(0f, screenHeightPx * 0.830f, screenWidthPx, screenHeightPx) // 5. Barra de Navegación Global
                            else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                        }
                        // TAB 4: Progreso (5 partes bien definidas)
                        4 -> when (currentStepIndex) {
                            0 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.080f, screenWidthPx * 0.965f, screenHeightPx * 0.260f) // 1. Tarjeta Principal de Avance
                            1 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.265f, screenWidthPx * 0.965f, screenHeightPx * 0.410f) // 2. Indicadores Clave (KPIs)
                            2 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.415f, screenWidthPx * 0.965f, screenHeightPx * 0.620f) // 3. Cronómetro y Alarma Diaria
                            3 -> Rect(screenWidthPx * 0.035f, screenHeightPx * 0.625f, screenWidthPx * 0.965f, screenHeightPx * 0.820f) // 4. Gráfica de Distribución de Estados
                            4 -> Rect(0f, screenHeightPx * 0.830f, screenWidthPx, screenHeightPx) // 5. Barra de Navegación Global
                            else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                        }
                        else -> Rect(screenWidthPx * 0.1f, screenHeightPx * 0.2f, screenWidthPx * 0.9f, screenHeightPx * 0.5f)
                    }
                }
            }
        }

        val targetCenter = remember(targetRect) {
            Offset(
                x = (targetRect.left + targetRect.right) / 2f,
                y = (targetRect.top + targetRect.bottom) / 2f
            )
        }
        val isTargetInTopHalf = targetCenter.y < screenHeightPx * 0.50f

        val cornerRadiusPx = with(density) { 24.dp.toPx() }
        val inflatePx = with(density) { 10.dp.toPx() }
        val cutoutPath = remember(targetRect, cornerRadiusPx, inflatePx) {
            Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = targetRect.inflate(inflatePx),
                        cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
                    )
                )
            }
        }

        // Semi-transparent scrim cutout canvas with rounded spotlight on the target
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    // Clicking outside does not dismiss, prompts user to tap the target
                }
        ) {
            clipPath(cutoutPath, clipOp = ClipOp.Difference) {
                drawRect(Color.Black.copy(alpha = 0.72f))
            }
        }

        // Glowing border framing the active target
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        (targetRect.left - inflatePx).roundToInt(),
                        (targetRect.top - inflatePx).roundToInt()
                    )
                }
                .size(
                    width = with(density) { (targetRect.width + inflatePx * 2f).toDp() },
                    height = with(density) { (targetRect.height + inflatePx * 2f).toDp() }
                )
                .clip(RoundedCornerShape(24.dp))
                .border(
                    width = 2.5.dp,
                    color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    isCurrentPointChecked = true
                    checkedSteps[currentStepIndex] = true
                    isEmergentExpanded = true
                }
                .testTag("cursor_target_touch_area_${currentStepIndex + 1}")
        )

        // =================================================================
        // DYNAMIC POSITIONING CALCULATIONS FOR UNIFIED TARGET POINTER & BADGE
        // Completely eliminates duplicate overlapping badges and text truncation!
        // =================================================================
        val indicatorEstWidthDp = 260.dp
        val indicatorEstHeightDp = 78.dp
        val indicatorWidthPx = with(density) { indicatorEstWidthDp.toPx() }
        val indicatorHeightPx = with(density) { indicatorEstHeightDp.toPx() }

        // Horizontal positioning: center on targetCenter.x, safely clamped within screen boundaries
        val minX = 10f * density.density
        val maxX = (screenWidthPx - indicatorWidthPx - 10f * density.density).coerceAtLeast(minX)
        val cursorX = (targetCenter.x - indicatorWidthPx / 2f).coerceIn(minX, maxX)

        // Decide pointing direction based on target position:
        // When target is in top half, indicator sits below target pointing UP 👆
        // When target is in bottom half, indicator sits above target pointing DOWN 👇
        val pointsUp = isTargetInTopHalf

        val staticBaseY = if (pointsUp) {
            targetRect.bottom + inflatePx + 6f * density.density
        } else {
            targetRect.top - inflatePx - indicatorHeightPx - 6f * density.density
        }

        // Clamp Y safely within screen viewport
        val minY = 10f * density.density
        val maxY = (screenHeightPx - indicatorHeightPx - 10f * density.density).coerceAtLeast(minY)

        // =================================================================
        // UNIFIED TARGET CURSOR & IDENTIFICATION BADGE (Zero Overlap Guaranteed)
        // =================================================================
        Box(
            modifier = Modifier
                .offset {
                    val bouncePx = if (!isCurrentPointChecked) bounceOffset * density.density else 0f
                    val animatedY = if (pointsUp) {
                        (staticBaseY - bouncePx).coerceIn(minY, maxY)
                    } else {
                        (staticBaseY + bouncePx).coerceIn(minY, maxY)
                    }
                    IntOffset(cursorX.roundToInt(), animatedY.roundToInt())
                }
                .width(indicatorEstWidthDp)
                .wrapContentHeight()
                .clickable {
                    isCurrentPointChecked = true
                    checkedSteps[currentStepIndex] = true
                    isEmergentExpanded = true
                }
                .testTag("animated_cursor_indicator"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // If pointing UP: Top element is the pointing hand/checkmark, bottom element is the label pill
                if (pointsUp) {
                    if (!isCurrentPointChecked) {
                        // Pulsing radar + Hand pointing UP 👆
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .graphicsLayer {
                                        scaleX = radarScale
                                        scaleY = radarScale
                                        alpha = radarAlpha * 0.5f
                                    }
                                    .clip(CircleShape)
                                    .background(currentStep.accentColor)
                            )
                            Surface(
                                shape = CircleShape,
                                color = Color.White,
                                shadowElevation = 6.dp,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Filled.TouchApp,
                                        contentDescription = "Toca aquí",
                                        tint = currentStep.accentColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    } else {
                        // Checked Green Circle (✔)
                        Surface(
                            shape = CircleShape,
                            color = EmeraldGreen,
                            shadowElevation = 6.dp,
                            border = BorderStroke(1.5.dp, Color.White),
                            modifier = Modifier.size(30.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Chequeado",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    // Unified Label & Action Pill
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                        shadowElevation = 8.dp,
                        border = BorderStroke(1.2.dp, Color.White),
                        modifier = Modifier
                            .testTag("floating_target_focus_badge")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (isCurrentPointChecked) "✅" else "👆",
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Column {
                                Text(
                                    text = if (isSpanish) {
                                        if (isCurrentPointChecked) "${currentStep.stepNumberTextEs}: ¡CHEQUEADO!" else "${currentStep.stepNumberTextEs}: ¡TOCA AQUÍ!"
                                    } else {
                                        if (isCurrentPointChecked) "${currentStep.stepNumberTextEn}: CHECKED!" else "${currentStep.stepNumberTextEn}: TAP HERE!"
                                    },
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                                Text(
                                    text = if (isSpanish) currentStep.targetNameEs else currentStep.targetNameEn,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White.copy(alpha = 0.92f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "⛶",
                                fontSize = 10.sp,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }
                } else {
                    // If pointing DOWN: Top element is the label pill, bottom element is the pointing hand/checkmark
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                        shadowElevation = 8.dp,
                        border = BorderStroke(1.2.dp, Color.White),
                        modifier = Modifier
                            .testTag("floating_target_focus_badge")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (isCurrentPointChecked) "✅" else "👇",
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Column {
                                Text(
                                    text = if (isSpanish) {
                                        if (isCurrentPointChecked) "${currentStep.stepNumberTextEs}: ¡CHEQUEADO!" else "${currentStep.stepNumberTextEs}: ¡TOCA AQUÍ!"
                                    } else {
                                        if (isCurrentPointChecked) "${currentStep.stepNumberTextEn}: CHECKED!" else "${currentStep.stepNumberTextEn}: TAP HERE!"
                                    },
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                                Text(
                                    text = if (isSpanish) currentStep.targetNameEs else currentStep.targetNameEn,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White.copy(alpha = 0.92f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "⛶",
                                fontSize = 10.sp,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    if (!isCurrentPointChecked) {
                        // Pulsing radar + Hand pointing DOWN 👇
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .graphicsLayer {
                                        scaleX = radarScale
                                        scaleY = radarScale
                                        alpha = radarAlpha * 0.5f
                                    }
                                    .clip(CircleShape)
                                    .background(currentStep.accentColor)
                            )
                            Surface(
                                shape = CircleShape,
                                color = Color.White,
                                shadowElevation = 6.dp,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Filled.TouchApp,
                                        contentDescription = "Toca aquí",
                                        tint = currentStep.accentColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    } else {
                        // Checked Green Circle (✔)
                        Surface(
                            shape = CircleShape,
                            color = EmeraldGreen,
                            shadowElevation = 6.dp,
                            border = BorderStroke(1.5.dp, Color.White),
                            modifier = Modifier.size(30.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Chequeado",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Top controls moved into the unified non-overlapping HUD panel below

        // =================================================================
        // UNIFIED TUTORIAL HUD PANEL
        // Docks dynamically to BottomCenter if target is in top half,
        // or to TopCenter if target is in bottom half.
        // GUARANTEES ZERO OVERLAP WITH TARGET ELEMENT & CURSOR!
        // =================================================================
        AnimatedVisibility(
            visible = !isFinished && !isEmergentExpanded,
            enter = fadeIn(tween(200)),
            exit = fadeOut(tween(150)),
            modifier = Modifier
                .align(if (isTargetInTopHalf) Alignment.BottomCenter else Alignment.TopCenter)
                .offset { IntOffset(hudDragOffsetX.roundToInt(), hudDragOffsetY.roundToInt()) }
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = if (isTargetInTopHalf) 0.dp else 24.dp,
                    bottom = if (isTargetInTopHalf) 16.dp else 0.dp
                )
                .fillMaxWidth()
        ) {
            Surface(
                shape = RoundedCornerShape(22.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = hudAlpha),
                shadowElevation = 14.dp,
                border = BorderStroke(1.5.dp, currentStep.accentColor.copy(alpha = if (isHudTransparent) 0.4f else 0.65f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("walkthrough_unified_panel")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    // DRAG HANDLE BAR & PEEK CONTROLS ("Mover con el dedo para ver lo que hay abajo")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left: Quick peek transparency toggle to see what is underneath
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isHudTransparent) PrimaryIndigo.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                            border = BorderStroke(1.dp, if (isHudTransparent) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { isHudTransparent = !isHudTransparent }
                                .testTag("walkthrough_peek_toggle")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Icon(
                                    imageVector = if (isHudTransparent) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Ver fondo",
                                    tint = if (isHudTransparent) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = if (isSpanish) (if (isHudTransparent) "Opaco" else "Ver abajo 👁️") else (if (isHudTransparent) "Solid" else "Peek 👁️"),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = if (isHudTransparent) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Center: Draggable pill handle with touch feedback (Drag freely with finger)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isHudDragging) currentStep.accentColor else currentStep.accentColor.copy(alpha = 0.16f),
                            border = BorderStroke(if (isHudDragging) 1.5.dp else 1.dp, currentStep.accentColor),
                            shadowElevation = if (isHudDragging) 6.dp else 0.dp,
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = { isHudDragging = true },
                                        onDragEnd = { isHudDragging = false },
                                        onDragCancel = { isHudDragging = false },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            hudDragOffsetX = (hudDragOffsetX + dragAmount.x).coerceIn(-380f, 380f)
                                            hudDragOffsetY = (hudDragOffsetY + dragAmount.y).coerceIn(-700f, 700f)
                                        }
                                    )
                                }
                                .testTag("walkthrough_drag_handle")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TouchApp,
                                    contentDescription = "Arrastrar ventana",
                                    tint = if (isHudDragging) Color.White else currentStep.accentColor,
                                    modifier = Modifier.size(13.dp)
                                )
                                Text(
                                    text = if (isSpanish) {
                                        if (isHudDragging) "Moviendo... ⠿" else "Mover ⠿"
                                    } else {
                                        if (isHudDragging) "Moving... ⠿" else "Move ⠿"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = if (isHudDragging) Color.White else currentStep.accentColor
                                )
                            }
                        }

                        // Right: Reset position button or quick flip dock
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) currentStep.accentColor.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                            border = BorderStroke(1.dp, if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) currentStep.accentColor.copy(alpha = 0.6f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { resetHudPosition(isTargetInTopHalf) }
                                .testTag("walkthrough_reset_position")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Icon(
                                    imageVector = if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) Icons.Default.RestartAlt else Icons.Default.SwipeVertical,
                                    contentDescription = "Posición",
                                    tint = if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) currentStep.accentColor else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) (if (isSpanish) "Centrar ⟲" else "Reset ⟲") else (if (isSpanish) "Alternar ↕️" else "Flip ↕️"),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = if (hudDragOffsetX != 0f || hudDragOffsetY != 0f) currentStep.accentColor else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // 1. TOP HEADER: Title, Step badge & Close Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = { isHudDragging = true },
                                        onDragEnd = { isHudDragging = false },
                                        onDragCancel = { isHudDragging = false },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            hudDragOffsetX = (hudDragOffsetX + dragAmount.x).coerceIn(-380f, 380f)
                                            hudDragOffsetY = (hudDragOffsetY + dragAmount.y).coerceIn(-700f, 700f)
                                        }
                                    )
                                }
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = currentStep.accentColor.copy(alpha = 0.15f),
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = currentStep.icon,
                                        contentDescription = null,
                                        tint = currentStep.accentColor,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f, fill = false)) {
                                Text(
                                    text = if (isSpanish) "Tutorial Interactivo" else "Interactive Tutorial",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                                Text(
                                    text = if (isSpanish) {
                                        "Paso ${currentStepIndex + 1} de ${steps.size}: ${if (isCurrentPointChecked) "¡Chequeado!" else "Toca para explorar"}"
                                    } else {
                                        "Step ${currentStepIndex + 1} of ${steps.size}: ${if (isCurrentPointChecked) "Checked!" else "Tap to explore"}"
                                    },
                                    fontSize = 10.5.sp,
                                    color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                            }
                        }

                        // Action buttons: Escalar (Emergente y más grande) + Cerrar
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // "Escalar de clic, se pone emergente y más grande"
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = currentStep.accentColor.copy(alpha = 0.18f),
                                border = BorderStroke(1.2.dp, currentStep.accentColor.copy(alpha = 0.65f)),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { isEmergentExpanded = true }
                                    .testTag("walkthrough_scale_button")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.OpenInFull,
                                        contentDescription = if (isSpanish) "Escalar de clic" else "Scale on click",
                                        tint = currentStep.accentColor,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = if (isSpanish) "Escalar ⛶" else "Scale ⛶",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = currentStep.accentColor
                                    )
                                }
                            }

                            // Close / Skip button
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { onDismiss() }
                                    .testTag("walkthrough_skip_button")
                            ) {
                                Text(
                                    text = if (isSpanish) "Cerrar ✕" else "Skip ✕",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    // 2. TAB SWITCHER CHIPS (In-app mode)
                    if (!isHubMode) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            tabLabels.forEachIndexed { index, pair ->
                                val isSelected = activeTab == index
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelected) currentStep.accentColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    border = BorderStroke(1.dp, if (isSelected) Color.White else Color.Transparent),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { onTabSelected(index) }
                                        .testTag("walkthrough_tab_chip_$index")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = pair.first,
                                            contentDescription = null,
                                            tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = pair.second,
                                            fontSize = 10.5.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 3. STEP PROGRESS PILLS (1..N)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        steps.forEachIndexed { index, step ->
                            val isCurrent = index == currentStepIndex
                            val isChecked = checkedSteps[index] == true

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = when {
                                    isCurrent -> step.accentColor
                                    isChecked -> EmeraldGreen.copy(alpha = 0.85f)
                                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                },
                                border = BorderStroke(1.dp, if (isCurrent) Color.White else Color.Transparent),
                                modifier = Modifier
                                    .height(24.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        currentStepIndex = index
                                        isCurrentPointChecked = checkedSteps[index] == true
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(horizontal = 7.dp)
                                ) {
                                    Text(
                                        text = if (isChecked) "${index + 1} ✓" else "${index + 1}",
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCurrent || isChecked) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 4. ACTION CONTENT AREA: Persistent clear explanation with interactive explore indicator
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Header row: Target badge + Explore status (Always horizontal, never squished vertically)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = currentStep.accentColor.copy(alpha = 0.15f),
                                border = BorderStroke(1.dp, currentStep.accentColor.copy(alpha = 0.45f)),
                                modifier = Modifier.weight(1f, fill = false)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(text = "🎯", fontSize = 11.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) currentStep.targetNameEs else currentStep.targetNameEn,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = currentStep.accentColor,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        softWrap = false
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isCurrentPointChecked) EmeraldGreen.copy(alpha = 0.15f) else currentStep.accentColor.copy(alpha = 0.09f),
                                border = BorderStroke(1.dp, if (isCurrentPointChecked) EmeraldGreen.copy(alpha = 0.5f) else currentStep.accentColor.copy(alpha = 0.35f)),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        isCurrentPointChecked = !isCurrentPointChecked
                                        checkedSteps[currentStepIndex] = isCurrentPointChecked
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = if (isCurrentPointChecked) "✅" else (if (isTargetInTopHalf) "👆" else "👇"),
                                        fontSize = 11.sp
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) {
                                            if (isCurrentPointChecked) "¡Explorado!" else "Explorar"
                                        } else {
                                            if (isCurrentPointChecked) "Explored!" else "Explore"
                                        },
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        softWrap = false
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Title
                        Text(
                            text = if (isSpanish) currentStep.titleEs else currentStep.titleEn,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        // What is it for
                        Text(
                            text = if (isSpanish) currentStep.whatIsItForEs else currentStep.whatIsItForEn,
                            fontSize = 11.5.sp,
                            lineHeight = 15.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Bullets (Clickable to open emergent enlarged view!)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { isEmergentExpanded = true }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .heightIn(max = 160.dp)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                val bullets = if (isSpanish) currentStep.bulletPointsEs else currentStep.bulletPointsEn
                                bullets.forEach { bullet ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(
                                            text = "•",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = currentStep.accentColor,
                                            modifier = Modifier.padding(end = 5.dp)
                                        )
                                        Text(
                                            text = bullet,
                                            fontSize = 11.sp,
                                            lineHeight = 14.5.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        // Hint banner: Toca para Escalar (más grande y emergente)
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = currentStep.accentColor.copy(alpha = 0.08f),
                            border = BorderStroke(1.dp, currentStep.accentColor.copy(alpha = 0.25f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { isEmergentExpanded = true }
                                .padding(vertical = 3.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "🔍", fontSize = 10.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) "Toca 'Escalar' o aquí para abrir emergente" else "Tap 'Scale' or here to pop up enlarged",
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Text(
                                    text = if (isSpanish) "Más grande ➔" else "Enlarge ➔",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = currentStep.accentColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Nav actions: Anterior & Siguiente (always available and prominent)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (currentStepIndex > 0) {
                                OutlinedButton(
                                    onClick = {
                                        currentStepIndex--
                                        isCurrentPointChecked = checkedSteps[currentStepIndex] == true
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(38.dp)
                                        .testTag("walkthrough_prev_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = if (isSpanish) "Anterior" else "Back",
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            val isLastStep = currentStepIndex == steps.size - 1

                            Button(
                                onClick = {
                                    if (isLastStep) {
                                        isFinished = true
                                    } else {
                                        currentStepIndex++
                                        isCurrentPointChecked = checkedSteps[currentStepIndex] == true
                                    }
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = currentStep.accentColor,
                                    contentColor = Color.White
                                ),
                                contentPadding = PaddingValues(horizontal = 10.dp),
                                modifier = Modifier
                                    .weight(if (currentStepIndex > 0) 1.6f else 1f)
                                    .height(38.dp)
                                    .testTag("walkthrough_next_button")
                            ) {
                                Text(
                                    text = if (isLastStep) {
                                        if (isSpanish) "¡Finalizar! 🎉" else "Finish! 🎉"
                                    } else {
                                        val nextPart = if (isSpanish) steps[currentStepIndex + 1].stepNumberTextEs else steps[currentStepIndex + 1].stepNumberTextEn
                                        if (isSpanish) "Siguiente ($nextPart) ➔" else "Next ($nextPart) ➔"
                                    },
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                                if (!isLastStep) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // =================================================================
        // EMERGENT ENLARGED MODAL (Ventana Emergente Ampliada)
        // Se activa cuando el usuario presiona "Escalar" de clic.
        // Presenta cada cuadro de la ayuda de forma emergente, centrada,
        // con tipografía grande, controles de zoom interactivo (1x, 1.25x, 1.5x),
        // pestañas, viñetas completas y botones de navegación.
        // =================================================================
        AnimatedVisibility(
            visible = !isFinished && isEmergentExpanded,
            enter = fadeIn(tween(200)) + scaleIn(tween(220), initialScale = 0.88f),
            exit = fadeOut(tween(160)) + scaleOut(tween(180), targetScale = 0.88f),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.72f))
                    .clickable { isEmergentExpanded = false },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(26.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = if (isEmergentDragging) 32.dp else 24.dp,
                    border = BorderStroke(if (isEmergentDragging) 2.5.dp else 2.dp, currentStep.accentColor),
                    modifier = Modifier
                        .offset { IntOffset(emergentDragOffsetX.roundToInt(), emergentDragOffsetY.roundToInt()) }
                        .padding(horizontal = 12.dp, vertical = 18.dp)
                        .fillMaxWidth()
                        .widthIn(max = 560.dp)
                        .wrapContentHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {} // Prevent click-through to backdrop scrim
                        )
                        .testTag("walkthrough_emergent_modal")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Emergent Top Drag & Position Control Bar (Adaptive layout that never breaks into vertical text)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Center/Left: Draggable pill handle with touch feedback
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isEmergentDragging) currentStep.accentColor else currentStep.accentColor.copy(alpha = 0.16f),
                                border = BorderStroke(if (isEmergentDragging) 1.5.dp else 1.dp, currentStep.accentColor),
                                shadowElevation = if (isEmergentDragging) 6.dp else 0.dp,
                                modifier = Modifier
                                    .weight(1f, fill = false)
                                    .pointerInput(Unit) {
                                        detectDragGestures(
                                            onDragStart = { isEmergentDragging = true },
                                            onDragEnd = { isEmergentDragging = false },
                                            onDragCancel = { isEmergentDragging = false },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                emergentDragOffsetX = (emergentDragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                                emergentDragOffsetY = (emergentDragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                            }
                                        )
                                    }
                                    .testTag("emergent_drag_handle")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.TouchApp,
                                        contentDescription = "Mover con el dedo",
                                        tint = if (isEmergentDragging) Color.White else currentStep.accentColor,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = if (isSpanish) {
                                            if (isEmergentDragging) "Moviendo... ⠿" else "Arrastra para mover ⠿"
                                        } else {
                                            if (isEmergentDragging) "Moving... ⠿" else "Drag to move ⠿"
                                        },
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis,
                                        color = if (isEmergentDragging) Color.White else currentStep.accentColor
                                    )
                                }
                            }

                            // Right: Centrar (Reset) button - appears dynamically only when moved, preventing cramped layout
                            AnimatedVisibility(
                                visible = emergentDragOffsetX != 0f || emergentDragOffsetY != 0f,
                                enter = fadeIn() + expandHorizontally(),
                                exit = fadeOut() + shrinkHorizontally()
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = currentStep.accentColor.copy(alpha = 0.18f),
                                    border = BorderStroke(1.dp, currentStep.accentColor.copy(alpha = 0.6f)),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { resetEmergentPosition() }
                                        .testTag("walkthrough_emergent_recenter_button")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.RestartAlt,
                                            contentDescription = "Centrar",
                                            tint = currentStep.accentColor,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Text(
                                            text = if (isSpanish) "Centrar ⟲" else "Center ⟲",
                                            fontSize = 10.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            softWrap = false,
                                            color = currentStep.accentColor
                                        )
                                    }
                                }
                            }
                        }

                        // 1. Emergent Header: Clean horizontal layout preventing vertical text wrapping
                        // Row A: Main Identity & Title (Left) + Close Button (Right)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .pointerInput(Unit) {
                                        detectDragGestures(
                                            onDragStart = { isEmergentDragging = true },
                                            onDragEnd = { isEmergentDragging = false },
                                            onDragCancel = { isEmergentDragging = false },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                emergentDragOffsetX = (emergentDragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                                emergentDragOffsetY = (emergentDragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                            }
                                        )
                                    }
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = currentStep.accentColor.copy(alpha = 0.18f),
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = currentStep.icon,
                                            contentDescription = null,
                                            tint = currentStep.accentColor,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = if (isSpanish) {
                                        if (isHubMode) "Guía Rápida de Inicio" else "Tutorial con Cursor"
                                    } else {
                                        if (isHubMode) "Quick Hub Guide" else "Cursor Tutorial"
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Close entire walkthrough
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .clickable { onDismiss() }
                                    .testTag("walkthrough_emergent_close_button")
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "✕",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Row B: Horizontal Format for Badges (EMERGENTE + PARTE X DE Y) & Controls (Scale + Reduce)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Left: Badges strictly horizontal with generous padding and no vertical wrapping
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = currentStep.accentColor
                                ) {
                                    Text(
                                        text = if (isSpanish) "EMERGENTE" else "POPUP",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                                        maxLines = 1,
                                        softWrap = false
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = currentStep.accentColor.copy(alpha = 0.16f),
                                    border = BorderStroke(1.dp, currentStep.accentColor.copy(alpha = 0.5f))
                                ) {
                                    Text(
                                        text = if (isSpanish) currentStep.stepNumberTextEs else currentStep.stepNumberTextEn,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = currentStep.accentColor,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                        maxLines = 1,
                                        softWrap = false
                                    )
                                }
                            }

                            // Right: Scale control cycler + Reduce button
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // "Escalar de clic" toggle: cycles 1.0x -> 1.25x -> 1.5x -> 1.0x
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = currentStep.accentColor.copy(alpha = 0.16f),
                                    border = BorderStroke(1.2.dp, currentStep.accentColor.copy(alpha = 0.6f)),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            scaleLevel = (scaleLevel + 1) % 3
                                        }
                                        .testTag("walkthrough_scale_click_toggle")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ZoomIn,
                                            contentDescription = if (isSpanish) "Escalar tamaño" else "Scale size",
                                            tint = currentStep.accentColor,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = when (scaleLevel) {
                                                0 -> "1.0x"
                                                1 -> "1.25x 🔍"
                                                else -> "1.5x 🔎"
                                            },
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = currentStep.accentColor,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }

                                // Reducir button: restores docked view
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { isEmergentExpanded = false }
                                        .testTag("walkthrough_emergent_reduce_button")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CloseFullscreen,
                                            contentDescription = if (isSpanish) "Reducir" else "Reduce",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = if (isSpanish) "Reducir" else "Reduce",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }
                            }
                        }

                        // 2. Tab Switcher (if in-app mode)
                        if (!isHubMode) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                tabLabels.forEachIndexed { index, pair ->
                                    val isSelected = activeTab == index
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isSelected) currentStep.accentColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        border = BorderStroke(1.dp, if (isSelected) Color.White else Color.Transparent),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable { onTabSelected(index) }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Icon(
                                                imageVector = pair.first,
                                                contentDescription = null,
                                                tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = pair.second,
                                                fontSize = 10.5.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 3. Step Progress Pills (1..N)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            steps.forEachIndexed { index, step ->
                                val isCurrent = index == currentStepIndex
                                val isChecked = checkedSteps[index] == true

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = when {
                                        isCurrent -> step.accentColor
                                        isChecked -> EmeraldGreen.copy(alpha = 0.85f)
                                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                    },
                                    border = BorderStroke(1.dp, if (isCurrent) Color.White else Color.Transparent),
                                    modifier = Modifier
                                        .height(26.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            currentStepIndex = index
                                            isCurrentPointChecked = checkedSteps[index] == true
                                        }
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = if (isChecked) "${index + 1} ✓" else "${index + 1}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isCurrent || isChecked) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // 4. Target Highlight Row + Checkbox
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = currentStep.accentColor.copy(alpha = 0.15f),
                                border = BorderStroke(1.2.dp, currentStep.accentColor.copy(alpha = 0.55f)),
                                modifier = Modifier.weight(1f, fill = false)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
                                ) {
                                    Text(text = "🎯", fontSize = (12 * textScale).sp)
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = if (isSpanish) currentStep.targetNameEs else currentStep.targetNameEn,
                                        fontSize = (12 * textScale).sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = currentStep.accentColor,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        softWrap = false
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isCurrentPointChecked) EmeraldGreen.copy(alpha = 0.15f) else currentStep.accentColor.copy(alpha = 0.09f),
                                border = BorderStroke(1.2.dp, if (isCurrentPointChecked) EmeraldGreen.copy(alpha = 0.6f) else currentStep.accentColor.copy(alpha = 0.4f)),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        isCurrentPointChecked = !isCurrentPointChecked
                                        checkedSteps[currentStepIndex] = isCurrentPointChecked
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
                                ) {
                                    Text(
                                        text = if (isCurrentPointChecked) "✅" else "☑️",
                                        fontSize = (12 * textScale).sp
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) {
                                            if (isCurrentPointChecked) "¡Entendido!" else "Marcar visto"
                                        } else {
                                            if (isCurrentPointChecked) "Got it!" else "Mark seen"
                                        },
                                        fontSize = (11.5 * textScale).sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCurrentPointChecked) EmeraldGreen else currentStep.accentColor,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        softWrap = false
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // 5. Title & Explanation (Grande)
                        Text(
                            text = if (isSpanish) currentStep.titleEs else currentStep.titleEn,
                            fontSize = (16.5 * textScale).sp,
                            lineHeight = (21 * textScale).sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = if (isSpanish) currentStep.whatIsItForEs else currentStep.whatIsItForEn,
                            fontSize = (13 * textScale).sp,
                            lineHeight = (17.5 * textScale).sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // 6. Detailed Bullet Points (Grande y espaciado)
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .heightIn(max = (230 * textScale).dp)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val bullets = if (isSpanish) currentStep.bulletPointsEs else currentStep.bulletPointsEn
                                bullets.forEach { bullet ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(
                                            text = "•",
                                            fontSize = (14 * textScale).sp,
                                            fontWeight = FontWeight.Black,
                                            color = currentStep.accentColor,
                                            modifier = Modifier.padding(end = 8.dp)
                                        )
                                        Text(
                                            text = bullet,
                                            fontSize = (13 * textScale).sp,
                                            lineHeight = (18 * textScale).sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // 7. Navigation Actions: Anterior & Siguiente
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (currentStepIndex > 0) {
                                OutlinedButton(
                                    onClick = {
                                        currentStepIndex--
                                        isCurrentPointChecked = checkedSteps[currentStepIndex] == true
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("walkthrough_emergent_prev_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) "Anterior" else "Back",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            val isLastStep = currentStepIndex == steps.size - 1

                            Button(
                                onClick = {
                                    if (isLastStep) {
                                        isEmergentExpanded = false
                                        isFinished = true
                                    } else {
                                        currentStepIndex++
                                        isCurrentPointChecked = checkedSteps[currentStepIndex] == true
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = currentStep.accentColor,
                                    contentColor = Color.White
                                ),
                                contentPadding = PaddingValues(horizontal = 12.dp),
                                modifier = Modifier
                                    .weight(if (currentStepIndex > 0) 1.6f else 1f)
                                    .height(44.dp)
                                    .testTag("walkthrough_emergent_next_button")
                            ) {
                                Text(
                                    text = if (isLastStep) {
                                        if (isSpanish) "¡Finalizar! 🎉" else "Finish! 🎉"
                                    } else {
                                        val nextPart = if (isSpanish) steps[currentStepIndex + 1].stepNumberTextEs else steps[currentStepIndex + 1].stepNumberTextEn
                                        if (isSpanish) "Siguiente ($nextPart) ➔" else "Next ($nextPart) ➔"
                                    },
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = false
                                )
                                if (!isLastStep) {
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        // =================================================================
        AnimatedVisibility(
            visible = isFinished,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut(),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(20.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(26.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 20.dp,
                border = BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .testTag("walkthrough_finish_dialog")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = EmeraldGreen.copy(alpha = 0.18f),
                        border = BorderStroke(1.5.dp, EmeraldGreen.copy(alpha = 0.45f)),
                        modifier = Modifier.size(68.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🎉", fontSize = 34.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = if (isSpanish) "¡Tutorial Completado!" else "Walkthrough Completed!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isSpanish) {
                            "Has chequeado las ${steps.size} partes principales con el cursor. Ya estás listo para aprovechar al máximo EnglishSwipe."
                        } else {
                            "You have checked all ${steps.size} main sections with the cursor. You are now ready to make the most of EnglishSwipe."
                        },
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Checklist summary
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            steps.forEach { step ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "✅", fontSize = 12.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isSpanish) step.titleEs else step.titleEn,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryIndigo,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("walkthrough_start_learning_button")
                    ) {
                        Text(
                            text = if (isSpanish) {
                                if (isHubMode) "¡Empezar a Aprender Ahora! 🚀" else if (activeTab == 2) "Comenzar a Estudiar 🚀" else "¡Comenzar a Explorar la App! 🚀"
                            } else {
                                if (isHubMode) "Start Learning Now! 🚀" else if (activeTab == 2) "Start Studying 🚀" else "Start Exploring the App! 🚀"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (onOpenInAppTutorial != null && isHubMode) {
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = {
                                onDismiss()
                                onOpenInAppTutorial()
                            },
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.5.dp, PrimaryIndigo),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("walkthrough_open_in_app_tutorial_button")
                        ) {
                            Text(
                                text = if (isSpanish) "Ver Tutorial de Menús dentro de la App 📱" else "View In-App Menus Tutorial 📱",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryIndigo
                            )
                        }
                    }
                }
            }
        }
    }
}
