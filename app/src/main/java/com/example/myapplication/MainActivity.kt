package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SimpleTextField(text = text, onTextChange = { text = it })
        OpenSecondActivityButton(text)
        CallFriendButton(text)
    }
}

@Composable
fun SimpleTextField(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("Введите текст") }
    )
}

@Composable
fun OpenSecondActivityButton(text: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("EXTRA_TEXT", text)
            context.startActivity(intent)
        }
    ) {
        Text("Открыть вторую Activity")
    }
}

@Composable
fun CallFriendButton(text: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            when {
                (text.isBlank()) -> {
                    val message = "Введите номер телефона"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                (!text.matches(Regex("^\\+?\\d+$"))) -> {
                    val message = "Некорректный номер. Только цифры или + в начале"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val phone = text
                    val intent = Intent(Intent.ACTION_DIAL, "tel:$phone".toUri())
                    context.startActivity(intent)
                }
            }
        }
    ) {
        Text("Позвонить другу")
    }
}