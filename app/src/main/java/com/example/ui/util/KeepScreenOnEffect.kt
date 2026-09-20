package com.example.ui.util

import android.app.Activity
import android.content.ContextWrapper
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * Keeps the device screen turned on while this composable is active on screen.
 * Safely removes FLAG_KEEP_SCREEN_ON when the composable leaves the composition
 * or when the user moves the app into the background (ON_PAUSE).
 * Re-adds the flag when resumed (ON_RESUME).
 */
@Composable
fun KeepScreenOnEffect(enabled: Boolean = true) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(enabled, lifecycleOwner, context) {
        if (!enabled) return@DisposableEffect onDispose {}

        val window = run {
            var ctx = context
            while (ctx is ContextWrapper) {
                if (ctx is Activity) return@run ctx.window
                ctx = ctx.baseContext
            }
            null
        }

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }

        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
