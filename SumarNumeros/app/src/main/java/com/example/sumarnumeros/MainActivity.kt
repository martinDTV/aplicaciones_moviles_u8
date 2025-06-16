package com.example.sumarnumeros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sumarnumeros.ui.theme.SumarNumerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumarNumerosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SumarNumerosUI()
                }
            }
        }
    }
}

@Composable
fun SumarNumerosUI() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val suma = (num1.toIntOrNull() ?: 0) + (num2.toIntOrNull() ?: 0)
            resultado = "Resultado: $suma"
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Sumar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultado, style = MaterialTheme.typography.bodyLarge)
    }
}
