package com.example.learningandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.learningandroid.ui.theme.LearningAndroidTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val receivedText = intent.getStringExtra("write_text") ?: ""
        setContent {
            LearningAndroidTheme {
                SecondScreen(
                    text = receivedText,
                    onClickToFirstScreen = { finish() }
                )
            }
        }
    }
}