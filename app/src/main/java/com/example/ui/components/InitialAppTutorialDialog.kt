package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import kotlinx.coroutines.launch

/**
 * Data representation for each educational step in the Initial Tutorial.
 */
data class InitialTutorialStep(
    val stepIndex: Int,
    val tagEs: String,
    val tagEn: String,
    val titleEs: String,
    val titleEn: String,
    val subtitleEs: String,
    val subtitleEn: String,
    val primaryIcon: ImageVector,
    val accentColor: Color,
    val bulletPointsEs: List<Pair<String, String>>, // (Bold title, explanation)
    val bulletPointsEn: List<Pair<String, String>>,
    val highlightTipEs: String? = null,
    val highlightTipEn: String? = null
)

val INITIAL_TUTORIAL_STEPS_LEARN_ENGLISH: List<InitialTutorialStep> = listOf(
    // STEP 1: Colecciones y Listas (Nivel Básico para Principiantes)
    InitialTutorialStep(
        stepIndex = 0,
        tagEs = "PASO 1 DE 6 • COLECCIONES Y LISTAS",
        tagEn = "STEP 1 OF 6 • COLLECTIONS & LISTS",
        titleEs = "Elige tu Colección y Lista",
        titleEn = "Choose Your Collection & List",
        subtitleEs = "Aprende el vocabulario que más se apegue a tu nivel",
        subtitleEn = "Learn the vocabulary tailored to your exact pace",
        primaryIcon = Icons.Filled.CollectionsBookmark,
        accentColor = StarAmber,
        bulletPointsEs = listOf(
            "Colección y Lista a tu medida" to "Para aprender, simplemente elige una colección y, de ella, la lista que más se apegue a tu objetivo de aprendizaje.",
            "La Básica es para Principiantes" to "Si estás empezando, la colección Básica (Basic 1) está diseñada especialmente para principiantes con palabras cotidianas, pronunciación y contexto.",
            "Ruta Progresiva Continua" to "A medida que avances, continuarás con las siguientes colecciones: Basic 2, Basic 3, y los niveles subsiguientes (A1 a C2) paso a paso."
        ),
        bulletPointsEn = listOf(
            "Tailored Collection & List" to "To learn, simply select a collection, and within it, choose the study list that best fits your goals.",
            "Basics for Beginners" to "If you are starting out, the Basic 1 collection is specifically crafted for beginners with essential everyday words, phonetics, and context.",
            "Progressive Learning Path" to "As you progress, advance naturally to the next collections: Basic 2, Basic 3, and subsequent levels (A1 to C2)."
        ),
        highlightTipEs = "💡 Tip inicial: Comienza con Basic 1 si deseas construir una base sólida de vocabulario en inglés.",
        highlightTipEn = "💡 Quick tip: Start with Basic 1 if you want to build a solid foundation of English vocabulary."
    ),

    // STEP 2: Dinámica de Estudio (Deslizar ↑ y Dominio 100% con 4 aciertos →)
    InitialTutorialStep(
        stepIndex = 1,
        tagEs = "PASO 2 DE 6 • DINÁMICA DE APRENDIZAJE",
        tagEn = "STEP 2 OF 6 • STUDY DYNAMICS",
        titleEs = "Desliza y Domina al 100%",
        titleEn = "Swipe Up & Master at 100%",
        subtitleEs = "Desliza ↑ para pasar y → para dominar con 4 aciertos",
        subtitleEn = "Swipe ↑ to advance and → to master with 4 reviews",
        primaryIcon = Icons.Filled.SwipeVertical,
        accentColor = PrimaryIndigo,
        bulletPointsEs = listOf(
            "Desliza hacia arriba (↑)" to "Al pulsar en tu lista, entra a la ventana para aprender. Comienza a deslizar hacia arriba para ver tarjeta por tarjeta con audio y fonética.",
            "Toca la tarjeta" to "Toca la tarjeta para voltearla al instante y ver oraciones de ejemplo y explicaciones gramaticales.",
            "Desliza a la derecha (→)" to "Cuando comprendas el vocabulario, simplemente desliza la tarjeta a la derecha (o pulsa Dominada).",
            "¡100% Dominado con 4 aciertos!" to "Si lo haces 4 veces con una palabra, ¡el vocabulario queda automáticamente 100% DOMINADO!",
            "Siguiente lista automática" to "Cuando tu lista esté al 100% dominada, el sistema te invita a seguir con la otra lista automáticamente hasta completar la colección."
        ),
        bulletPointsEn = listOf(
            "Swipe Up (↑) to Learn" to "Tap any list to enter the Learn screen. Swipe up to progress smoothly through words with audio, phonetic guides, and examples.",
            "Tap to Flip" to "Tap any card to instantly flip it and see real-life sentence examples and grammar details.",
            "Swipe Right (→) When Known" to "When you understand the word, simply swipe the card to the right (or tap Mastered).",
            "100% Mastered with 4 Swipes!" to "Once swiped right 4 times across spaced intervals, the word becomes 100% MASTERED!",
            "Auto-Advance Next List" to "When a list reaches 100% mastery, you can seamlessly jump to the next list to keep mastering words."
        ),
        highlightTipEs = "🎯 Regla de oro: 4 aciertos = 100% de dominio garantizado en tu memoria a largo plazo.",
        highlightTipEn = "🎯 Golden rule: 4 successful swipes right = 100% mastery locked into long-term memory."
    ),

    // STEP 3: Listas en Estudio (Retoma de inmediato)
    InitialTutorialStep(
        stepIndex = 2,
        tagEs = "PASO 3 DE 6 • LISTAS EN ESTUDIO",
        tagEn = "STEP 3 OF 6 • LISTS IN STUDY",
        titleEs = "Listas en Estudio",
        titleEn = "Lists in Study",
        subtitleEs = "Retoma al instante donde te quedaste sin perder tiempo",
        subtitleEn = "Resume exactly where you left off without searching",
        primaryIcon = Icons.Filled.TrackChanges,
        accentColor = ElectricCyanDark,
        bulletPointsEs = listOf(
            "Sección 'Listas en Estudio'" to "En la pantalla de Inicio encontrarás el acceso directo a 'Listas en Estudio'.",
            "Donde te quedaste" to "Si presionas Listas en Estudio, ahí están exactamente las listas en las que estás estudiando actualmente o en las que te quedaste.",
            "Porcentaje de avance en vivo" to "Cada lista te indica su porcentaje de dominio (ej. 25%, 50%, 75%, 100%) y la fecha del último repaso.",
            "Retoma con 1 toque" to "Toca cualquier lista en estudio y continuarás practicando al instante desde la última tarjeta estudiada."
        ),
        bulletPointsEn = listOf(
            "'Lists in Study' Section" to "On the Home screen you will find the direct card for 'Lists in Study'.",
            "Where You Left Off" to "Tapping Lists in Study brings up exactly the lists you are actively studying or where you paused.",
            "Live Progress Percentages" to "Each list clearly shows its mastery percentage (e.g., 25%, 50%, 75%, 100%) and review date.",
            "1-Tap Instant Resume" to "Tap any study list to instantly continue learning from your exact last studied card."
        ),
        highlightTipEs = "📌 No tienes que buscar entre cientos de carpetas: tus listas activas siempre te esperan aquí.",
        highlightTipEn = "📌 Never dig through folders: your active lists are always waiting right here."
    ),

    // STEP 4: Modo Automático y Audio en Segundo Plano (Hands-Free)
    InitialTutorialStep(
        stepIndex = 3,
        tagEs = "PASO 4 DE 6 • MODO AUTOMÁTICO Y AUDIO",
        tagEn = "STEP 4 OF 6 • AUTO-PLAY & AUDIO",
        titleEs = "Modo Automático y Segundo Plano",
        titleEn = "Auto-Play & Background Audio",
        subtitleEs = "Escúchala después de practicar y ponla en segundo plano",
        subtitleEn = "Listen after practice and let it play in the background",
        primaryIcon = Icons.Filled.Headphones,
        accentColor = Color(0xFF8B5CF6),
        bulletPointsEs = listOf(
            "Modo Automático Inteligente" to "La ventana de aprendizaje cuenta con un modo automático que avanza las tarjetas solo y reproduce la pronunciación con voz nativa.",
            "El mejor método de estudio" to "Lo mejor que puedes hacer es escuchar la lista después de que la practicas deslizando. Te ayudará a fijar los sonidos y la memoria.",
            "Escucha en Segundo Plano" to "Una vez escuchada, puedes poner la aplicación en segundo plano con la pantalla apagada o mientras usas otras apps.",
            "Aprende sin esfuerzo" to "Puedes escucharla y volver a estudiarla en segundo plano: será mucho más fácil para ti entrenar el oído mientras caminas o descansas."
        ),
        bulletPointsEn = listOf(
            "Intelligent Auto-Play" to "The learning screen features an auto-scroll mode that glides through cards and speaks each word with native audio.",
            "Best Study Practice" to "The most effective routine is listening to your list right after practicing with swipes. It cements auditory memory.",
            "Background Playback" to "Once started, you can lock your screen or switch to other apps; the audio keeps playing continuously in the background.",
            "Effortless Listening" to "Learn through background listening: training your ear becomes effortlessly natural while commuting or relaxing."
        ),
        highlightTipEs = "🎧 Consejo estrella: Practica primero deslizando y luego deja la lista sonando en segundo plano.",
        highlightTipEn = "🎧 Star tip: First practice with swipes, then let the list play hands-free in the background."
    ),

    // STEP 5: Cuestionarios y Práctica de Audio
    InitialTutorialStep(
        stepIndex = 4,
        tagEs = "PASO 5 DE 6 • CUESTIONARIOS Y AUDIO",
        tagEn = "STEP 5 OF 6 • QUIZZES & AUDIO",
        titleEs = "Cuestionarios y Práctica de Oído",
        titleEn = "Quizzes & Audio Training",
        subtitleEs = "Crea cuestionarios en base a lo que has estudiado",
        subtitleEn = "Create custom quizzes based on your studied lists",
        primaryIcon = Icons.Filled.Quiz,
        accentColor = PracticeCoral,
        bulletPointsEs = listOf(
            "Cuestionarios Inteligentes" to "En base a las listas que has estudiado, puedes generar cuestionarios interactivos en la pestaña de Práctica.",
            "Práctica de Audio Exclusiva" to "Es sumamente interesante practicar el audio solamente en esta función: escucha la palabra y selecciona la respuesta correcta.",
            "Refuerzo de Retención" to "Evalúa tu velocidad de comprensión oral y ortográfica para asegurarte de que nunca olvides las palabras aprendidas.",
            "Detección de puntos débiles" to "El cuestionario detecta las palabras que requieren más práctica y las agenda para tus siguientes repasos."
        ),
        bulletPointsEn = listOf(
            "Smart Quizzes" to "Based on the lists you have studied, you can generate interactive quizzes in the Practice tab.",
            "Exclusive Audio Practice" to "It is deeply effective to practice audio-only mode: listen to the pronunciation and pick the correct meaning.",
            "Retention Reinforcement" to "Test your auditory comprehension and spelling speed to make sure words stay permanently learned.",
            "Smart Weak-Spot Detection" to "The quiz identifies cards that need extra practice and automatically re-queues them for review."
        ),
        highlightTipEs = "🧪 Reta tu oído: ¡El modo de solo audio en los cuestionarios es perfecto para afinar tu comprensión oral!",
        highlightTipEn = "🧪 Challenge your ears: Audio-only quiz mode is perfect for fine-tuning your listening skills!"
    ),

    // STEP 6: Estadísticas y Todas las Colecciones
    InitialTutorialStep(
        stepIndex = 5,
        tagEs = "PASO 6 DE 6 • ESTADÍSTICAS Y COLECCIONES",
        tagEn = "STEP 6 OF 6 • STATS & COLLECTIONS",
        titleEs = "Estadísticas y Colecciones",
        titleEn = "Stats & Vocabulary Collections",
        subtitleEs = "Monitorea tu constancia y explora miles de palabras",
        subtitleEn = "Track your daily consistency and explore thousands of words",
        primaryIcon = Icons.Filled.BarChart,
        accentColor = EmeraldGreen,
        bulletPointsEs = listOf(
            "Estadísticas y Racha Diaria" to "En la sección de Estadísticas puedes ver tu racha de días (🔥), minutos de estudio, palabras dominadas y gráficas de progreso.",
            "Todas las Colecciones" to "Explora cuadernos oficiales organizados por niveles del Marco Común Europeo (A1, A2, B1, B2, C1, C2) y listas temáticas.",
            "Ventana para Aprender" to "Todas las colecciones te indican su estado y te permiten ingresar a la ventana de Aprender con un solo toque.",
            "Acceso al Tutorial Siempre Disponible" to "Este tutorial se presenta al iniciar la aplicación (con opción de saltar cuando desees), y siempre puedes consultarlo en Tiempo de Estudio & Recordatorio o con el botón (?)."
        ),
        bulletPointsEn = listOf(
            "Stats & Daily Streak" to "In the Stats section, monitor your consecutive day streak (🔥), study minutes, mastered words, and visual analytics.",
            "All Vocabulary Collections" to "Explore official booklets classified by CEFR levels (A1, A2, B1, B2, C1, C2) and custom thematic lists.",
            "Direct Learn Window" to "All collections show their current status and allow you to jump straight into the Learn window with a single tap.",
            "Always Available Tutorial" to "This tutorial is shown at startup (with instant skip anytime), and you can revisit it anytime in Study Time & Reminder or via the (?) button."
        ),
        highlightTipEs = "🎉 ¡Todo listo! Elige tu primera lista y comienza a deslizar para dominar el inglés.",
        highlightTipEn = "🎉 You're all set! Pick your first list and start swiping to master English."
    )
)

