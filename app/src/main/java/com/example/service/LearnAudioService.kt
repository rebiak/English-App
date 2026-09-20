package com.example.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.data.model.Flashcard
import com.example.ui.util.SpanishPhoneticUtil
import com.example.ui.viewmodel.AutoScrollSpeed
import com.example.ui.viewmodel.CardDisplayMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale

class LearnAudioService : Service() {

    companion object {
        const val CHANNEL_ID = "english_swipe_audio_channel"
        const val CHANNEL_NAME = "Reproducción de Audio en Segundo Plano"
        const val NOTIFICATION_ID = 4040

        const val ACTION_START_OR_RESUME = "com.example.service.ACTION_START_OR_RESUME"
        const val ACTION_PAUSE = "com.example.service.ACTION_PAUSE"
        const val ACTION_PLAY = "com.example.service.ACTION_PLAY"
        const val ACTION_NEXT = "com.example.service.ACTION_NEXT"
        const val ACTION_PREV = "com.example.service.ACTION_PREV"
        const val ACTION_STOP = "com.example.service.ACTION_STOP"
    }

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private var wakeLock: PowerManager.WakeLock? = null
    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private var isTtsSpeaking = false

    private var playbackLoopJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        acquireWakeLock()
        initTts()
        BackgroundAudioPlaybackManager.setServiceActive(true)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action ?: ACTION_START_OR_RESUME
        Log.d("LearnAudioService", "onStartCommand action: $action")

        when (action) {
            ACTION_START_OR_RESUME, ACTION_PLAY -> {
                BackgroundAudioPlaybackManager.setPlaying(true)
                BackgroundAudioPlaybackManager.setServiceActive(true)
                startForegroundNotification()
                startBackgroundPlaybackLoop()
            }
            ACTION_PAUSE -> {
                pausePlayback()
            }
            ACTION_NEXT -> {
                advanceToNextCard()
            }
            ACTION_PREV -> {
                advanceToPreviousCard()
            }
            ACTION_STOP -> {
                stopPlaybackService()
            }
        }

        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Mantiene la pronunciación y avance de tarjetas activo en segundo plano"
                setShowBadge(false)
                setSound(null, null)
                enableVibration(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun acquireWakeLock() {
        try {
            val powerManager = getSystemService(Context.POWER_SERVICE) as? PowerManager
            wakeLock = powerManager?.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "EnglishSwipe:AudioPlaybackWakeLock"
            )?.apply {
                setReferenceCounted(false)
                acquire(2 * 60 * 60 * 1000L) // 2 hours max
            }
        } catch (e: Exception) {
            Log.w("LearnAudioService", "Could not acquire WakeLock: ${e.message}")
        }
    }

