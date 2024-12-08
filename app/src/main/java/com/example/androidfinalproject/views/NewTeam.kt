package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
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
    val selectedColor = remember { mutableStateOf(Color.White) } // Color seleccionado
    val openDialog = remember { mutableStateOf(false) } // Controlar apertura del diálogo

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

        // Botón para abrir el Color Picker
        Button(onClick = { openDialog.value = true }) {
            Text("Seleccionar Color")
        }

        // Mostrar el color seleccionado
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(selectedColor.value)
                .border(2.dp, Color.Black)
        )

        // Dialog para seleccionar color
        if (openDialog.value) {
            ColorPickerDialog(
                selectedColor = selectedColor,
                onDismiss = { openDialog.value = false }
            )
        }

        // Botón para guardar el equipo
        Button(
            onClick = {
                if (name.isNotEmpty() && coach.isNotEmpty()) {
                    val newTeam = Team(
                        name = name,
                        coach = coach,
                        color = selectedColor.value.toHex()
                    )
                    viewModel.addTeam(newTeam)
                    navController.navigate("TeamList")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Equipo")
        }
    }
}

@Composable
fun ColorPickerDialog(
    selectedColor: MutableState<Color>,
    onDismiss: () -> Unit
) {
    val colors = listOf(
        Color(0xFFFF0000), // Red
        Color(0xFF00FF00), // Green
        Color(0xFF0000FF), // Blue
        Color(0xFFFFFF00), // Yellow
        Color(0xFF00FFFF), // Cyan
        Color(0xFFFF00FF), // Magenta
        Color(0xFF808080), // Gray
        Color(0xFF000000), // Black
        Color(0xFFFFFFFF), // White
        Color(0xFFFFA500), // Orange
        Color(0xFF800080), // Purple
        Color(0xFF008080), // Teal
        Color(0xFF4682B4), // Steel Blue
        Color(0xFFFFC0CB), // Pink
        Color(0xFFADFF2F), // Green Yellow
        Color(0xFF5F9EA0), // Cadet Blue
        Color(0xFF6495ED), // Cornflower Blue
        Color(0xFFFFD700), // Gold
        Color(0xFFDC143C), // Crimson
        Color(0xFFB22222), // Firebrick
        Color(0xFF2E8B57), // Sea Green
        Color(0xFF00CED1), // Dark Turquoise
        Color(0xFF6A5ACD), // Slate Blue
        Color(0xFFDA70D6), // Orchid
        Color(0xFFF08080), // Light Coral
        Color(0xFF8B4513), // Saddle Brown
        Color(0xFF00FA9A), // Medium Spring Green
        Color(0xFF191970), // Midnight Blue
        Color(0xFFB8860B), // Dark Goldenrod
        Color(0xFF4169E1), // Royal Blue
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selecciona un color") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Elige un color:", modifier = Modifier.padding(bottom = 8.dp))
                // Grid Layout para los colores
                Column {
                    for (i in colors.indices step 5) { // 5 colores por fila
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            colors.subList(i, (i + 5).coerceAtMost(colors.size)).forEach { color ->
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(color)
                                        .clickable { selectedColor.value = color }
                                        .border(
                                            width = 2.dp,
                                            color = if (selectedColor.value == color) Color.Black else Color.Transparent
                                        )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}


// Extensión para convertir Color a formato hexadecimal
fun Color.toHex(): String {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    return String.format("#%02X%02X%02X", red, green, blue)
}
