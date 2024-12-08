package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.viewmodels.TeamViewModel

@Composable
fun EditTeamView(navController: NavController, teamId: Int, viewModel: TeamViewModel = hiltViewModel()) {
    // Obtener el equipo por ID desde el ViewModel y recolectar el flujo
    val team by viewModel.getTeamById(teamId).collectAsState(initial = null)

    // Verificar si el equipo es nulo antes de acceder a sus propiedades
    if (team == null) {
        // Si no se encuentra el equipo, podemos mostrar un mensaje de error o un mensaje vacío
        Text(text = "Equipo no encontrado", fontSize = 20.sp)
        return
    }

    // Estados para los campos de texto y color
    var name by remember { mutableStateOf(team!!.name) }
    var coach by remember { mutableStateOf(team!!.coach) }
    var selectedColor by remember { mutableStateOf(team!!.color) }

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
        Text(text = "Editar Equipo", fontSize = 24.sp)

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

        // Botón para guardar cambios
        Button(
            onClick = {
                team!!.name = name
                team!!.coach = coach
                team!!.color = selectedColor
                viewModel.updateTeam(team!!) // Actualizar equipo en la base de datos
                navController.navigate("TeamList") // Navegar a la lista de equipos
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar Cambios")
        }
    }
}