/**
 * Full 6-step tutorial curriculum tailored specifically for users learning Spanish.
 */
val INITIAL_TUTORIAL_STEPS_LEARN_SPANISH: List<InitialTutorialStep> = listOf(
    // STEP 1: Colecciones y Listas de Español (Nivel Básico para Principiantes)
    InitialTutorialStep(
        stepIndex = 0,
        tagEs = "PASO 1 DE 6 • COLECCIONES Y LISTAS DE ESPAÑOL",
        tagEn = "STEP 1 OF 6 • SPANISH COLLECTIONS & LISTS",
        titleEs = "Elige tu Colección y Lista de Español",
        titleEn = "Choose Your Spanish Collection & List",
        subtitleEs = "Aprende vocabulario en español adaptado a tu nivel",
        subtitleEn = "Learn Spanish vocabulary tailored to your exact pace",
        primaryIcon = Icons.Filled.CollectionsBookmark,
        accentColor = StarAmber,
        bulletPointsEs = listOf(
            "Colección y Lista a tu medida" to "Para aprender español, simplemente elige una colección y selecciona la lista de vocabulario que mejor se apegue a tu aprendizaje.",
            "Básico 1 para Principiantes" to "Si estás empezando con el español, la colección Básica (Básico 1) está diseñada especialmente para principiantes con palabras cotidianas, pronunciación nativa y contexto real.",
            "Ruta Progresiva Continua" to "Al dominarla, avanzarás con las siguientes colecciones: Básico 2, Básico 3, y los niveles subsiguientes (A1 a C2) paso a paso."
        ),
        bulletPointsEn = listOf(
            "Tailored Collection & List" to "To learn Spanish, simply select a collection and choose the study list that best fits your language goals.",
            "Basic 1 for Beginners" to "If you are starting out with Spanish, the Basic 1 collection is crafted specifically for beginners with essential everyday words, native audio, and context.",
            "Continuous Progressive Path" to "As you progress, advance naturally to Básico 2, Básico 3, and subsequent levels (A1 through C2)."
        ),
        highlightTipEs = "💡 Tip inicial: Comienza con Básico 1 si deseas construir una base sólida y natural de vocabulario en español.",
        highlightTipEn = "💡 Quick tip: Start with Basic 1 if you want to build a solid foundation of Spanish vocabulary."
    ),

    // STEP 2: Dinámica de Aprendizaje de Español (Deslizar ↑ y Dominio 100% con 4 aciertos →)
    InitialTutorialStep(
        stepIndex = 1,
        tagEs = "PASO 2 DE 6 • DINÁMICA DE APRENDIZAJE",
        tagEn = "STEP 2 OF 6 • STUDY DYNAMICS",
        titleEs = "Desliza y Domina el Español al 100%",
        titleEn = "Swipe Up & Master Spanish at 100%",
        subtitleEs = "Desliza ↑ para pasar y → para dominar con 4 aciertos",
        subtitleEn = "Swipe ↑ to advance and → to master with 4 reviews",
        primaryIcon = Icons.Filled.SwipeVertical,
        accentColor = EmeraldGreen,
        bulletPointsEs = listOf(
            "Desliza hacia arriba (↑)" to "Al pulsar tu lista de español, entra a la ventana para aprender. Desliza hacia arriba para ver tarjeta por tarjeta con audio en español y fonética.",
            "Toca la tarjeta para voltear" to "Toca la tarjeta para voltearla al instante y ver oraciones de ejemplo en contexto real y explicaciones gramaticales.",
            "Desliza a la derecha (→)" to "Cuando comprendas el vocabulario en español, simplemente desliza la tarjeta a la derecha (o pulsa Dominada).",
            "¡100% Dominado con 4 aciertos!" to "Si lo haces 4 veces con una palabra, ¡el vocabulario en español queda automáticamente 100% DOMINADO!",
            "Siguiente lista automática" to "Cuando tu lista esté al 100% dominada, el sistema te invita a seguir con la otra lista automáticamente hasta completar la colección."
        ),
        bulletPointsEn = listOf(
            "Swipe Up (↑) to Learn" to "Tap your Spanish list to enter the Learn screen. Swipe up to progress smoothly through words with native Spanish audio and context.",
            "Tap Card to Flip" to "Tap any card to instantly flip it and explore authentic sentence examples and grammar explanations.",
            "Swipe Right (→) When Known" to "When you understand the Spanish word, simply swipe the card to the right (or tap Mastered).",
            "100% Mastered with 4 Swipes!" to "Once swiped right 4 times across spaced intervals, the Spanish word is 100% MASTERED!",
            "Auto-Advance Next List" to "When a list reaches 100% mastery, you can seamlessly jump to the next list to keep mastering Spanish."
        ),
        highlightTipEs = "🎯 Regla de oro: 4 aciertos a la derecha fijan permanentemente cada palabra en español en tu memoria a largo plazo.",
        highlightTipEn = "🎯 Golden rule: 4 successful swipes right = 100% Spanish mastery locked into long-term memory."
    ),

    // STEP 3: Listas en Estudio de Español (Retoma de inmediato)
    InitialTutorialStep(
        stepIndex = 2,
        tagEs = "PASO 3 DE 6 • LISTAS EN ESTUDIO",
        tagEn = "STEP 3 OF 6 • LISTS IN STUDY",
        titleEs = "Listas en Estudio de Español",
        titleEn = "Spanish Lists in Study",
        subtitleEs = "Retoma al instante donde te quedaste sin perder tiempo",
        subtitleEn = "Resume exactly where you left off without searching",
        primaryIcon = Icons.Filled.TrackChanges,
        accentColor = ElectricCyanDark,
        bulletPointsEs = listOf(
            "Sección 'Listas en Estudio'" to "En la pantalla de Inicio encontrarás el acceso directo a 'Listas en Estudio'.",
            "Donde te quedaste" to "Si presionas Listas en Estudio, ahí están exactamente las listas de español en las que estás estudiando actualmente o en las que te quedaste.",
            "Porcentaje de avance en vivo" to "Cada lista te indica su porcentaje de dominio (ej. 25%, 50%, 75%, 100%) y la fecha del último repaso.",
            "Retoma con 1 toque" to "Toca cualquier lista de español en estudio y continuarás practicando al instante desde la última tarjeta estudiada."
        ),
        bulletPointsEn = listOf(
            "'Lists in Study' Section" to "On the Home screen you will find the direct shortcut for 'Lists in Study'.",
            "Where You Left Off" to "Tapping Lists in Study brings up exactly the Spanish lists you are actively studying or where you paused.",
            "Live Progress Percentages" to "Each list clearly shows its mastery percentage (e.g., 25%, 50%, 75%, 100%) and review date.",
            "1-Tap Instant Resume" to "Tap any Spanish study list to instantly continue learning from your exact last studied card."
        ),
        highlightTipEs = "📌 No tienes que buscar entre cientos de carpetas: tus listas activas de español siempre te esperan aquí.",
        highlightTipEn = "📌 Never dig through folders: your active Spanish lists are always waiting right here."
    ),

    // STEP 4: Modo Automático y Audio en Segundo Plano de Español
    InitialTutorialStep(
        stepIndex = 3,
        tagEs = "PASO 4 DE 6 • MODO AUTOMÁTICO Y AUDIO",
        tagEn = "STEP 4 OF 6 • AUTO-PLAY & AUDIO",
        titleEs = "Modo Automático y Voz en Segundo Plano",
        titleEn = "Auto-Play & Spanish Background Audio",
        subtitleEs = "Escúchala después de practicar y ponla en segundo plano",
        subtitleEn = "Listen after practice and let it play in the background",
        primaryIcon = Icons.Filled.Headphones,
        accentColor = Color(0xFF8B5CF6),
        bulletPointsEs = listOf(
            "Modo Automático Inteligente" to "La ventana de aprendizaje cuenta con un modo automático que avanza las tarjetas solo y reproduce la pronunciación con voz nativa en español.",
            "El mejor método de estudio" to "Lo mejor que puedes hacer es escuchar la lista de español después de que la practicas deslizando. Te ayudará a fijar la entonación y los sonidos naturales del español.",
            "Audio en Segundo Plano" to "Una vez activada la reproducción, puedes poner la aplicación en segundo plano con la pantalla apagada o mientras usas otras apps.",
            "Aprende español sin esfuerzo" to "Puedes escucharla y volver a estudiarla en segundo plano: será mucho más fácil entrenar el oído al español mientras caminas o descansas."
        ),
        bulletPointsEn = listOf(
            "Intelligent Auto-Play" to "The learning screen features an auto-scroll mode that glides through cards and speaks each Spanish word with native pronunciation.",
            "Best Study Practice" to "The most effective routine is listening to your Spanish list right after practicing with swipes to cement auditory memory.",
            "Background Playback" to "Once started, you can lock your screen or switch to other apps; the Spanish audio keeps playing smoothly in the background.",
            "Effortless Listening" to "Training your ear to native Spanish rhythms becomes effortlessly natural while commuting or relaxing."
        ),
        highlightTipEs = "🎧 Consejo estrella: Practica primero deslizando y luego deja la lista de español sonando en segundo plano.",
        highlightTipEn = "🎧 Star tip: First practice with swipes, then let the Spanish list play hands-free in the background."
    ),

    // STEP 5: Cuestionarios y Práctica de Oído de Español
    InitialTutorialStep(
        stepIndex = 4,
        tagEs = "PASO 5 DE 6 • CUESTIONARIOS Y AUDIO",
        tagEn = "STEP 5 OF 6 • QUIZZES & AUDIO",
        titleEs = "Cuestionarios y Práctica de Oído",
        titleEn = "Spanish Quizzes & Listening Practice",
        subtitleEs = "Crea cuestionarios en base a lo que has estudiado",
        subtitleEn = "Create custom quizzes based on your studied lists",
        primaryIcon = Icons.Filled.Quiz,
        accentColor = PracticeCoral,
        bulletPointsEs = listOf(
            "Cuestionarios Inteligentes de Español" to "En base a las listas de español que has estudiado, puedes generar cuestionarios interactivos en la pestaña de Práctica.",
            "Práctica Auditiva Exclusiva" to "Es sumamente interesante practicar el audio solamente en esta función: escucha la palabra en español y selecciona el significado correcto.",
            "Refuerzo de Retención" to "Evalúa tu velocidad de comprensión oral y ortografía para asegurarte de que nunca olvides el vocabulario aprendido.",
            "Detección de puntos débiles" to "El cuestionario detecta las palabras en español que requieren más práctica y las agenda para tus siguientes repasos."
        ),
        bulletPointsEn = listOf(
            "Smart Spanish Quizzes" to "Based on the Spanish lists you have studied, you can generate interactive quizzes in the Practice tab.",
            "Exclusive Audio Practice" to "It is deeply effective to practice audio-only mode: listen to Spanish pronunciation and pick the correct meaning.",
            "Retention Reinforcement" to "Test your Spanish auditory comprehension and spelling speed to make sure words stay permanently learned.",
            "Weak-Spot Detection" to "The quiz identifies Spanish cards that need extra practice and automatically schedules them for review."
        ),
        highlightTipEs = "🧪 Reta tu oído: ¡El modo de solo audio en los cuestionarios es perfecto para afinar tu comprensión del español hablado!",
        highlightTipEn = "🧪 Challenge your ears: Audio-only quiz mode is perfect for fine-tuning your spoken Spanish comprehension!"
    ),

    // STEP 6: Estadísticas y Colecciones de Español
    InitialTutorialStep(
        stepIndex = 5,
        tagEs = "PASO 6 DE 6 • ESTADÍSTICAS Y COLECCIONES",
        tagEn = "STEP 6 OF 6 • STATS & COLLECTIONS",
        titleEs = "Estadísticas y Colecciones de Español",
        titleEn = "Stats & Spanish Vocabulary Collections",
        subtitleEs = "Monitorea tu constancia y explora miles de palabras en español",
        subtitleEn = "Track your daily consistency and explore thousands of Spanish words",
        primaryIcon = Icons.Filled.BarChart,
        accentColor = EmeraldGreen,
        bulletPointsEs = listOf(
            "Estadísticas y Racha Diaria" to "En la sección de Estadísticas puedes ver tu racha de días (🔥), minutos de práctica de español, palabras dominadas y gráficas de retención.",
            "Todas las Colecciones de Español" to "Explora cuadernos oficiales organizados por niveles del Marco Común Europeo (A1, A2, B1, B2, C1, C2) y listas temáticas de español.",
            "Ventana para Aprender" to "Todas las colecciones te indican su estado y te permiten ingresar a la ventana de Aprender con un solo toque.",
            "Acceso al Tutorial Siempre Disponible" to "Este tutorial se presenta al iniciar la aplicación (con opción de saltar cuando desees), y siempre puedes consultarlo en Tiempo de Estudio & Recordatorio o con el botón (?)."
        ),
        bulletPointsEn = listOf(
            "Stats & Daily Streak" to "In the Stats section, monitor your consecutive day streak (🔥), Spanish study minutes, mastered words, and analytics.",
            "All Spanish Collections" to "Explore official booklets classified by CEFR levels (A1, A2, B1, B2, C1, C2) and custom thematic Spanish lists.",
            "Direct Learn Window" to "All collections show their status and let you jump straight into the Learn window with a single tap.",
            "Always Available Tutorial" to "This tutorial is shown at startup (with instant skip anytime), and you can revisit it anytime in settings or the (?) button on Home."
        ),
        highlightTipEs = "🎉 ¡Todo listo! Elige tu primera lista de español y comienza a deslizar para dominar el idioma.",
        highlightTipEn = "🎉 You're all set! Pick your first Spanish list and start swiping to master the language."
    )
)

