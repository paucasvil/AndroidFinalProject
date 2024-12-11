package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.viewmodels.TeamViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.example.androidfinalproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTeamView(navController: NavController, teamId: Int, viewModel: TeamViewModel = hiltViewModel()) {
    // Obtener el equipo por ID desde el ViewModel y recolectar el flujo
    val team by viewModel.getTeamById(teamId).collectAsState(initial = null)

    // Verificar si el equipo es nulo antes de acceder a sus propiedades
    if (team == null) {
        // Si no se encuentra el equipo, mostramos un mensaje de error
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

    // Lottie animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.volleyball_modern))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE) // Repetir la animación

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // Espacio superior para el título

        // Título
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.SportsVolleyball,
                contentDescription = "Voleibol",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF1976D2)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Editar Equipo",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2) // Color azul de la temática de voleibol
            )
        }

        // Campo para el nombre del equipo
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Equipo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1976D2), // Bordes azules
                unfocusedBorderColor = Color.Gray
            )
        )

        // Campo para el nombre del entrenador
        OutlinedTextField(
            value = coach,
            onValueChange = { coach = it },
            label = { Text("Nombre del Entrenador") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1976D2), // Bordes azules
                unfocusedBorderColor = Color.Gray
            )
        )

        // Selección de color
        Text(text = "Color del Equipo:", fontSize = 18.sp, color = Color(0xFF1976D2))
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
                            color = Color(0xFF1976D2) // Bordes azules para el color seleccionado
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color(0xFF1976D2)), // Azul para el botón
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "Guardar Cambios",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Texto blanco para resaltar sobre el azul
            )
        }
        Button(
            onClick = { navController.navigate("Menu") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)) // Azul vibrante
        ) {
            Text(
                text = "Atrás",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.White
            )
        }


        // Lottie Animation (volleyball) en la parte inferior
        Spacer(modifier = Modifier.weight(1f)) // Empujar la animación hacia abajo
        LottieAnimation(composition, progress, modifier = Modifier.fillMaxWidth().height(200.dp))
    }
}
