package com.example

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.service.BackgroundAudioPlaybackManager
import com.example.service.DailyReminderManager
import com.example.ui.screens.MainScreen
import com.example.ui.theme.EnglishSwipeTheme
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by viewModels()

  private val requestNotificationPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted: Boolean ->
    // Handled
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    enableEdgeToEdge()
    viewModel.recordAppOpen()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
      }
    }

    handleNavigationIntent(intent)

    setContent {
      val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
      val themeStyle by viewModel.appThemeStyle.collectAsStateWithLifecycle()

      EnglishSwipeTheme(
        themeStyle = themeStyle,
        darkTheme = isDarkMode
      ) {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          MainScreen(viewModel = viewModel)
        }
      }
    }
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
    handleNavigationIntent(intent)
  }

  private fun handleNavigationIntent(intent: Intent?) {
    val navTab = intent?.getIntExtra(DailyReminderManager.EXTRA_NAV_TAB, -1) ?: -1
    if (navTab in 0..4) {
      viewModel.setNavIndex(navTab)
    }
  }

  override fun onStart() {
    super.onStart()
    BackgroundAudioPlaybackManager.setAppInForeground(true)
  }

  override fun onResume() {
    super.onResume()
    BackgroundAudioPlaybackManager.setAppInForeground(true)
  }

  override fun onPause() {
    super.onPause()
    BackgroundAudioPlaybackManager.setAppInForeground(false)
  }

  override fun onStop() {
    super.onStop()
    BackgroundAudioPlaybackManager.setAppInForeground(false)
  }
}

