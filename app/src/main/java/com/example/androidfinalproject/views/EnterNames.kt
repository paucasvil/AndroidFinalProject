package com.example.androidfinalproject.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidfinalproject.model.Team
import com.example.androidfinalproject.viewmodels.TeamViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.graphics.Color as ComposeColor
import com.airbnb.lottie.compose.LottieAnimation

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

import com.example.androidfinalproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTeamNamesView(navController: NavController, viewModel: TeamViewModel = hiltViewModel()) {
    // Estados para nombres de equipos
    var teamAName by remember { mutableStateOf("") }
    var teamBName by remember { mutableStateOf("") }
    var teamAColor by remember { mutableStateOf("#FF0000") }  // Color rojo por defecto
    var teamBColor by remember { mutableStateOf("#0000FF") }  // Color azul por defecto

    // Obtener lista de equipos desde el ViewModel
    val teams by viewModel.teamList.collectAsState()

    var expandedA by remember { mutableStateOf(false) }
    var expandedB by remember { mutableStateOf(false) }

    // Cargar la animación Lottie desde los recursos
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.volleyball_animation)) // Cambia "volleyball_animation" por el nombre de tu archivo JSON

    // Fondo de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animación Lottie en la parte superior
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(200.dp) // Ajusta el tamaño de la animación según lo necesites
                    .padding(bottom = 24.dp), // Espacio debajo de la animación
                iterations = Int.MAX_VALUE// Repetir animación indefinidamente
            )

            // Título estilizado
            Text(
                text = "Selecciona los nombres de los equipos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = ComposeColor(0xFF1976D2),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )
//h
            // Dropdown para Equipo A
            Text(text = "Equipo local", color = ComposeColor.White)
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = teamAName,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Selecciona Equipo Local") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedA = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = ComposeColor(0xFFFFEB3B), // Amarillo
                        unfocusedBorderColor = ComposeColor(0xFF1976D2) // Azul
                    )
                )
                DropdownMenu(
                    expanded = expandedA,
                    onDismissRequest = { expandedA = false }
                ) {
                    teams.forEach { team ->
                        DropdownMenuItem(
                            text = { Text(text = team.name) },
                            onClick = {
                                teamAName = team.name
                                teamAColor = team.color
                                expandedA = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para Equipo B
            Text(text = "Equipo Visitante", color = ComposeColor.White)
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = teamBName,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Selecciona Equipo Visitante") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedB = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = ComposeColor(0xFFFFEB3B),
                        unfocusedBorderColor = ComposeColor(0xFF1976D2)
                    )
                )
                DropdownMenu(
                    expanded = expandedB,
                    onDismissRequest = { expandedB = false }
                ) {
                    teams.forEach { team ->
                        DropdownMenuItem(
                            text = { Text(text = team.name) },
                            onClick = {
                                teamBName = team.name
                                teamBColor = team.color
                                expandedB = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para crear un nuevo equipo
            Button(
                onClick = { navController.navigate("CreateTeam") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ComposeColor(0xFFFFEB3B),
                    contentColor = ComposeColor(0xFF1976D2)
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(text = "Crear Nuevo Equipo", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para continuar
            Button(
                onClick = {
                    if (teamAName.isNotEmpty() && teamBName.isNotEmpty()) {
                        navController.navigate("NewMatch/$teamAName/$teamBName")
                    }
                },
                enabled = teamAName.isNotEmpty() && teamBName.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ComposeColor(0xFFFFEB3B),
                    contentColor = ComposeColor(0xFF1976D2)
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(text = "Iniciar Partido", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón de "Atrás" estilizado al final de la pantalla
            Button(
                onClick = { navController.navigate("Menu") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ComposeColor(0xFF1976D2)) // Azul vibrante
            ) {
                Text(
                    text = "Atrás",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}
