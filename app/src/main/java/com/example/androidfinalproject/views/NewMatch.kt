package com.example.androidfinalproject.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.model.Match
import com.example.androidfinalproject.viewmodels.MatchViewModel
import java.text.SimpleDateFormat
import java.util.*
import com.example.androidfinalproject.viewmodels.TeamViewModel
import com.example.androidfinalproject.model.Team



@Composable
fun NewMatchView(navController: NavController, teamA: String, teamB: String,
                 viewModel: MatchViewModel = hiltViewModel(), viewModel2: TeamViewModel = hiltViewModel()) {

    val teamAPoints = remember { mutableStateOf(0) }
    val teamBPoints = remember { mutableStateOf(0) }
    val currentServer = remember { mutableStateOf(teamA) }

    val teamAColor = remember { mutableStateOf("#0000FF") } // Color por defecto
    val teamBColor = remember { mutableStateOf("#FFA500") } // Color por defecto
    LaunchedEffect(teamA) {
        viewModel2.getTeamColor(teamA).collect { color ->
            teamAColor.value = color
        }
    }

    LaunchedEffect(teamB) {
        viewModel2.getTeamColor(teamB).collect { color ->
            teamBColor.value = color
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        // Equipo A (mitad superior)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor(teamAColor.value)))
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
                .background(Color(android.graphics.Color.parseColor(teamBColor.value)))
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
                // Obtener la fecha actual
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                // Crear un objeto Match y agregarlo a la base de datos
                val newMatch = Match(
                    teamA = teamA,
                    teamB = teamB,
                    teamAScore = teamAPoints.value,
                    teamBScore = teamBPoints.value,
                    date = currentDate
                )
                viewModel.addMatch(newMatch)

                // Navegar de regreso al menú
                navController.navigate("Menu")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = buttonColors(
                containerColor = Color(0xFF1976D2), // Amarillo principal
                contentColor = Color.White // Texto negro
            )
        ) {
            Text(
                text = "Finalizar Partido",
                fontSize = 18.sp,
                fontWeight = Bold
            )

        }
    }
}