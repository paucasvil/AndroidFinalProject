package com.example.androidfinalproject.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.R // Asegúrate de tener la imagen en res/drawable
import com.example.androidfinalproject.model.Match
import com.example.androidfinalproject.viewmodels.MatchViewModel

@Composable
fun ShowView(navController: NavController, viewModel: MatchViewModel = hiltViewModel()) {
    val matches by viewModel.matchList.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.volleyball_hands), // Cambia por el nombre de tu recurso
            contentDescription = "Fondo de voleibol",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Encabezado: "Partidos Previos"
            Text(
                text = "Partidos Previos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2), // Azul vibrante
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)

                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Comprobar si la lista está vacía
            if (matches.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay partidos disponibles.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            } else {
                // Lista estilizada de partidos
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp) // Espaciado adicional en la parte superior
                ) {
                    items(matches) { match ->
                        MatchCard(match = match)
                    }
                }
            }

            // Botón de "Atrás" estilizado al final de la pantalla
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
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MatchCard(match: Match) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDEC70), // Fondo amarillo
            contentColor = Color.Black // Texto negro para contraste
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp) // Margen externo
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Encabezado con un ícono decorativo y el título
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.SportsVolleyball,
                    contentDescription = "Ícono de voleibol",
                    tint = Color(0xFF1976D2), // Azul vibrante
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Partido",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1976D2)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Detalles de los equipos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = match.teamA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2) // Azul para equipo A
                )
                Text(
                    text = match.teamAScore.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = match.teamB,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F) // Rojo para equipo B
                )
                Text(
                    text = match.teamBScore.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Fecha decorada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        color = Color(0xFF1976D2).copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Fecha: ${match.date}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1976D2)
                )
            }
        }
    }
}

