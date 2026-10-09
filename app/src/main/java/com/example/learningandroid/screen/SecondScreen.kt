package com.example.learningandroid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningandroid.ui.theme.LearningAndroidTheme

@Composable
fun SecondScreen(
    text: String,
    onClickToFirstScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(text = "Text: $text")
        Button(onClick = onClickToFirstScreen) {
            Text("Back to first screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    LearningAndroidTheme {
        SecondScreen(
            text = "TestTestTestTest",
            onClickToFirstScreen = { }
        )
    }
}