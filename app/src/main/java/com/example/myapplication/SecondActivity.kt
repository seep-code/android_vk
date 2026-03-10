package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val receivedText = intent.getStringExtra("EXTRA_TEXT") ?: "Текст не получен"

        setContent {
            MyApplicationTheme {
                SecondScreen(receivedText)
            }
        }
    }
}

@Composable
fun SecondScreen(receivedText: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ReceivedTextLabel()
        ReceivedTextValue(receivedText)
    }
}

@Composable
fun ReceivedTextLabel() {
    Text(
        text = "Полученный текст:",
        fontSize = 24.sp
    )
}

@Composable
fun ReceivedTextValue(receivedText: String) {
    Text(
        text = receivedText,
        fontSize = 24.sp
    )
}