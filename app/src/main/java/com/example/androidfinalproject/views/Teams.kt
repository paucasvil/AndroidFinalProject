package com.example.androidfinalproject.views

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.animateDpAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.model.Team
import com.example.androidfinalproject.viewmodels.TeamViewModel

@Composable
fun TeamListView(navController: NavController, viewModel: TeamViewModel = hiltViewModel()) {
    val teams by viewModel.teamList.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.volleyball_court2), // Cambia 'volleyball_court2' por tu archivo de imagen
            contentDescription = "Fondo de Voleibol",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp)) // Para mover el título más abajo

            Text(
                text = "Equipos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2), // Color azul de la temática de voleibol
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (teams.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(teams) { team ->
                        var isClicked by remember { mutableStateOf(false) }

                        // Animación de desplazamiento a la derecha
                        val offset by animateDpAsState(
                            targetValue = if (isClicked) 1000.dp else 0.dp, // Desplazamiento de 200dp a la derecha
                            animationSpec = tween(durationMillis = 300) // Duración de la animación
                        )

                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.elevatedCardElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    isClicked = !isClicked // Al hacer clic, cambia el estado
                                    navController.navigate("EditTeam/${team.id}")
                                }
                                .graphicsLayer { // Aplica el desplazamiento
                                    translationX = offset.toPx() // Desplazamiento en X
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(android.graphics.Color.parseColor(team.color))) // Fondo color del equipo
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Usamos el icono de Voleibol
                                Icon(
                                    imageVector = Icons.Filled.SportsVolleyball, // Icono de voleibol
                                    contentDescription = "Voleibol",
                                    modifier = Modifier.size(40.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.padding(start = 8.dp)) {
                                    Text(
                                        text = team.name,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Entrenador: ${team.coach}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "No hay equipos disponibles",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("CreateTeam") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(Color(0xFF1976D2)), // Azul para el botón
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "Crear un equipo",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
