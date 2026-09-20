package com.example.ui.util

enum class AppLanguage(val code: String, val displayName: String, val flag: String) {
    SPANISH("es", "Español", "🇪🇸"),
    ENGLISH("en", "English", "🇺🇸")
}

enum class LearningMode(
    val id: String,
    val titleEs: String,
    val titleEn: String,
    val subtitleEs: String,
    val subtitleEn: String,
    val flag: String,
    val targetLanguage: String,
    val nativeLanguage: String
) {
    ES_TO_EN(
        id = "ES_TO_EN",
        titleEs = "Aprender Inglés",
        titleEn = "Learn English",
        subtitleEs = "Español ➡️ Inglés (Nativo español)",
        subtitleEn = "Spanish ➡️ English (Spanish native)",
        flag = "🇬🇧",
        targetLanguage = "en",
        nativeLanguage = "es"
    ),
    EN_TO_ES(
        id = "EN_TO_ES",
        titleEs = "Aprender Español",
        titleEn = "Learn Spanish",
        subtitleEs = "Inglés ➡️ Español (Nativo inglés)",
        subtitleEn = "English ➡️ Spanish (English native)",
        flag = "🇪🇸",
        targetLanguage = "es",
        nativeLanguage = "en"
    );

    companion object {
        fun fromId(id: String?): LearningMode {
            return when (id?.uppercase()) {
                "EN_TO_ES" -> EN_TO_ES
                else -> ES_TO_EN
            }
        }
    }
}

fun getCategoryDisplayName(cat: String, language: AppLanguage): String {
    val trimmed = cat.trim()
    val lower = trimmed.lowercase()
    if (lower == "all" || lower == "todas") return if (language == AppLanguage.SPANISH) "Todas" else "All"
    if (language == AppLanguage.ENGLISH) {
        return when {
            lower == "cotidiano y social" || lower == "everyday & social" -> "Everyday & Social"
            lower == "viajes y lugares" || lower == "travel & places" -> "Travel & Places"
            lower == "trabajo y negocios" || lower == "work & business" -> "Work & Business"
            lower == "tecnología y ciencia" || lower == "tech & science" -> "Tech & Science"
            lower == "comida y estilo de vida" || lower == "food & lifestyle" -> "Food & Lifestyle"
            lower == "verbos frasales y jerga" || lower == "phrasal verbs & slang" -> "Phrasal Verbs & Slang"
            lower == "académico y exámenes" || lower == "academic & exam" -> "Academic & Exam"
            else -> trimmed
        }
    } else {
        return when {
            lower == "everyday & social" || lower == "cotidiano y social" -> "Cotidiano y Social"
            lower == "travel & places" || lower == "viajes y lugares" -> "Viajes y Lugares"
            lower == "work & business" || lower == "trabajo y negocios" -> "Trabajo y Negocios"
            lower == "tech & science" || lower == "tecnología y ciencia" -> "Tecnología y Ciencia"
            lower == "food & lifestyle" || lower == "comida y estilo de vida" -> "Comida y Estilo de Vida"
            lower == "phrasal verbs & slang" || lower == "verbos frasales y jerga" -> "Verbos Frasales y Jerga"
            lower == "academic & exam" || lower == "académico y exámenes" -> "Académico y Exámenes"
            else -> trimmed
        }
    }
}

