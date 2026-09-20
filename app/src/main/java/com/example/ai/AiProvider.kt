package com.example.ai

enum class AiProvider(
    val id: String,
    val displayName: String,
    val defaultModel: String,
    val availableModels: List<String>,
    val iconEmoji: String,
    val keyPlaceholder: String,
    val docsUrl: String
) {
    GOOGLE_AI(
        id = "google_ai",
        displayName = "Google AI Studio",
        defaultModel = "gemini-3.5-flash",
        availableModels = listOf("gemini-3.5-flash", "gemini-2.5-flash", "gemini-3.1-pro-preview"),
        iconEmoji = "⚡",
        keyPlaceholder = "AIzaSy...",
        docsUrl = "aistudio.google.com"
    ),
    OPENAI(
        id = "openai",
        displayName = "OpenAI (ChatGPT)",
        defaultModel = "gpt-4o-mini",
        availableModels = listOf("gpt-4o-mini", "gpt-4o", "gpt-3.5-turbo"),
        iconEmoji = "🟢",
        keyPlaceholder = "sk-proj-...",
        docsUrl = "platform.openai.com"
    ),
    DEEPSEEK(
        id = "deepseek",
        displayName = "DeepSeek AI",
        defaultModel = "deepseek-chat",
        availableModels = listOf("deepseek-chat", "deepseek-reasoner"),
        iconEmoji = "🐋",
        keyPlaceholder = "sk-...",
        docsUrl = "platform.deepseek.com"
    );

    companion object {
        fun fromId(id: String?): AiProvider {
            return values().firstOrNull { it.id.equals(id, ignoreCase = true) } ?: GOOGLE_AI
        }
    }
}

data class AiConfiguration(
    val selectedProvider: AiProvider = AiProvider.GOOGLE_AI,
    val googleApiKey: String = "",
    val googleModel: String = AiProvider.GOOGLE_AI.defaultModel,
    val openAiApiKey: String = "",
    val openAiModel: String = AiProvider.OPENAI.defaultModel,
    val deepSeekApiKey: String = "",
    val deepSeekModel: String = AiProvider.DEEPSEEK.defaultModel
) {
    fun getActiveApiKey(): String {
        return when (selectedProvider) {
            AiProvider.GOOGLE_AI -> googleApiKey
            AiProvider.OPENAI -> openAiApiKey
            AiProvider.DEEPSEEK -> deepSeekApiKey
        }
    }

    fun getActiveModel(): String {
        return when (selectedProvider) {
            AiProvider.GOOGLE_AI -> googleModel.ifBlank { selectedProvider.defaultModel }
            AiProvider.OPENAI -> openAiModel.ifBlank { selectedProvider.defaultModel }
            AiProvider.DEEPSEEK -> deepSeekModel.ifBlank { selectedProvider.defaultModel }
        }
    }

    fun isConfigured(): Boolean {
        return getActiveApiKey().isNotBlank()
    }
}
