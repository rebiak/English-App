package com.example.ai

import android.content.Context
import android.content.SharedPreferences
import com.example.BuildConfig

class AiSettingsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_ai_settings_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_SELECTED_PROVIDER = "key_selected_ai_provider"
        private const val KEY_GOOGLE_API_KEY = "key_google_api_key"
        private const val KEY_GOOGLE_MODEL = "key_google_model"
        private const val KEY_OPENAI_API_KEY = "key_openai_api_key"
        private const val KEY_OPENAI_MODEL = "key_openai_model"
        private const val KEY_DEEPSEEK_API_KEY = "key_deepseek_api_key"
        private const val KEY_DEEPSEEK_MODEL = "key_deepseek_model"
    }

    fun loadConfiguration(): AiConfiguration {
        val providerId = prefs.getString(KEY_SELECTED_PROVIDER, AiProvider.GOOGLE_AI.id)
        val selectedProvider = AiProvider.fromId(providerId)

        val defaultGoogleKey = try {
            val key = BuildConfig.GEMINI_API_KEY
            if (key == "MY_GEMINI_API_KEY") "" else key
        } catch (e: Exception) {
            ""
        }

        val googleApiKey = prefs.getString(KEY_GOOGLE_API_KEY, defaultGoogleKey) ?: defaultGoogleKey
        val googleModel = prefs.getString(KEY_GOOGLE_MODEL, AiProvider.GOOGLE_AI.defaultModel) ?: AiProvider.GOOGLE_AI.defaultModel

        val openAiApiKey = prefs.getString(KEY_OPENAI_API_KEY, "") ?: ""
        val openAiModel = prefs.getString(KEY_OPENAI_MODEL, AiProvider.OPENAI.defaultModel) ?: AiProvider.OPENAI.defaultModel

        val deepSeekApiKey = prefs.getString(KEY_DEEPSEEK_API_KEY, "") ?: ""
        val deepSeekModel = prefs.getString(KEY_DEEPSEEK_MODEL, AiProvider.DEEPSEEK.defaultModel) ?: AiProvider.DEEPSEEK.defaultModel

        return AiConfiguration(
            selectedProvider = selectedProvider,
            googleApiKey = googleApiKey,
            googleModel = googleModel,
            openAiApiKey = openAiApiKey,
            openAiModel = openAiModel,
            deepSeekApiKey = deepSeekApiKey,
            deepSeekModel = deepSeekModel
        )
    }

    fun saveProvider(provider: AiProvider) {
        prefs.edit().putString(KEY_SELECTED_PROVIDER, provider.id).apply()
    }

    fun saveGoogleConfig(apiKey: String, model: String) {
        prefs.edit()
            .putString(KEY_SELECTED_PROVIDER, AiProvider.GOOGLE_AI.id)
            .putString(KEY_GOOGLE_API_KEY, apiKey.trim())
            .putString(KEY_GOOGLE_MODEL, model.trim().ifBlank { AiProvider.GOOGLE_AI.defaultModel })
            .apply()
    }

    fun saveOpenAiConfig(apiKey: String, model: String) {
        prefs.edit()
            .putString(KEY_SELECTED_PROVIDER, AiProvider.OPENAI.id)
            .putString(KEY_OPENAI_API_KEY, apiKey.trim())
            .putString(KEY_OPENAI_MODEL, model.trim().ifBlank { AiProvider.OPENAI.defaultModel })
            .apply()
    }

    fun saveDeepSeekConfig(apiKey: String, model: String) {
        prefs.edit()
            .putString(KEY_SELECTED_PROVIDER, AiProvider.DEEPSEEK.id)
            .putString(KEY_DEEPSEEK_API_KEY, apiKey.trim())
            .putString(KEY_DEEPSEEK_MODEL, model.trim().ifBlank { AiProvider.DEEPSEEK.defaultModel })
            .apply()
    }
}
