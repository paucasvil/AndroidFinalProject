package com.example.androidfinalproject.views

import Match
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
import kotlinx.coroutines.launch

@Composable
fun NewMatchView(navController: NavController, teamAName: String, teamBName: String) {
    var teamAScore by remember { mutableStateOf(0) }
    var teamBScore by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Equipo : $teamAName - Puntuación: $teamAScore", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Equipo : $teamBName - Puntuación: $teamBScore", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { teamAScore++ }) {
            Text("Sumar punto a $teamAName")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { teamBScore++ }) {
            Text("Sumar punto a $teamBName")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val match = Match(
                teamA = teamAName,
                teamB = teamBName,
                teamAScore = teamAScore,
                teamBScore = teamBScore,
                date = System.currentTimeMillis().toString()
            )

            coroutineScope.launch {
                VolleyballApp.database.matchDatabaseDao().insertMatch(match)
                navController.navigate("Menu")
            }
        }) {
            Text("Finalizar Partido y Guardar")
        }
    }
}