fun getTypeDisplayName(type: String, language: AppLanguage): String {
    val lower = type.trim().lowercase()
    if (language == AppLanguage.SPANISH) {
        return when {
            lower.contains("noun") || lower.contains("sustantivo") -> "Sustantivo"
            (lower.contains("verb") || lower.contains("verbo")) && !lower.contains("phrasal") && !lower.contains("frasal") -> "Verbo"
            lower.contains("adjective") || lower.contains("adjetivo") -> "Adjetivo"
            lower.contains("adverb") || lower.contains("adverbio") -> "Adverbio"
            lower.contains("phrasal") || lower.contains("frasal") -> "Verbo Frasal"
            lower.contains("idiom") || lower.contains("modismo") || lower.contains("expresión") || lower.contains("expression") -> "Modismo"
            lower.contains("slang") || lower.contains("jerga") -> "Jerga / Slang"
            lower.contains("preposition") || lower.contains("preposicion") -> "Preposición"
            lower.contains("conjunction") || lower.contains("conjuncion") -> "Conjunción"
            else -> type
        }
    } else {
        return when {
            lower.contains("sustantivo") || lower.contains("noun") -> "Noun"
            (lower.contains("verbo") || lower.contains("verb")) && !lower.contains("frasal") && !lower.contains("phrasal") -> "Verb"
            lower.contains("adjetivo") || lower.contains("adjective") -> "Adjective"
            lower.contains("adverbio") || lower.contains("adverb") -> "Adverb"
            lower.contains("frasal") || lower.contains("phrasal") -> "Phrasal Verb"
            lower.contains("modismo") || lower.contains("idiom") -> "Idiom"
            lower.contains("jerga") || lower.contains("slang") -> "Slang"
            lower.contains("preposicion") || lower.contains("preposition") -> "Preposition"
            lower.contains("conjuncion") || lower.contains("conjunction") -> "Conjunction"
            else -> type
        }
    }
}

fun formatTime12h(hour24: Int, minute: Int): String {
    val period = if (hour24 < 12) "AM" else "PM"
    val hour12 = if (hour24 % 12 == 0) 12 else hour24 % 12
    return String.format(java.util.Locale.getDefault(), "%02d:%02d %s", hour12, minute, period)
}

object Strings {
    fun get(key: String, language: AppLanguage): String {
        return if (language == AppLanguage.SPANISH) {
            spanishMap[key] ?: englishMap[key] ?: key
        } else {
            englishMap[key] ?: key
        }
    }

