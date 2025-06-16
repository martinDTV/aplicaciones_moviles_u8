package com.example.calculadoraspinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalFocusManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraSpinnerApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraSpinnerApp() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
    var operacionSeleccionada by remember { mutableStateOf(operaciones[0]) }
    var expanded by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora con Spinner (Dropdown)", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = numero1,
            onValueChange = { input ->
                // Solo permitir números y decimal
                if (input.all { it.isDigit() || it == '.' }) numero1 = input
            },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = numero2,
            onValueChange = { input ->
                if (input.all { it.isDigit() || it == '.' }) numero2 = input
            },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown de operaciones (Spinner)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                readOnly = true,
                value = operacionSeleccionada,
                onValueChange = {},
                label = { Text("Operación") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                operaciones.forEach { operacion ->
                    DropdownMenuItem(
                        text = { Text(operacion) },
                        onClick = {
                            operacionSeleccionada = operacion
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                val n1 = numero1.toDoubleOrNull()
                val n2 = numero2.toDoubleOrNull()
                if (n1 == null || n2 == null) {
                    resultado = "Por favor ingresa números válidos"
                    return@Button
                }
                resultado = when (operacionSeleccionada) {
                    "Sumar" -> (n1 + n2).toString()
                    "Restar" -> (n1 - n2).toString()
                    "Multiplicar" -> (n1 * n2).toString()
                    "Dividir" -> {
                        if (n2 == 0.0) "No se puede dividir entre cero"
                        else (n1 / n2).toString()
                    }
                    else -> ""
                }
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        resultado?.let {
            Text(
                text = "Resultado: $it",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
