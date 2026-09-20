package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class TextToSpeechHelper(private val context: Context) {
    private var tts: TextToSpeech? = null
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private val audioManager = context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as? AudioManager

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking = _isSpeaking.asStateFlow()

    // Store pending speech if user clicks before initialization is finished
    private var pendingSpeech: PendingRequest? = null

    private data class PendingRequest(
        val text: String,
        val accent: String,
        val isSlow: Boolean,
        val speedMultiplier: Float = 1.0f,
        val languageCode: String = "en"
    )

    init {
        initializeTts()
    }

    private fun initializeTts() {
        try {
            tts = TextToSpeech(context.applicationContext) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val t = tts
                    if (t != null) {
                        // Use USAGE_ASSISTANCE_ACCESSIBILITY & CONTENT_TYPE_SPEECH to prevent sound truncations
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            val audioAttributes = AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                                .build()
                            t.setAudioAttributes(audioAttributes)
                        }

                        // Configure Language with US English fallback
                        var result = t.setLanguage(Locale.US)
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            result = t.setLanguage(Locale.ENGLISH)
                        }
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            result = t.setLanguage(Locale.getDefault())
                        }

                        t.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                            override fun onStart(utteranceId: String?) {
                                _isSpeaking.value = true
                            }

                            override fun onDone(utteranceId: String?) {
                                _isSpeaking.value = false
                            }

                            @Deprecated("Deprecated in Java")
                            override fun onError(utteranceId: String?) {
                                _isSpeaking.value = false
                            }

                            override fun onError(utteranceId: String?, errorCode: Int) {
                                _isSpeaking.value = false
                                Log.w("TTS", "Playback error code: $errorCode for $utteranceId")
                            }
                        })

                        _isReady.value = true
                        Log.i("TTS", "TextToSpeech successfully initialized")

                        // Process any pending request
                        pendingSpeech?.let { req ->
                            pendingSpeech = null
                            executeSpeak(req.text, req.accent, req.isSlow)
                        }
                    }
                } else {
                    Log.e("TTS", "TTS Initialization failed with status $status")
                    _isReady.value = false
                }
            }
        } catch (e: Exception) {
            Log.e("TTS", "Failed to construct TextToSpeech: ${e.message}", e)
        }
    }

    /**
     * Pre-warms the Android AudioTrack mixer and DAC pipeline with a 50ms smooth micro-sound
     * so that the streaming audio channel is 100% active and unmuted BEFORE the voice starts.
     */
    private fun preWarmAudioPipeline() {
        try {
            val sampleRate = 44100
            val durationMs = 50
            val numSamples = (sampleRate * durationMs) / 1000
            val buffer = ShortArray(numSamples)
            // Generate a very soft, smooth sine wave at low volume
            for (i in 0 until numSamples) {
                val angle = 2.0 * Math.PI * i / (sampleRate / 440.0)
                val envelope = Math.sin(Math.PI * i / numSamples)
                buffer[i] = (Math.sin(angle) * Short.MAX_VALUE * 0.02 * envelope).toInt().toShort()
            }

            val minBufferSize = AudioTrack.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )
            val trackSize = maxOf(minBufferSize, buffer.size * 2)

            val audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(trackSize)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            audioTrack.write(buffer, 0, buffer.size)
            audioTrack.play()
            scope.launch {
                delay(100)
                try {
                    audioTrack.stop()
                    audioTrack.release()
                } catch (ignored: Exception) {}
            }
        } catch (e: Exception) {
            Log.w("TTS", "Audio pre-warm error: ${e.message}")
        }
    }

    /**
     * Cleans text while strictly preserving English contractions and apostrophes (e.g. don't, it's, I've, they're).
     * Adds only a single leading space at the beginning as requested for fast, instant TTS synthesis.
     */
    private fun formatTextForSpeech(raw: String): String {
        val processed = raw
            // 1. Normalize all typographic/curly apostrophes to standard English single quote '
            .replace(Regex("[’‘ʼ´`]"), "'")
            // 2. Remove phonetic brackets [ ... ] and parentheses ( ... )
            .replace(Regex("\\[.*?\\]"), " ")
            .replace(Regex("\\(.*?\\)"), " ")
            // 3. Remove double quotes, markdown symbols, tildes without touching apostrophes
            .replace(Regex("[\"\"«»*~_#]"), " ")
            // 4. Remove single quotes that are isolated/standalone punctuation, while preserving contractions (e.g. don't, it's)
            .replace(Regex("(?<=\\s|^)'|'(?=\\s|$)"), " ")
            // 5. Collapse extra spaces
            .replace(Regex("\\s+"), " ")
            .trim()

        if (processed.isBlank()) return ""

        // A single space at the start so the voice synthesizer starts cleanly and immediately
        return " $processed"
    }

    fun speak(
        text: String,
        accent: String = "US",
        isSlow: Boolean = false,
        speedMultiplier: Float = 1.0f,
        languageCode: String = "en"
    ) {
        val formatted = formatTextForSpeech(text)
        if (formatted.isBlank()) return

        if (!_isReady.value || tts == null) {
            pendingSpeech = PendingRequest(formatted, accent, isSlow, speedMultiplier, languageCode)
            scope.launch {
                var waitCount = 0
                while (!_isReady.value && waitCount < 15) {
                    delay(50)
                    waitCount++
                }
                if (_isReady.value && pendingSpeech != null) {
                    val req = pendingSpeech
                    pendingSpeech = null
                    if (req != null) {
                        executeSpeak(req.text, req.accent, req.isSlow, req.speedMultiplier, req.languageCode)
                    }
                } else if (!_isReady.value) {
                    initializeTts()
                }
            }
            return
        }

        executeSpeak(formatted, accent, isSlow, speedMultiplier, languageCode)
    }

    private fun executeSpeak(
        text: String,
        accent: String,
        isSlow: Boolean,
        speedMultiplier: Float = 1.0f,
        languageCode: String = "en"
    ) {
        val currentTts = tts ?: return
        try {
            @Suppress("DEPRECATION")
            audioManager?.requestAudioFocus(
                null,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
            )

            val locale = if (languageCode.equals("es", ignoreCase = true) || accent.startsWith("ES", ignoreCase = true)) {
                when (accent.uppercase(Locale.ROOT)) {
                    "MX", "ES_MX" -> Locale.forLanguageTag("es-MX")
                    else -> Locale.forLanguageTag("es-ES")
                }
            } else {
                when (accent.uppercase(Locale.ROOT)) {
                    "UK", "GB" -> Locale.UK
                    "AU" -> Locale.forLanguageTag("en-AU")
                    "CA" -> Locale.CANADA
                    else -> Locale.US
                }
            }

            val langResult = currentTts.setLanguage(locale)
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                if (languageCode.equals("es", ignoreCase = true)) {
                    currentTts.setLanguage(Locale.forLanguageTag("es"))
                } else {
                    currentTts.setLanguage(Locale.ENGLISH)
                }
            }

            // Calibrated speech rates for crystal-clear enunciation with speed multiplier support
            val baseRate = if (isSlow) 0.52f else 0.92f
            val effectiveRate = (baseRate * speedMultiplier).coerceIn(0.35f, 2.5f)
            currentTts.setSpeechRate(effectiveRate)
            currentTts.setPitch(1.0f)

            val utteranceId = "EnglishSwipe_${System.currentTimeMillis()}"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val params = Bundle().apply {
                    putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC)
                    putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
                }
                currentTts.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
            } else {
                @Suppress("DEPRECATION")
                val params = HashMap<String, String>().apply {
                    put(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC.toString())
                    put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId)
                }
                @Suppress("DEPRECATION")
                currentTts.speak(text, TextToSpeech.QUEUE_FLUSH, params)
            }
        } catch (e: Exception) {
            Log.e("TTS", "Error during speak execution: ${e.message}", e)
        }
    }

    fun stop() {
        try {
            pendingSpeech = null
            tts?.stop()
            _isSpeaking.value = false
        } catch (e: Exception) {
            Log.e("TTS", "Error stopping TTS: ${e.message}")
        }
    }

    fun shutdown() {
        try {
            pendingSpeech = null
            tts?.stop()
            tts?.shutdown()
            tts = null
            _isReady.value = false
            _isSpeaking.value = false
        } catch (e: Exception) {
            Log.e("TTS", "Error shutting down TTS: ${e.message}")
        }
    }
}
