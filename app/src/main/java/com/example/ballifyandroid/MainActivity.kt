package com.example.ballifyandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.window.layout.WindowMetricsCalculator
import com.example.ballifyandroid.presentation.navigation.MainScreen
import com.example.ballifyandroid.ui.theme.BallifyAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val screenDimensions =getScreenDimensions()
        setContent {
            BallifyAndroidTheme {
                MainScreen(screenDimensions)
            }
        }
    }

    private fun getScreenDimensions(): Pair<Float, Float> {
        val metric = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val widthPx = metric.bounds.width()
        val heightPx = metric.bounds.height()
        val density = resources.displayMetrics.density
        val widthDp = widthPx / density
        val heightDp = heightPx / density
        return Pair(widthDp, heightDp)
    }
}

