package com.example.androidfinalproject.views

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidfinalproject.model.Team
import com.example.androidfinalproject.viewmodels.TeamViewModel
import androidx.hilt.navigation.compose.hiltViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Selecciona los nombres de los equipos", fontSize = 20.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdown para Equipo A
        Text(text = "Equipo local")
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = teamAName,
                onValueChange = {  },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Selecciona Equipo Local") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedA = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedA,
                onDismissRequest = { expandedA = false }
            ) {
                teams.forEach { team ->
                    DropdownMenuItem(
                        text = {
                            Text(text = team.name)
                        },
                        onClick = {
                            teamAName = team.name
                            teamAColor = team.color // Asignar el color del equipo
                            expandedA = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para Equipo B
        Text(text = "Equipo Visitante")
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = teamBName,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Selecciona Equipo Visitante") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedB = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedB,
                onDismissRequest = { expandedB = false }
            ) {
                teams.forEach { team ->
                    DropdownMenuItem(
                        text = {
                            Text(text = team.name)
                        },
                        onClick = {
                            teamBName = team.name
                            teamBColor = team.color // Asignar el color del equipo
                            expandedB = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para crear un nuevo equipo
        Button(
            onClick = {
                navController.navigate("CreateTeam") // Navegar a la vista de crear equipo
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear Nuevo Equipo")
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar Partido")
        }

    }
}
