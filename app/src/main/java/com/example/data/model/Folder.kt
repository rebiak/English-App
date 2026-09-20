package com.example.data.model

import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

data class Folder(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val emoji: String = "📁",
    val description: String = "",
    val categoryNames: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val level: String = "" // "Principiante", "Intermedio", "Avanzado" or ""
) {
    val isBasics: Boolean
        get() = id.startsWith("folder_basics_") || name.startsWith("Basics", ignoreCase = true)

    val isVocabularyBooklet: Boolean
        get() = id.startsWith("folder_vocabulary_") || name.contains("Vocabulary Booklet", ignoreCase = true) || name.contains("Booklet", ignoreCase = true)

    val isTranslations: Boolean
        get() = id.startsWith("folder_translations_") || name.contains("Translations", ignoreCase = true)

    val isAppDefault: Boolean
        get() = isBasics || isVocabularyBooklet || isTranslations

    val isPurgedLegacyFolder: Boolean
        get() = id in setOf("folder_everyday", "folder_work", "folder_travel") ||
                name.equals("General & Cotidiano", ignoreCase = true) ||
                name.equals("Profesional & Carrera", ignoreCase = true) ||
                name.equals("Viajes & Exploración", ignoreCase = true)

    val isUserCreated: Boolean
        get() = !isAppDefault && !isPurgedLegacyFolder

    fun toJson(): String {
        val obj = JSONObject()
        obj.put("id", id)
        obj.put("name", name)
        obj.put("emoji", emoji)
        obj.put("description", description)
        val arr = JSONArray()
        categoryNames.forEach { arr.put(it) }
        obj.put("categoryNames", arr)
        obj.put("createdAt", createdAt)
        obj.put("level", level)
        return obj.toString()
    }

    companion object {
        fun fromJson(jsonStr: String): Folder? {
            return try {
                val obj = JSONObject(jsonStr)
                val catArr = obj.optJSONArray("categoryNames")
                val catList = mutableListOf<String>()
                if (catArr != null) {
                    for (i in 0 until catArr.length()) {
                        catList.add(catArr.getString(i))
                    }
                }
                Folder(
                    id = obj.optString("id", UUID.randomUUID().toString()),
                    name = obj.optString("name", "General"),
                    emoji = obj.optString("emoji", "📁"),
                    description = obj.optString("description", ""),
                    categoryNames = catList,
                    createdAt = obj.optLong("createdAt", System.currentTimeMillis()),
                    level = obj.optString("level", "")
                )
            } catch (e: Exception) {
                null
            }
        }

        fun getDefaultFolders(): List<Folder> = listOf(
            Folder(
                id = "folder_basics_1",
                name = "Basics 1",
                emoji = "🌱",
                description = "Basics 1 con 8 listas de vocabulario y expresiones fundamentales",
                categoryNames = (1..8).map { "Basics 1 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_basics_2",
                name = "Basics 2",
                emoji = "🌿",
                description = "Basics 2 con 8 listas de vocabulario y expresiones fundamentales",
                categoryNames = (1..8).map { "Basics 2 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_basics_3",
                name = "Basics 3",
                emoji = "🌳",
                description = "Basics 3 con 8 listas de vocabulario y expresiones fundamentales",
                categoryNames = (1..8).map { "Basics 3 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_basics_4",
                name = "Basics 4",
                emoji = "🌾",
                description = "Basics 4 con 8 listas de repaso de nivel 3 y expresiones",
                categoryNames = (1..8).map { "Basics 4 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_basics_5",
                name = "Basics 5",
                emoji = "🍀",
                description = "Basics 5 con 8 listas de repaso de nivel 4 y estructuras",
                categoryNames = (1..8).map { "Basics 5 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_basics_6",
                name = "Basics 6",
                emoji = "🌲",
                description = "Basics 6 con 8 listas de repaso de nivel 5 y expresiones avanzadas",
                categoryNames = (1..8).map { "Basics 6 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_vocabulary_booklet_1",
                name = "Vocabulary Booklet 1",
                emoji = "📘",
                description = "Booklet 1 con 36 listas completas de vocabulario",
                categoryNames = (1..36).map { "List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_vocabulary_booklet_2",
                name = "Vocabulary Booklet 2",
                emoji = "📙",
                description = "Booklet 2 con 36 listas completas de vocabulario",
                categoryNames = (1..36).map { "Booklet 2 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_vocabulary_booklet_3",
                name = "Vocabulary Booklet 3",
                emoji = "📗",
                description = "Booklet 3 con 36 listas completas de vocabulario",
                categoryNames = (1..36).map { "Booklet 3 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_vocabulary_booklet_4",
                name = "Vocabulary Booklet 4",
                emoji = "📕",
                description = "Booklet 4 con 36 listas completas de vocabulario",
                categoryNames = (1..36).map { "Booklet 4 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_vocabulary_booklet_5",
                name = "Vocabulary Booklet 5",
                emoji = "📓",
                description = "Booklet 5 con 37 listas completas de vocabulario",
                categoryNames = (1..37).map { "Booklet 5 - List $it" },
                level = FolderLevel.ADVANCED
            ),
            Folder(
                id = "folder_vocabulary_booklet_6",
                name = "Vocabulary Booklet 6",
                emoji = "📚",
                description = "Booklet 6 con 36 listas completas de vocabulario",
                categoryNames = (1..36).map { "Booklet 6 - List $it" },
                level = FolderLevel.ADVANCED
            ),
            Folder(
                id = "folder_translations_1",
                name = "Translations Sentences 1",
                emoji = "💬",
                description = "Booklet 1 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 1 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_translations_2",
                name = "Translations Sentences 2",
                emoji = "🗣️",
                description = "Booklet 2 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 2 - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_translations_3",
                name = "Translations Sentences 3",
                emoji = "📝",
                description = "Booklet 3 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 3 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_translations_4",
                name = "Translations Sentences 4",
                emoji = "📖",
                description = "Booklet 4 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 4 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_translations_5",
                name = "Translations Sentences 5",
                emoji = "💡",
                description = "Booklet 5 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 5 - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_translations_6",
                name = "Translations Sentences 6",
                emoji = "🌐",
                description = "Booklet 6 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 6 - List $it" },
                level = FolderLevel.ADVANCED
            ),
            Folder(
                id = "folder_translations_7",
                name = "Translations Sentences 7",
                emoji = "🎯",
                description = "Booklet 7 con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations 7 - List $it" },
                level = FolderLevel.ADVANCED
            ),
            Folder(
                id = "folder_translations_a",
                name = "Translations Sentences A",
                emoji = "🏛️",
                description = "Booklet A con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations A - List $it" },
                level = FolderLevel.BEGINNER
            ),
            Folder(
                id = "folder_translations_b",
                name = "Translations Sentences B",
                emoji = "⚡",
                description = "Booklet B con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations B - List $it" },
                level = FolderLevel.INTERMEDIATE
            ),
            Folder(
                id = "folder_translations_c",
                name = "Translations Sentences C",
                emoji = "🏆",
                description = "Booklet C con 30 listas de oraciones y traducciones en contexto",
                categoryNames = (1..30).map { "Translations C - List $it" },
                level = FolderLevel.ADVANCED
            )
        )
    }
}

object FolderLevel {
    const val NONE = ""
    const val BEGINNER = "Principiante"
    const val INTERMEDIATE = "Intermedio"
    const val ADVANCED = "Avanzado"

    val ALL = listOf(BEGINNER, INTERMEDIATE, ADVANCED)

    fun normalize(level: String?): String {
        if (level == null) return NONE
        val lower = level.trim().lowercase()
        return when {
            lower.contains("principiante") || lower.contains("beginner") || lower.contains("básico") || lower.contains("basico") -> BEGINNER
            lower.contains("intermedio") || lower.contains("intermediate") || lower.contains("medio") -> INTERMEDIATE
            lower.contains("avanzado") || lower.contains("advanced") -> ADVANCED
            else -> level.trim()
        }
    }

    fun getDisplayName(level: String, isSpanish: Boolean): String {
        return when (normalize(level)) {
            BEGINNER -> if (isSpanish) "Principiante" else "Beginner"
            INTERMEDIATE -> if (isSpanish) "Intermedio" else "Intermediate"
            ADVANCED -> if (isSpanish) "Avanzado" else "Advanced"
            else -> level
        }
    }

    fun getEmoji(level: String): String {
        return when (normalize(level)) {
            BEGINNER -> "🌱"
            INTERMEDIATE -> "⚡"
            ADVANCED -> "🔥"
            else -> "🏷️"
        }
    }
}

/**
 * Natural alphanumeric comparator for strings (e.g. "List 2" before "List 10",
 * "Translations Sentences 1", "Translations Sentences 2", "Translations Sentences 3", etc.)
 */
fun naturalOrderCompare(s1: String, s2: String): Int {
    val regex = "(\\d+)|(\\D+)".toRegex()
    val tokens1 = regex.findAll(s1).map { it.value }.toList()
    val tokens2 = regex.findAll(s2).map { it.value }.toList()
    val maxLen = maxOf(tokens1.size, tokens2.size)
    for (i in 0 until maxLen) {
        val t1 = tokens1.getOrNull(i) ?: return -1
        val t2 = tokens2.getOrNull(i) ?: return 1
        val num1 = t1.toLongOrNull()
        val num2 = t2.toLongOrNull()
        if (num1 != null && num2 != null) {
            val cmp = num1.compareTo(num2)
            if (cmp != 0) return cmp
        } else {
            val cmp = t1.compareTo(t2, ignoreCase = true)
            if (cmp != 0) return cmp
        }
    }
    return 0
}

private fun getFolderPriority(name: String): Int {
    val lower = name.trim().lowercase()
    return when {
        lower.startsWith("basics") -> 0
        lower.startsWith("vocabulary booklet") -> 1
        lower.startsWith("translations sentences") -> 2
        else -> 3
    }
}

@JvmName("sortedFoldersNaturally")
fun Iterable<Folder>.sortedNaturally(): List<Folder> = sortedWith { f1, f2 ->
    val p1 = getFolderPriority(f1.name)
    val p2 = getFolderPriority(f2.name)
    if (p1 != p2) {
        p1.compareTo(p2)
    } else {
        naturalOrderCompare(f1.name, f2.name)
    }
}

@JvmName("sortedStringsNaturally")
fun Iterable<String>.sortedNaturally(): List<String> = sortedWith { s1, s2 -> naturalOrderCompare(s1, s2) }

