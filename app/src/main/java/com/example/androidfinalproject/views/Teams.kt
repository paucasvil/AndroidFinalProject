package com.example.androidfinalproject.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun TeamListView(navController: NavController, viewModel: TeamViewModel = hiltViewModel()) {
    // Recolectar el estado de los equipos de forma reactiva
    val teams by viewModel.teamList.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Equipos", fontSize = 24.sp)

        if (teams.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(teams) { team ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color(android.graphics.Color.parseColor(team.color)))
                            .clickable {
                                navController.navigate("EditTeam/${team.id}")
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = team.name, fontSize = 20.sp, color = Color.White)
                            Text(text = "Entrenador: ${team.coach}", fontSize = 16.sp, color = Color.White)
                        }
                    }
                }
            }
        } else {
            Text(text = "No hay equipos disponibles", fontSize = 20.sp)
            Button(
                onClick = { navController.navigate("CreateTeam") },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(text = "Crear un equipo")
            }
        }


    }
}
