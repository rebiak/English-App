package com.example

import android.app.Application
import android.database.CursorWindow
import android.util.Log

class EnglishSwipeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            val field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 100 * 1024 * 1024) // 100MB buffer to handle full vocabulary datasets
            Log.d("EnglishSwipeApp", "CursorWindow size configured to 100MB successfully")
        } catch (e: Throwable) {
            Log.w("EnglishSwipeApp", "Could not set sCursorWindowSize via reflection", e)
        }
    }
}
