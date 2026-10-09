package com.example.learningandroid
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.net.toUri

class FirstActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstScreen(
                onClickToSecondScreen = { text ->
                    val intent = Intent(
                        this,
                        SecondActivity::class.java
                    ).apply {
                        putExtra("write_text", text)
                    }

                    startActivity(intent)
                },

                onClickCall = { phone ->
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        "tel:$phone".toUri()
                    )

                    startActivity(intent)
                },

                onClickShare = { text ->
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }

                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Share by"
                        )
                    )
                }
            )
        }
    }
}