package com.example.service

import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.data.model.Flashcard
import com.example.ui.util.LearningMode
import com.example.ui.viewmodel.AutoScrollSpeed
import com.example.ui.viewmodel.CardDisplayMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Shared state and controller for Audio Playback across Foreground UI and Background Service.
 */
object BackgroundAudioPlaybackManager {

    private val _cards = MutableStateFlow<List<Flashcard>>(emptyList())
    val cards = _cards.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _isServiceActive = MutableStateFlow(false)
    val isServiceActive = _isServiceActive.asStateFlow()

    private val _speedOption = MutableStateFlow(AutoScrollSpeed.SPEED_1X)
    val speedOption = _speedOption.asStateFlow()

    private val _cardDisplayMode = MutableStateFlow(CardDisplayMode.WORD)
    val cardDisplayMode = _cardDisplayMode.asStateFlow()

    private val _voiceAccent = MutableStateFlow("US")
    val voiceAccent = _voiceAccent.asStateFlow()

    private val _learningMode = MutableStateFlow(LearningMode.ES_TO_EN)
    val learningMode = _learningMode.asStateFlow()

    private val _isAppInForeground = MutableStateFlow(true)
    val isAppInForeground = _isAppInForeground.asStateFlow()

    // Listener for UI pager sync when background service advances cards
    var onCardIndexAdvancedListener: ((Int) -> Unit)? = null

    fun setAppInForeground(isForeground: Boolean) {
        _isAppInForeground.value = isForeground
    }

    fun setServiceActive(active: Boolean) {
        _isServiceActive.value = active
    }

    fun setPlaying(playing: Boolean) {
        _isPlaying.value = playing
    }

    fun updateConfig(
        cardsList: List<Flashcard>,
        index: Int,
        accent: String,
        speed: AutoScrollSpeed,
        mode: CardDisplayMode,
        learningMode: LearningMode = _learningMode.value
    ) {
        _cards.value = cardsList
        if (cardsList.isNotEmpty()) {
            _currentIndex.value = index.coerceIn(0, cardsList.size - 1)
        }
        _voiceAccent.value = accent
        _speedOption.value = speed
        _cardDisplayMode.value = mode
        _learningMode.value = learningMode
    }

    fun updateCurrentIndexFromUI(index: Int, newCards: List<Flashcard>? = null) {
        if (newCards != null && newCards.isNotEmpty()) {
            _cards.value = newCards
        }
        val list = _cards.value
        if (list.isNotEmpty()) {
            _currentIndex.value = index.coerceIn(0, list.size - 1)
        } else {
            _currentIndex.value = index.coerceAtLeast(0)
        }
    }

    fun startOrSyncBackgroundService(
        context: Context,
        cardsList: List<Flashcard>,
        startIndex: Int,
        accent: String,
        speed: AutoScrollSpeed,
        mode: CardDisplayMode,
        learningMode: LearningMode = _learningMode.value
    ) {
        updateConfig(cardsList, startIndex, accent, speed, mode, learningMode)
        _isPlaying.value = true

        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_START_OR_RESUME
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun pausePlayback(context: Context) {
        _isPlaying.value = false
        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_PAUSE
        }
        try {
            context.startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun resumePlayback(context: Context) {
        _isPlaying.value = true
        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_START_OR_RESUME
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun nextCard(context: Context) {
        val list = _cards.value
        if (list.isNotEmpty()) {
            val next = (_currentIndex.value + 1) % list.size
            _currentIndex.value = next
            onCardIndexAdvancedListener?.invoke(next)
        }
        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_NEXT
        }
        try {
            context.startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun previousCard(context: Context) {
        val list = _cards.value
        if (list.isNotEmpty()) {
            val prev = if (_currentIndex.value - 1 < 0) list.size - 1 else _currentIndex.value - 1
            _currentIndex.value = prev
            onCardIndexAdvancedListener?.invoke(prev)
        }
        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_PREV
        }
        try {
            context.startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopBackgroundService(context: Context) {
        _isPlaying.value = false
        val intent = Intent(context, LearnAudioService::class.java).apply {
            action = LearnAudioService.ACTION_STOP
        }
        try {
            context.startService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun notifyIndexAdvancedFromService(newIndex: Int) {
        _currentIndex.value = newIndex
        onCardIndexAdvancedListener?.invoke(newIndex)
    }
}
