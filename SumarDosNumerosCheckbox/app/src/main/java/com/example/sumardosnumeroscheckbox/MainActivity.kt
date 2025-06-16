package com.example.sumardosnumeroscheckbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sumardosnumeroscheckbox.ui.theme.SumarDosNumerosCheckboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumarDosNumerosCheckboxTheme {
                AppSumaResta()
            }
        }
    }
}

@Composable
fun AppSumaResta() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var sumar by remember { mutableStateOf(false) }
    var restar by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ingresa dos números")

        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = sumar, onCheckedChange = { sumar = it })
            Text("Sumar")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = restar, onCheckedChange = { restar = it })
            Text("Restar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val n1 = numero1.toIntOrNull() ?: 0
            val n2 = numero2.toIntOrNull() ?: 0
            val resultados = mutableListOf<String>()

            if (sumar) resultados.add("Suma: ${n1 + n2}")
            if (restar) resultados.add("Resta: ${n1 - n2}")
            if (!sumar && !restar) resultados.add("Selecciona al menos una operación")

            resultado = resultados.joinToString("\n")
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(resultado, fontSize = 20.sp)
    }
}
