package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun NewMatchView(navController: NavController, teamA: String, teamB: String) {
    val teamAPoints = remember { mutableStateOf(0) }
    val teamBPoints = remember { mutableStateOf(0) }
    val currentServer = remember { mutableStateOf(teamA) }

    // Colores personalizados
    val lightBlue = Color(0xFFADD8E6)
    val lightGreen = Color(0xFF90EE90)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Equipo A (mitad superior)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(lightBlue)
                .clickable {
                    if (currentServer.value == teamA) {
                        teamAPoints.value += 1
                    } else {
                        currentServer.value = teamA
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = teamA, fontSize = 24.sp, color = Color.White)
                Text(text = "Puntos: ${teamAPoints.value}", fontSize = 20.sp, color = Color.White)
                if (currentServer.value == teamA) {
                    Text(text = "Saque", fontSize = 18.sp, color = Color.White)
                }
            }
        }

        // Equipo B (mitad inferior)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(lightGreen)
                .clickable {
                    if (currentServer.value == teamB) {
                        teamBPoints.value += 1
                    } else {
                        currentServer.value = teamB
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = teamB, fontSize = 24.sp, color = Color.White)
                Text(text = "Puntos: ${teamBPoints.value}", fontSize = 20.sp, color = Color.White)
                if (currentServer.value == teamB) {
                    Text(text = "Saque", fontSize = 18.sp, color = Color.White)
                }
            }
        }

        // Botón de finalizar partido
        Button(
            onClick = {
                navController.navigate("Menu")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Finalizar Partido")
        }
    }
}