val INITIAL_TUTORIAL_STEPS: List<InitialTutorialStep> = INITIAL_TUTORIAL_STEPS_LEARN_ENGLISH

/**
 * Initial App Walkthrough Tutorial Dialog.
 * Automatically shown during startup, and accessible anytime via shortcut button.
 */
@Composable
fun InitialAppTutorialDialog(
    appLanguage: AppLanguage,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    learningMode: LearningMode = LearningMode.ES_TO_EN,
    onSelectLearningMode: ((LearningMode) -> Unit)? = null
) {
    val isSpanish = appLanguage == AppLanguage.SPANISH
    var currentTutorialMode by remember(learningMode) { mutableStateOf(learningMode) }
    val steps = if (currentTutorialMode == LearningMode.EN_TO_ES) {
        INITIAL_TUTORIAL_STEPS_LEARN_SPANISH
    } else {
        INITIAL_TUTORIAL_STEPS_LEARN_ENGLISH
    }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { steps.size })
    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f)),
            color = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 14.dp),
                    border = BorderStroke(1.5.dp, steps[pagerState.currentPage.coerceIn(steps.indices)].accentColor.copy(alpha = 0.35f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 520.dp)
                        .fillMaxHeight(0.92f)
                        .testTag("initial_tutorial_dialog_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 18.dp, vertical = 16.dp)
                    ) {
                        // ==========================================
                        // TOP BAR: STEP BADGE, CLOSE / SKIP BUTTON
                        // ==========================================
                        val currentStep = steps[pagerState.currentPage.coerceIn(steps.indices)]

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Step Pill (constrained so it never squeezes the Skip button)
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = currentStep.accentColor.copy(alpha = 0.15f),
                                border = BorderStroke(1.dp, currentStep.accentColor.copy(alpha = 0.4f)),
                                modifier = Modifier.weight(1f, fill = false)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = currentStep.primaryIcon,
                                        contentDescription = null,
                                        tint = currentStep.accentColor,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text(
                                        text = if (isSpanish) currentStep.tagEs else currentStep.tagEn,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = currentStep.accentColor,
                                        letterSpacing = 0.5.sp,
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Skip / Close Button (guaranteed no-wrap and fixed layout)
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { onDismiss() }
                                    .testTag("initial_tutorial_skip_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = if (isSpanish) "Saltar" else "Skip",
                                        fontSize = 12.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        softWrap = false,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = if (isSpanish) "Cerrar tutorial" else "Close tutorial",
                                        modifier = Modifier.size(15.dp),
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        // ==========================================
                        // LEARNING MODE SWITCHER TABS (🇬🇧 Inglés / 🇪🇸 Español)
                        // ==========================================
                        Surface(
                            shape = RoundedCornerShape(13.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                // Learn English Tab
                                val isSelectedEn = currentTutorialMode == LearningMode.ES_TO_EN
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelectedEn) PrimaryIndigo else Color.Transparent,
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            currentTutorialMode = LearningMode.ES_TO_EN
                                            onSelectLearningMode?.invoke(LearningMode.ES_TO_EN)
                                        }
                                        .testTag("tutorial_switch_to_learn_english")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "🇬🇧", fontSize = 13.sp)
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = if (isSpanish) "Aprender Inglés" else "Learn English",
                                            fontSize = 11.5.sp,
                                            fontWeight = if (isSelectedEn) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelectedEn) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                // Learn Spanish Tab
                                val isSelectedEs = currentTutorialMode == LearningMode.EN_TO_ES
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelectedEs) EmeraldGreen else Color.Transparent,
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            currentTutorialMode = LearningMode.EN_TO_ES
                                            onSelectLearningMode?.invoke(LearningMode.EN_TO_ES)
                                        }
                                        .testTag("tutorial_switch_to_learn_spanish")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "🇪🇸", fontSize = 13.sp)
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = if (isSpanish) "Aprender Español" else "Learn Spanish",
                                            fontSize = 11.5.sp,
                                            fontWeight = if (isSelectedEs) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelectedEs) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        // ==========================================
                        // PAGER CONTENT (6 DETAILED STEPS)
                        // ==========================================
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxSize()
                            ) { pageIndex ->
                                val step = steps[pageIndex]
                                val scrollState = rememberScrollState()

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(scrollState)
                                        .padding(horizontal = 4.dp, vertical = 4.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    // Hero Icon Banner
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(18.dp),
                                            color = step.accentColor.copy(alpha = 0.16f),
                                            border = BorderStroke(1.5.dp, step.accentColor.copy(alpha = 0.45f)),
                                            modifier = Modifier.size(54.dp)
                                        ) {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Icon(
                                                    imageVector = step.primaryIcon,
                                                    contentDescription = null,
                                                    tint = step.accentColor,
                                                    modifier = Modifier.size(28.dp)
                                                )
                                            }
                                        }

                                        Column {
                                            Text(
                                                text = if (isSpanish) step.titleEs else step.titleEn,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                lineHeight = 25.sp
                                            )
                                            Text(
                                                text = if (isSpanish) step.subtitleEs else step.subtitleEn,
                                                fontSize = 12.5.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    // Bullet Points List
                                    val points = if (isSpanish) step.bulletPointsEs else step.bulletPointsEn
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        points.forEachIndexed { idx, (bulletTitle, bulletDesc) ->
                                            Surface(
                                                shape = RoundedCornerShape(14.dp),
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                                                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                                    verticalAlignment = Alignment.Top
                                                ) {
                                                    Surface(
                                                        shape = CircleShape,
                                                        color = step.accentColor.copy(alpha = 0.2f),
                                                        modifier = Modifier
                                                            .size(22.dp)
                                                            .padding(top = 1.dp)
                                                    ) {
                                                        Box(contentAlignment = Alignment.Center) {
                                                            Text(
                                                                text = "${idx + 1}",
                                                                fontSize = 11.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = step.accentColor
                                                            )
                                                        }
                                                    }

                                                    Column(modifier = Modifier.weight(1f)) {
                                                        Text(
                                                            text = bulletTitle,
                                                            fontSize = 13.5.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = MaterialTheme.colorScheme.onSurface
                                                        )
                                                        Spacer(modifier = Modifier.height(2.dp))
                                                        Text(
                                                            text = bulletDesc,
                                                            fontSize = 12.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                            lineHeight = 17.5.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Highlight Tip Box (if present)
                                    val tip = if (isSpanish) step.highlightTipEs else step.highlightTipEn
                                    if (tip != null) {
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = step.accentColor.copy(alpha = 0.12f),
                                            border = BorderStroke(1.dp, step.accentColor.copy(alpha = 0.35f)),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = tip,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                lineHeight = 16.5.sp,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // ==========================================
                        // BOTTOM CONTROLS: INDICATOR DOTS & BUTTONS
                        // ==========================================
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Back button (visible when page > 0)
                            if (pagerState.currentPage > 0) {
                                OutlinedButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                    modifier = Modifier.testTag("initial_tutorial_prev_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Anterior",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) "Atrás" else "Back",
                                        fontSize = 13.sp
                                    )
                                }
                            } else {
                                TextButton(
                                    onClick = onDismiss,
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                    modifier = Modifier.testTag("initial_tutorial_bottom_skip_button")
                                ) {
                                    Text(
                                        text = if (isSpanish) "Saltar" else "Skip",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        maxLines = 1,
                                        softWrap = false,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            // Step Indicator Dots
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                repeat(steps.size) { dotIdx ->
                                    val isCurrent = pagerState.currentPage == dotIdx
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(
                                                width = if (isCurrent) 18.dp else 7.dp,
                                                height = 7.dp
                                            )
                                            .background(
                                                if (isCurrent) currentStep.accentColor else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                            )
                                    )
                                }
                            }

                            // Next / Start Learning Button
                            if (pagerState.currentPage < steps.size - 1) {
                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = currentStep.accentColor
                                    ),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                                    modifier = Modifier.testTag("initial_tutorial_next_button")
                                ) {
                                    Text(
                                        text = if (isSpanish) "Siguiente" else "Next",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Siguiente",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = onDismiss,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = EmeraldGreen
                                    ),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                                    modifier = Modifier.testTag("initial_tutorial_finish_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.RocketLaunch,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isSpanish) "¡Comenzar!" else "Start!",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
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
