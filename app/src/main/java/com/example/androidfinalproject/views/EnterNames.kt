package com.example.androidfinalproject.views

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

@Composable
fun EnterTeamNamesView(navController: NavController) {
    // Estados para nombres de equipos
    var teamAName by remember { mutableStateOf("") }
    var teamBName by remember { mutableStateOf("") }

    // Opciones para seleccionar nombres
    val teamOptions = listOf("Equipo 1", "Equipo 2", "Equipo 3", "Equipo 4")
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
                onValueChange = { teamAName = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Selecciona Equipo Local") },
                readOnly = false,
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
                teamOptions.forEach { team ->
                    DropdownMenuItem(
                        text = {
                            Text(text = team) // El texto del ítem
                        },
                        onClick = {
                            teamAName = team
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
                onValueChange = { teamBName = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Selecciona Equipo Visitante") },
                readOnly = false,
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
                teamOptions.forEach { team ->
                    DropdownMenuItem(
                        text = {
                            Text(text = team) // El texto del ítem
                        },
                        onClick = {
                            teamBName = team
                            expandedB = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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
