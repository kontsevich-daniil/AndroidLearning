package com.example.learningandroid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningandroid.ui.theme.LearningAndroidTheme

@Composable
fun FirstScreen(
    onClickToSecondScreen: (String) -> Unit,
    onClickCall: (String) -> Unit,
    onClickShare: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        )
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                error = null
            },
            label = { Text("Enter text") },
            isError = error != null,
            supportingText = {
                error?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (text.isBlank()) {
                    error = "Enter text."
                } else {
                    onClickToSecondScreen(text)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to second screen")
        }

        Button(
            onClick = {
                val phoneNumber = text.trim()

                if (isValidPhoneNumber(phoneNumber)) {
                    error = null
                    onClickCall(normalizePhoneNumber(phoneNumber))
                } else {
                    error = "Enter a number consisting of 6 or 11 digits, or a number in the format +7XXXXXXXXXX."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Call by number")
        }

        Button(
            onClick = {
                if (text.isBlank()) {
                    error = "Enter text."
                } else {
                    error = null
                    onClickShare(text.trim())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Share text")
        }
    }
}

private fun isValidPhoneNumber(phone: String): Boolean
{
    val normalizedPhone = phone.trim()
    val digits = normalizedPhone.filter { it.isDigit() }
    val validLength = when {
        normalizedPhone.startsWith("+7") ->
            digits.length == 11

        else ->
            digits.length == 6 || digits.length == 11
    }

    val validCharacters = normalizedPhone.all {
        it.isDigit() || it in "+- ()"
    }

    val validPlus = normalizedPhone.count { it == '+' } <= 1 &&
            (!normalizedPhone.contains('+') ||
                    normalizedPhone.startsWith("+"))

    return normalizedPhone.isNotBlank() &&
            validLength &&
            validCharacters &&
            validPlus
}

private fun normalizePhoneNumber(phone: String): String
{
    val normalized = phone.trim()
    return if (normalized.startsWith("7")) {
        "+$normalized"
    } else {
        normalized
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    LearningAndroidTheme {
        FirstScreen(
            onClickToSecondScreen = { },
            onClickCall = { },
            onClickShare = { }
        )
    }
}