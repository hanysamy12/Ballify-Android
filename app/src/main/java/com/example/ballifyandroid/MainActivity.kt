package com.example.ballifyandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ballifyandroid.presentation.navigation.MainScreen
import com.example.ballifyandroid.ui.theme.BallifyAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BallifyAndroidTheme {
                MainScreen()
            }
        }
    }
}