    private fun initTts() {
        try {
            tts = TextToSpeech(applicationContext) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val t = tts
                    if (t != null) {
                        t.setLanguage(Locale.US)
                        t.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                            override fun onStart(utteranceId: String?) {
                                isTtsSpeaking = true
                            }

                            override fun onDone(utteranceId: String?) {
                                isTtsSpeaking = false
                            }

                            @Deprecated("Deprecated in Java")
                            override fun onError(utteranceId: String?) {
                                isTtsSpeaking = false
                            }

                            override fun onError(utteranceId: String?, errorCode: Int) {
                                isTtsSpeaking = false
                            }
                        })
                        isTtsReady = true
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("LearnAudioService", "Error constructing Service TTS: ${e.message}")
        }
    }

    private fun startForegroundNotification() {
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun buildNotification(): Notification {
        val cards = BackgroundAudioPlaybackManager.cards.value
        val currentIndex = BackgroundAudioPlaybackManager.currentIndex.value
        val isPlaying = BackgroundAudioPlaybackManager.isPlaying.value
        val card = cards.getOrNull(currentIndex)

        val learningMode = BackgroundAudioPlaybackManager.learningMode.value
        val titleText = if (learningMode == com.example.ui.util.LearningMode.EN_TO_ES) {
            card?.spanish ?: "EnglishSwipe"
        } else {
            card?.english ?: "EnglishSwipe"
        }

        val displayPhonetic = card?.let { SpanishPhoneticUtil.getDisplayPhonetic(it, learningMode) } ?: ""
        val phoneticPart = if (displayPhonetic.isNotBlank()) "$displayPhonetic • " else ""
        val contentText = if (card != null) {
            if (learningMode == com.example.ui.util.LearningMode.EN_TO_ES) {
                "${card.english}${if (displayPhonetic.isNotBlank()) " • $displayPhonetic" else ""} • ${card.cefrLevel}"
            } else {
                "$phoneticPart${card.spanish}"
            }
        } else {
            "Reproducción activa en segundo plano"
        }
        val subText = if (card != null) "${card.category} • ${currentIndex + 1}/${cards.size}" else null

        // Open main app pending intent
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openAppPendingIntent = PendingIntent.getActivity(
            this, 100, openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Previous Action
        val prevIntent = Intent(this, LearnAudioService::class.java).apply {
            action = ACTION_PREV
            data = android.net.Uri.parse("englishswipe://action/prev")
        }
        val prevPendingIntent = PendingIntent.getService(
            this, 101, prevIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Play/Pause Action - distinct request codes and data URI ensure no stale cached PendingIntent
        val playPauseAction = if (isPlaying) ACTION_PAUSE else ACTION_PLAY
        val playPauseRequestCode = if (isPlaying) 103 else 102
        val playPauseIntent = Intent(this, LearnAudioService::class.java).apply {
            action = playPauseAction
            data = android.net.Uri.parse("englishswipe://action/$playPauseAction")
        }
        val playPausePendingIntent = PendingIntent.getService(
            this, playPauseRequestCode, playPauseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Next Action
        val nextIntent = Intent(this, LearnAudioService::class.java).apply {
            action = ACTION_NEXT
            data = android.net.Uri.parse("englishswipe://action/next")
        }
        val nextPendingIntent = PendingIntent.getService(
            this, 104, nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Stop Action
        val stopIntent = Intent(this, LearnAudioService::class.java).apply {
            action = ACTION_STOP
            data = android.net.Uri.parse("englishswipe://action/stop")
        }
        val stopPendingIntent = PendingIntent.getService(
            this, 105, stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_audio_learning)
            .setContentTitle(titleText)
            .setContentText(contentText)
            .setContentIntent(openAppPendingIntent)
            .setOngoing(isPlaying)
            .setOnlyAlertOnce(true)
            .setDeleteIntent(stopPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(android.R.drawable.ic_media_previous, "Anterior", prevPendingIntent)
            .addAction(
                if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play,
                if (isPlaying) "Pausar" else "Reanudar",
                playPausePendingIntent
            )
            .addAction(android.R.drawable.ic_media_next, "Siguiente", nextPendingIntent)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Cerrar", stopPendingIntent)

        if (subText != null) {
            builder.setSubText(subText)
        }

        return builder.build()
    }

    private fun updateNotification() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        manager?.notify(NOTIFICATION_ID, buildNotification())
    }

    private fun startBackgroundPlaybackLoop() {
        playbackLoopJob?.cancel()
        playbackLoopJob = serviceScope.launch {
            while (isActive && BackgroundAudioPlaybackManager.isPlaying.value) {
                val cards = BackgroundAudioPlaybackManager.cards.value
                if (cards.isEmpty()) {
                    delay(500)
                    continue
                }

                val currentIndex = BackgroundAudioPlaybackManager.currentIndex.value
                val card = cards.getOrNull(currentIndex) ?: break

                updateNotification()

                // Only perform speaking & advancing here if app is in background
                if (!BackgroundAudioPlaybackManager.isAppInForeground.value) {
                    val displayMode = BackgroundAudioPlaybackManager.cardDisplayMode.value
                    val currentLearningMode = BackgroundAudioPlaybackManager.learningMode.value
                    val textToSpeak = if (currentLearningMode == com.example.ui.util.LearningMode.EN_TO_ES) {
                        if (displayMode == CardDisplayMode.EXAMPLE && card.exampleTranslation.isNotBlank()) {
                            card.exampleTranslation
                        } else {
                            card.spanish
                        }
                    } else {
                        if (displayMode == CardDisplayMode.EXAMPLE && card.example.isNotBlank()) {
                            card.example
                        } else {
                            card.english
                        }
                    }

                    // Speak word in background
                    speakCard(textToSpeak, BackgroundAudioPlaybackManager.voiceAccent.value, currentLearningMode)

                    // Wait for TTS speaking to complete
                    while (isTtsSpeaking && isActive && BackgroundAudioPlaybackManager.isPlaying.value) {
                        delay(100)
                    }

                    // Calculate reading interval synchronized with speed multiplier
                    val speed = BackgroundAudioPlaybackManager.speedOption.value
                    val words = textToSpeak.split(Regex("\\s+")).filter { it.isNotBlank() }.size
                    val waitMs = when (speed) {
                        AutoScrollSpeed.SPEED_1X -> (4200L + (words * 380L)).coerceIn(4500L, 9000L)
                        AutoScrollSpeed.SPEED_2X -> (2600L + (words * 240L)).coerceIn(2800L, 5500L)
                        AutoScrollSpeed.SPEED_3X -> (1600L + (words * 150L)).coerceIn(1800L, 3400L)
                    }

                    var elapsed = 0L
                    while (elapsed < waitMs && isActive && BackgroundAudioPlaybackManager.isPlaying.value && !BackgroundAudioPlaybackManager.isAppInForeground.value) {
                        delay(100)
                        elapsed += 100
                    }

                    // If still playing and in background, advance to next card!
                    if (BackgroundAudioPlaybackManager.isPlaying.value && !BackgroundAudioPlaybackManager.isAppInForeground.value && cards.isNotEmpty()) {
                        val nextIndex = (currentIndex + 1) % cards.size
                        BackgroundAudioPlaybackManager.notifyIndexAdvancedFromService(nextIndex)
                    }
                } else {
                    // In foreground, the UI handles visual animation and audio sync
                    delay(300)
                }
            }
        }
    }

    private fun speakCard(text: String, accent: String, learningMode: com.example.ui.util.LearningMode = com.example.ui.util.LearningMode.ES_TO_EN) {
        val currentTts = tts ?: return
        if (!isTtsReady) return

        try {
            val locale = if (learningMode == com.example.ui.util.LearningMode.EN_TO_ES) {
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
            val res = currentTts.setLanguage(locale)
            if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                if (learningMode == com.example.ui.util.LearningMode.EN_TO_ES) {
                    currentTts.setLanguage(Locale.forLanguageTag("es"))
                } else {
                    currentTts.setLanguage(Locale.ENGLISH)
                }
            }
            val speed = BackgroundAudioPlaybackManager.speedOption.value
            val baseRate = 0.92f
            val effectiveRate = (baseRate * speed.speedMultiplier).coerceIn(0.35f, 2.5f)
            currentTts.setSpeechRate(effectiveRate)
            currentTts.setPitch(1.0f)

            val utteranceId = "BgSwipe_${System.currentTimeMillis()}"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                currentTts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
            } else {
                @Suppress("DEPRECATION")
                currentTts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        } catch (e: Exception) {
            Log.e("LearnAudioService", "Error during service speak: ${e.message}")
        }
    }

    private fun advanceToNextCard() {
        val cards = BackgroundAudioPlaybackManager.cards.value
        if (cards.isNotEmpty()) {
            tts?.stop()
            isTtsSpeaking = false
            val next = (BackgroundAudioPlaybackManager.currentIndex.value + 1) % cards.size
            BackgroundAudioPlaybackManager.notifyIndexAdvancedFromService(next)
            updateNotification()
            if (BackgroundAudioPlaybackManager.isPlaying.value && !BackgroundAudioPlaybackManager.isAppInForeground.value) {
                startBackgroundPlaybackLoop()
            }
        }
    }

    private fun advanceToPreviousCard() {
        val cards = BackgroundAudioPlaybackManager.cards.value
        if (cards.isNotEmpty()) {
            tts?.stop()
            isTtsSpeaking = false
            val current = BackgroundAudioPlaybackManager.currentIndex.value
            val prev = if (current - 1 < 0) {
                cards.size - 1
            } else {
                current - 1
            }
            BackgroundAudioPlaybackManager.notifyIndexAdvancedFromService(prev)
            updateNotification()
            if (BackgroundAudioPlaybackManager.isPlaying.value && !BackgroundAudioPlaybackManager.isAppInForeground.value) {
                startBackgroundPlaybackLoop()
            }
        }
    }

    private fun pausePlayback() {
        BackgroundAudioPlaybackManager.setPlaying(false)
        playbackLoopJob?.cancel()
        tts?.stop()
        isTtsSpeaking = false
        updateNotification()
    }

    private fun stopPlaybackService() {
        BackgroundAudioPlaybackManager.setPlaying(false)
        BackgroundAudioPlaybackManager.setServiceActive(false)
        playbackLoopJob?.cancel()
        tts?.stop()
        isTtsSpeaking = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        manager?.cancel(NOTIFICATION_ID)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        playbackLoopJob?.cancel()
        serviceJob.cancel()
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
        } catch (ignored: Exception) {}

        try {
            if (wakeLock?.isHeld == true) {
                wakeLock?.release()
            }
        } catch (ignored: Exception) {}

        BackgroundAudioPlaybackManager.setServiceActive(false)
    }
}
