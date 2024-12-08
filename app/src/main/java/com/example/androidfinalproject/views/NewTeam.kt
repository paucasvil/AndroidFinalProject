package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.model.Team
import com.example.androidfinalproject.viewmodels.TeamViewModel

@Composable
fun CreateTeamView(navController: NavController, viewModel: TeamViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var coach by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("#FFFFFF") } // Color por defecto (blanco)

    // Lista de colores disponibles
    val colorOptions = listOf(
        "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF", "#FFA500", "#808080", "#800080"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Crear Equipo", fontSize = 24.sp)

        // Campo para el nombre del equipo
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Equipo") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo para el nombre del entrenador
        OutlinedTextField(
            value = coach,
            onValueChange = { coach = it },
            label = { Text("Nombre del Entrenador") },
            modifier = Modifier.fillMaxWidth()
        )

        // Selección de color
        Text(text = "Color del Equipo:", fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            colorOptions.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(android.graphics.Color.parseColor(color)))
                        .clickable {
                            selectedColor = color // Guardar el color seleccionado
                        }
                        .border(
                            width = if (selectedColor == color) 2.dp else 0.dp,
                            color = Color.Black
                        )
                )
            }
        }

        // Botón para guardar el equipo
        Button(
            onClick = {
                // Validación básica de los campos
                if (name.isNotEmpty() && coach.isNotEmpty()) {
                    val newTeam = Team(
                        name = name,
                        coach = coach,
                        color = selectedColor
                    )
                    // Intentamos agregar el equipo y manejar posibles excepciones
                    try {
                        viewModel.addTeam(newTeam) // Agregar equipo a la base de datos
                        navController.navigate("TeamList") // Navegar a la lista de equipos
                    } catch (e: Exception) {
                        // Manejo de error si falla la inserción
                        println("Error al agregar el equipo: ${e.message}")
                    }
                } else {
                    // Mostrar un mensaje si los campos están vacíos
                    println("Por favor complete todos los campos.")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear Equipo")
        }
    }
}