    private val spanishMap = mapOf(
        // Navigation
        "nav_learn" to "Aprender",
        "nav_practice" to "Práctica",
        "nav_words" to "Vocabulario",
        "nav_create" to "Crear",
        "nav_stats" to "Progreso",

        // Header & Streak
        "streak_days" to "DÍAS",
        "daily_goal" to "Meta diaria",
        "swipe_up_hint" to "Desliza hacia arriba",
        "topic" to "Tema",
        "categories" to "Listas",
        "accent" to "Acento",
        "language" to "Idioma",
        "learning_mode" to "Modo de Aprendizaje",
        "learning_mode_switch" to "Cambiar Modo de Aprendizaje",
        "learning_mode_es_to_en" to "Aprender Inglés",
        "learning_mode_en_to_es" to "Aprender Español",
        "learning_mode_es_to_en_sub" to "Español ➡️ Inglés (Nativo hispanohablante)",
        "learning_mode_en_to_es_sub" to "Inglés ➡️ Español (Nativo angloparlante)",
        "auto_scroll" to "Auto-Scroll",
        "auto_scroll_active" to "Auto-Avance Activo",
        "auto_scroll_paused" to "Auto-Avance Pausado",
        "auto_scroll_speed" to "Velocidad",
        "auto_scroll_hint" to "Avance automático inteligente con audio",

        // Actions on Feed Card
        "action_review" to "Repasar",
        "action_know" to "Lo sé",
        "action_flip" to "Voltear",
        "listen_normal" to "Escuchar",
        "listen_slow" to "Lento",
        "card_mastery" to "Dominio",
        "streak_cards" to "tarjetas",
        "front_tap_hint" to "Toca para voltear • Desliza ⬆ para continuar",
        "back_title" to "Significado & Ejemplos",
        "example_label" to "Ejemplo en contexto:",
        "play_audio" to "Escuchar",
        "slow_audio" to "Lento",
        "view_mode_word" to "🔤 Palabra",
        "view_mode_example" to "💬 Frase",
        "mode_normal_loop" to "Modo Normal",
        "mode_random_loop" to "Modo Aleatorio",
        "exit_expanded" to "Salir",
        "clear_screen" to "Pantalla limpia",
        "restore_screen" to "Restaurar interfaz",

        // Daily Goal & Reminder
        "daily_goal_settings_title" to "Tiempo de Estudio & Recordatorio",
        "daily_goal_settings_subtitle" to "Personaliza tus minutos de práctica diaria y la hora de tu recordatorio.",
        "how_much_time" to "¿Cuánto tiempo vas a practicar en esta sesión?",
        "custom_goal_option" to "Personalizado ⚙️",
        "custom_goal_desc" to "Elige exactamente cuántos minutos practicar",
        "daily_minutes" to "Minutos de sesión",
        "daily_reminder_title" to "Recordatorio Diario",
        "daily_reminder_subtitle" to "Te notificaremos a la hora seleccionada para practicar",
        "daily_reminder_enable" to "Activar Recordatorio Diario",
        "reminder_time_picker" to "Hora del Recordatorio:",
        "reminder_preset_morning" to "🌅 Mañana (08:00 AM)",
        "reminder_preset_afternoon" to "☀️ Tarde (02:00 PM)",
        "reminder_preset_evening" to "🌇 Tarde/Noche (07:00 PM)",
        "reminder_preset_night" to "🌙 Noche (09:00 PM)",
        "save_daily_goal" to "Guardar y Comenzar",
        "start_swiping" to "¡Comenzar a Practicar!",
        "daily_goal_card_title" to "⏱️ Tiempo de Práctica & Recordatorio",
        "edit_daily_goal" to "Configurar Tiempo y Horario",

        // Session Timer & Completion Card
        "session_timer_title" to "⏱️ Temporizador de Sesión",
        "session_time_completed_title" to "¡Tiempo Cumplido! ⏱️🎉",
        "session_time_completed_msg" to "¡Excelente trabajo! Has completado tus %d minutos de práctica programada.",
        "session_ask_continue" to "¿Deseas seguir practicando?",
        "session_continue_5m" to "+5 minutos",
        "session_continue_10m" to "+10 minutos",
        "session_continue_more" to "¡Seguir practicando!",
        "session_finish_for_today" to "Terminar por hoy",
        "session_timer_running" to "En curso",
        "session_timer_paused" to "Pausado",
        "session_time_remaining" to "Tiempo restante:",
        "session_preset_5m" to "5m • Rápido",
        "session_preset_10m" to "10m • Casual",
        "session_preset_20m" to "20m • Ideal",
        "session_preset_30m" to "30m • Enfocado",
        "session_preset_45m" to "45m • Intensivo",
        "session_preset_60m" to "60m • Maestro",

        // Quiz Mode
        "tab_quiz" to "❓ Modo Quiz",
        "tab_listening" to "🎧 Comprensión Auditiva",
        "quiz_select_list" to "Selecciona una lista:",
        "quiz_question_count" to "Preguntas:",
        "quiz_start" to "Iniciar Quiz",
        "quiz_start_ai" to "Crear Quiz con IA",
        "quiz_correct" to "¡Correcto! 🎉",
        "quiz_incorrect" to "Incorrecto ⚠️",
        "quiz_next" to "Siguiente",
        "quiz_finish" to "Terminar Quiz",
        "quiz_retry" to "Reintentar Quiz",
        "quiz_score" to "Puntuación",
        "quiz_completed_title" to "¡Quiz Completado! 🏆",
        "quiz_completed_subtitle" to "Has terminado todas las preguntas de esta ronda.",
        "quiz_review_words" to "Repasar Palabras",

        // Listening Mode
        "listening_title" to "🎧 Escucha y Reconoce",
        "listening_subtitle" to "Escucha la pronunciación en inglés e identifica el término o significado correcto.",
        "listening_play" to "Reproducir Audio",
        "listening_slow" to "Reproducir Lento",
        "listening_prompt" to "¿Qué palabra o significado escuchaste?",
        "listening_verify" to "Comprobar",

        // Create Card Screen
        "create_title" to "Crear Flashcards",
        "tab_ai_gen" to "✨ Generador IA",
        "tab_manual" to "➕ Manual",
        "tab_import" to "📥 Importar Texto",
        "tab_sub_words" to "✨ Palabras o Lista",
        "tab_sub_topic" to "💡 Por Tema",

        // Single Word AI Tab
        "single_ai_title" to "✨ Generar con IA (Una o Varias Palabras)",
        "single_ai_subtitle" to "Escribe una palabra o una lista completa. La IA generará traducciones, fonética IPA, definiciones y ejemplos.",
        "input_word_label" to "Palabra, frase o lista",
        "input_word_placeholder" to "ej. 'stubborn' o varias palabras:\nresilience, serendipity, bite the bullet",
        "btn_generate_single" to "Generar Tarjeta con IA",
        "btn_generate_multi" to "Generar Tarjetas con IA",
        "btn_add_to_deck" to "Guardar Tarjeta",
        "btn_save_all" to "Guardar Todas",
        "card_ready_save" to "¡Tarjeta guardada con éxito!",
        "save_to_list" to "Guardar en la lista:",
        "generated_preview" to "Tarjetas generadas",

        // Topic AI Tab
        "topic_ai_title" to "💡 Generar por Tema o Prompt Libre",
        "topic_ai_subtitle" to "Pídele a la IA cualquier categoría para generar un lote de tarjetas completas.",
        "topic_input_label" to "¿Qué vocabulario quieres aprender?",
        "topic_input_placeholder" to "ej. 10 phrasal verbs indispensables en el trabajo...",
        "btn_generate_deck" to "Generar Baraja con IA",

        // Import Tab
        "import_title" to "📥 Importar Vocabulario en Bloque",
        "import_subtitle" to "Formato: Inglés | Español | Ejemplo | Ícono (opcional)",
        "import_placeholder" to "Inglés | Español | Ejemplo | Ícono (opcional)",
        "btn_import_submit" to "Importar a mi Baraja",
        "import_success" to "¡Se importaron exitosamente las tarjetas!",

        // Manual Tab
        "manual_title" to "➕ Crear Tarjeta Manual",
        "manual_term_en" to "Término en Inglés *",
        "manual_trans_es" to "Traducción en Español *",
        "manual_phonetic" to "Pronunciación Fonética IPA (Opcional)",
        "manual_definition" to "Definición en contexto (Opcional)",
        "manual_ex_en" to "Frase de Ejemplo en Inglés",
        "manual_ex_es" to "Traducción del Ejemplo",
        "manual_category" to "Lista / Categoría:",
        "manual_category_new" to "Nueva Lista",
        "manual_category_dialog_title" to "Nueva Lista",
        "manual_category_dialog_label" to "Nombre de la lista",
        "manual_category_dialog_add" to "Agregar",
        "manual_category_dialog_cancel" to "Cancelar",
        "manual_icon_label" to "Ícono / Emoji:",
        "manual_btn_save" to "Guardar Tarjeta",
        "manual_saved_success" to "¡Tarjeta guardada con éxito!",

        // My Words Screen
        "my_words_title" to "Mis Palabras",
        "search_placeholder" to "Buscar palabra en inglés o español...",
        "filter_all" to "Todas",
        "filter_starred" to "⭐ Favoritas",
        "filter_mastered" to "🟢 Dominadas",
        "filter_learned" to "🟡 Aprendidas",
        "filter_in_progress" to "🔵 En Progreso",
        "filter_need_practice" to "🔴 Repasar",
        "filter_new" to "⚪ Nuevas",
        "btn_manage_lists" to "Listas",
        "btn_new_list" to "Nueva",
        "no_words_found" to "No se encontraron palabras",
        "no_words_subtitle" to "Intenta con otra búsqueda o agrega nuevas tarjetas.",

        // Stats & Progress
        "progress_title" to "Resumen de Avance",
        "progress_subtitle" to "Avance general y progreso detallado por cada lista",
        "overall_progress" to "AVANCE TOTAL",
        "completed_label" to "Completado",
        "words_learned_of" to "palabras aprendidas",
        "streak_label" to "Racha Actual",
        "studied_today_label" to "Estudiadas Hoy",
        "total_words_label" to "Total Palabras",
        "status_distribution" to "Distribución del Estado de Vocabulario",
        "progress_by_category" to "Avance por Lista / Categoría",
        "tap_to_expand" to "Toca para despejar",

        // Categories Management Sheet
        "manage_lists_title" to "Listas de Vocabulario",
        "manage_lists_subtitle" to "Crea, renombra o elimina listas para organizar tus palabras",
        "create_list_button" to "Crear Nueva Lista",
        "dialog_new_list_title" to "Nueva Lista de Vocabulario",
        "dialog_new_list_label" to "Nombre de la lista",
        "dialog_rename_list_title" to "Renombrar Lista",
        "dialog_rename_list_label" to "Nuevo nombre",
        "dialog_delete_list_title" to "¿Eliminar Lista?",
        "dialog_delete_list_msg" to "¿Deseas eliminar esta lista? Puedes conservar o eliminar sus tarjetas.",
        "delete_keep_cards" to "Eliminar lista (conservar tarjetas)",
        "delete_all_cards" to "Eliminar lista y todas sus tarjetas",
        "cancel" to "Cancelar",
        "save" to "Guardar",
        "delete" to "Eliminar",
        "default_badge" to "Predeterminada"
    )

