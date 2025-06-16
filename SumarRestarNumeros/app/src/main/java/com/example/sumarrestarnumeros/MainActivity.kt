package com.example.sumarrestarnumeros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
// import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumaRestaApp()
        }
    }
}

@Composable
fun SumaRestaApp() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var operacion by remember { mutableStateOf("sumar") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Selecciona una operación:")

        Row {
            RadioButton(
                selected = operacion == "sumar",
                onClick = { operacion = "sumar" }
            )
            Text("Sumar", modifier = Modifier.padding(start = 4.dp, end = 16.dp))
            RadioButton(
                selected = operacion == "restar",
                onClick = { operacion = "restar" }
            )
            Text("Restar", modifier = Modifier.padding(start = 4.dp))
        }

        Button(onClick = {
            val n1 = num1.toDoubleOrNull()
            val n2 = num2.toDoubleOrNull()
            if (n1 == null || n2 == null) {
                Toast.makeText(context, "Ingresa números válidos", Toast.LENGTH_SHORT).show()
            } else {
                resultado = if (operacion == "sumar") n1 + n2 else n1 - n2
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular")
        }

        resultado?.let {
            Text("Resultado: $it", style = MaterialTheme.typography.titleMedium)
        }
    }
}