    private val englishMap = mapOf(
        // Navigation
        "nav_learn" to "Learn",
        "nav_practice" to "Practice",
        "nav_words" to "Vocabulary",
        "nav_create" to "Create",
        "nav_stats" to "Stats",

        // Header & Streak
        "streak_days" to "DAYS",
        "daily_goal" to "Daily goal",
        "swipe_up_hint" to "Swipe up",
        "topic" to "Topic",
        "categories" to "Lists",
        "accent" to "Accent",
        "language" to "Language",
        "learning_mode" to "Learning Mode",
        "learning_mode_switch" to "Switch Learning Mode",
        "learning_mode_es_to_en" to "Learn English",
        "learning_mode_en_to_es" to "Learn Spanish",
        "learning_mode_es_to_en_sub" to "Spanish ➡️ English (Spanish native)",
        "learning_mode_en_to_es_sub" to "English ➡️ Spanish (English native)",
        "auto_scroll" to "Auto-Scroll",
        "auto_scroll_active" to "Auto-Scroll Active",
        "auto_scroll_paused" to "Auto-Scroll Paused",
        "auto_scroll_speed" to "Speed",
        "auto_scroll_hint" to "Intelligent auto-scroll with synchronized audio",

        // Actions on Feed Card
        "action_review" to "Review",
        "action_know" to "I know it",
        "action_flip" to "Flip",
        "listen_normal" to "Listen",
        "listen_slow" to "Slow",
        "card_mastery" to "Mastery",
        "streak_cards" to "cards",
        "front_tap_hint" to "Tap to flip • Swipe ⬆ for next",
        "back_title" to "Meaning & Examples",
        "example_label" to "Example in context:",
        "play_audio" to "Listen",
        "slow_audio" to "Slow",
        "view_mode_word" to "🔤 Word",
        "view_mode_example" to "💬 Sentence",
        "mode_normal_loop" to "Normal Mode",
        "mode_random_loop" to "Shuffle Mode",
        "exit_expanded" to "Exit",
        "clear_screen" to "Clear screen",
        "restore_screen" to "Restore UI",

        // Daily Goal & Reminder
        "daily_goal_settings_title" to "Study Time & Daily Reminder",
        "daily_goal_settings_subtitle" to "Customize your daily practice minutes and notification schedule.",
        "how_much_time" to "How much time will you practice in this session?",
        "custom_goal_option" to "Custom ⚙️",
        "custom_goal_desc" to "Choose exactly how many minutes to study",
        "daily_minutes" to "Session minutes",
        "daily_reminder_title" to "Daily Reminder",
        "daily_reminder_subtitle" to "We will notify you at your preferred time to practice",
        "daily_reminder_enable" to "Enable Daily Reminder",
        "reminder_time_picker" to "Reminder Time:",
        "reminder_preset_morning" to "🌅 Morning (08:00 AM)",
        "reminder_preset_afternoon" to "☀️ Afternoon (02:00 PM)",
        "reminder_preset_evening" to "🌇 Evening (07:00 PM)",
        "reminder_preset_night" to "🌙 Night (09:00 PM)",
        "save_daily_goal" to "Save & Start Session",
        "start_swiping" to "Start Practicing!",
        "daily_goal_card_title" to "⏱️ Study Time & Reminder",
        "edit_daily_goal" to "Configure Time & Reminder",

        // Session Timer & Completion Card
        "session_timer_title" to "⏱️ Session Timer",
        "session_time_completed_title" to "Time's Up! ⏱️🎉",
        "session_time_completed_msg" to "Great job! You have completed your scheduled %d minutes of practice.",
        "session_ask_continue" to "Would you like to continue practicing?",
        "session_continue_5m" to "+5 minutes",
        "session_continue_10m" to "+10 minutes",
        "session_continue_more" to "Keep Practicing!",
        "session_finish_for_today" to "Finish for today",
        "session_timer_running" to "Running",
        "session_timer_paused" to "Paused",
        "session_time_remaining" to "Time remaining:",
        "session_preset_5m" to "5m • Quick",
        "session_preset_10m" to "10m • Casual",
        "session_preset_20m" to "20m • Ideal",
        "session_preset_30m" to "30m • Focused",
        "session_preset_45m" to "45m • Intensive",
        "session_preset_60m" to "60m • Master",

        // Quiz Mode
        "tab_quiz" to "❓ Quiz Mode",
        "tab_listening" to "🎧 Listening Mode",
        "quiz_select_list" to "Select a list:",
        "quiz_question_count" to "Questions:",
        "quiz_start" to "Start Quiz",
        "quiz_start_ai" to "Create AI Quiz",
        "quiz_correct" to "Correct! 🎉",
        "quiz_incorrect" to "Incorrect ⚠️",
        "quiz_next" to "Next",
        "quiz_finish" to "Finish Quiz",
        "quiz_retry" to "Retry Quiz",
        "quiz_score" to "Score",
        "quiz_completed_title" to "Quiz Completed! 🏆",
        "quiz_completed_subtitle" to "You have completed all questions in this round.",
        "quiz_review_words" to "Review Words",

        // Listening Mode
        "listening_title" to "🎧 Listen & Recognize",
        "listening_subtitle" to "Listen to English pronunciation and identify the correct word or meaning.",
        "listening_play" to "Play Audio",
        "listening_slow" to "Play Slow",
        "listening_prompt" to "What word or meaning did you hear?",
        "listening_verify" to "Verify",

        // Create Card Screen
        "create_title" to "Create Flashcards",
        "tab_ai_gen" to "✨ AI Generator",
        "tab_manual" to "➕ Manual",
        "tab_import" to "📥 Import Text",
        "tab_sub_words" to "✨ Word(s) or List",
        "tab_sub_topic" to "💡 By Topic",

        // Single Word AI Tab
        "single_ai_title" to "✨ AI Generator (Single or Multiple Words)",
        "single_ai_subtitle" to "Type a single word or a full list. AI will generate translations, IPA phonetics, definitions, and examples.",
        "input_word_label" to "Word, phrase, or list",
        "input_word_placeholder" to "e.g. 'stubborn' or multiple words:\nresilience, serendipity, bite the bullet",
        "btn_generate_single" to "Generate Card with AI",
        "btn_generate_multi" to "Generate Cards with AI",
        "btn_add_to_deck" to "Save Card",
        "btn_save_all" to "Save All",
        "card_ready_save" to "Card saved successfully!",
        "save_to_list" to "Save to list:",
        "generated_preview" to "Generated cards",

        // Topic AI Tab
        "topic_ai_title" to "💡 Generate by Topic or Prompt",
        "topic_ai_subtitle" to "Ask AI for any category to generate a complete batch of cards.",
        "topic_input_label" to "What vocabulary do you want to learn?",
        "topic_input_placeholder" to "e.g. 10 essential phrasal verbs for the workplace...",
        "btn_generate_deck" to "Generate Deck with AI",

        // Import Tab
        "import_title" to "📥 Bulk Import Vocabulary",
        "import_subtitle" to "Format: English | Spanish | Example | Icon (optional)",
        "import_placeholder" to "English | Spanish | Example | Icon (optional)",
        "btn_import_submit" to "Import to My Deck",
        "import_success" to "Flashcards imported successfully!",

        // Manual Tab
        "manual_title" to "➕ Create Manual Card",
        "manual_term_en" to "English Term *",
        "manual_trans_es" to "Spanish Translation *",
        "manual_phonetic" to "IPA Phonetic Transcription (Optional)",
        "manual_definition" to "Context definition (Optional)",
        "manual_ex_en" to "English Example Sentence",
        "manual_ex_es" to "Example Translation",
        "manual_category" to "List / Category:",
        "manual_category_new" to "New List",
        "manual_category_dialog_title" to "New List",
        "manual_category_dialog_label" to "List name",
        "manual_category_dialog_add" to "Add",
        "manual_category_dialog_cancel" to "Cancel",
        "manual_icon_label" to "Icon / Emoji:",
        "manual_btn_save" to "Save Flashcard",
        "manual_saved_success" to "Card saved successfully!",

        // My Words Screen
        "my_words_title" to "My Words",
        "search_placeholder" to "Search English or Spanish words...",
        "filter_all" to "All",
        "filter_starred" to "⭐ Starred",
        "filter_mastered" to "🟢 Mastered",
        "filter_learned" to "🟡 Learned",
        "filter_in_progress" to "🔵 In Progress",
        "filter_need_practice" to "🔴 Need Practice",
        "filter_new" to "⚪ New",
        "btn_manage_lists" to "Lists",
        "btn_new_list" to "New",
        "no_words_found" to "No words found",
        "no_words_subtitle" to "Try another search or add new flashcards.",

        // Stats & Progress
        "progress_title" to "Progress Summary",
        "progress_subtitle" to "Overall progress and list-by-list breakdown",
        "overall_progress" to "OVERALL PROGRESS",
        "completed_label" to "Completed",
        "words_learned_of" to "words learned",
        "streak_label" to "Current Streak",
        "studied_today_label" to "Studied Today",
        "total_words_label" to "Total Words",
        "status_distribution" to "Vocabulary Status Distribution",
        "progress_by_category" to "Progress by List / Category",
        "tap_to_expand" to "Tap to expand",

        // Categories Management Sheet
        "manage_lists_title" to "Vocabulary Lists",
        "manage_lists_subtitle" to "Create, rename, or delete lists to organize your words",
        "create_list_button" to "Create New List",
        "dialog_new_list_title" to "New Vocabulary List",
        "dialog_new_list_label" to "List name",
        "dialog_rename_list_title" to "Rename List",
        "dialog_rename_list_label" to "New name",
        "dialog_delete_list_title" to "Delete List?",
        "dialog_delete_list_msg" to "Do you want to delete this list? You can keep or delete its cards.",
        "delete_keep_cards" to "Delete list (keep cards)",
        "delete_all_cards" to "Delete list and all its cards",
        "cancel" to "Cancel",
        "save" to "Save",
        "delete" to "Delete",
        "default_badge" to "Default"
    )
}
