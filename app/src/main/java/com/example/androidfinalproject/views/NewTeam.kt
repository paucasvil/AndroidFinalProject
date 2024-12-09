package com.example.androidfinalproject.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.androidfinalproject.R
import com.example.androidfinalproject.model.Team
import com.example.androidfinalproject.viewmodels.TeamViewModel
//import com.google.android.gms.maps.model.Circle
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.graphicsLayer
import java.time.format.TextStyle

@Composable
fun CreateTeamView(navController: NavController, viewModel: TeamViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var coach by remember { mutableStateOf("") }
    val selectedColor = remember { mutableStateOf(Color.White) }
    val openDialog = remember { mutableStateOf(false) }

    // Lottie animation for the volleyball team
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.volleyball_scene))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)

    // Animations for title (fade-in + slide-up)
    val titleAlpha = remember { Animatable(0f) }
    val titleOffset = remember { Animatable(20f) }

    LaunchedEffect(Unit) {
        // Animations to fade and slide the title
        titleAlpha.animateTo(1f, animationSpec = tween(durationMillis = 3000))
        titleOffset.animateTo(0f, animationSpec = tween(durationMillis = 3000))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaciado superior para bajar el título
        Spacer(modifier = Modifier.height(80.dp)) // Ajusta el espaciado según sea necesario

        // Título de la pantalla con animación
        Box(
            modifier = Modifier
                .graphicsLayer(
                    alpha = titleAlpha.value, // Desvanecimiento
                    translationY = titleOffset.value // Desplazamiento
                )
        ) {
            Text(
                text = "Crear Equipo",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(bottom = 24.dp),
                fontFamily = FontFamily.Serif
            )
        }

        // Espaciado superior para los campos y botones
        Spacer(modifier = Modifier.height(40.dp)) // Aquí puedes aumentar o disminuir la distancia

        // Campo para el nombre del equipo
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Equipo", color = Color(0xFF1976D2)) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F1F1))
                .border(2.dp, Color(0xFF1976D2)),
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
        )

        // Campo para el nombre del entrenador
        OutlinedTextField(
            value = coach,
            onValueChange = { coach = it },
            label = { Text("Nombre del Entrenador", color = Color(0xFF1976D2)) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F1F1))
                .border(2.dp, Color(0xFF1976D2)),
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
        )

        // Botón para abrir el selector de color
        Button(
            onClick = { openDialog.value = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(60.dp)
                .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
            colors = buttonColors(
                containerColor = Color(0xFFFFEB3B)
            )
        ) {
            Text("Seleccionar Color", color = Color.Black, fontSize = 16.sp)
        }

        // Mostrar el color seleccionado (ahora en un círculo)
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(selectedColor.value, shape = CircleShape)
                .border(2.dp, Color.Black)
                .padding(2.dp)
        )

        // Dialog para seleccionar color
        if (openDialog.value) {
            ColorPickerDialog(
                selectedColor = selectedColor,
                onDismiss = { openDialog.value = false }
            )
        }

        // Botón para crear el equipo
        Button(
            onClick = {
                if (name.isNotEmpty() && coach.isNotEmpty()) {
                    val newTeam = Team(
                        name = name,
                        coach = coach,
                        color = selectedColor.value.toHex()
                    )
                    viewModel.addTeam(newTeam)
                    navController.navigate("TeamList")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(60.dp)
                .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
            colors = buttonColors(
                containerColor = Color(0xFFFFEB3B)
            )
        ) {
            Text("Crear Equipo", color = Color.Black, fontSize = 16.sp)
        }

        // Espaciado para empujar la animación hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Lottie Animation (Volleyball) en la parte inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(top = 24.dp)
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}



@Composable
fun ColorPickerDialog(
    selectedColor: MutableState<Color>,
    onDismiss: () -> Unit
) {
    val colors = listOf(
        Color(0xFFFF0000), // Red
        Color(0xFF00FF00), // Green
        Color(0xFF0000FF), // Blue
        Color(0xFFFFFF00), // Yellow
        Color(0xFF00FFFF), // Cyan
        Color(0xFFFF00FF), // Magenta
        Color(0xFF808080), // Gray
        Color(0xFF000000), // Black
        Color(0xFFFFFFFF), // White
        Color(0xFFFFA500), // Orange
        Color(0xFF800080), // Purple
        Color(0xFF008080), // Teal
        Color(0xFF4682B4), // Steel Blue
        Color(0xFFFFC0CB), // Pink
        Color(0xFFADFF2F), // Green Yellow
        Color(0xFF5F9EA0), // Cadet Blue
        Color(0xFF6495ED), // Cornflower Blue
        Color(0xFFFFD700), // Gold
        Color(0xFFDC143C), // Crimson
        Color(0xFFB22222), // Firebrick
        Color(0xFF2E8B57), // Sea Green
        Color(0xFF00CED1), // Dark Turquoise
        Color(0xFF6A5ACD), // Slate Blue
        Color(0xFFDA70D6), // Orchid
        Color(0xFFF08080), // Light Coral
        Color(0xFF8B4513), // Saddle Brown
        Color(0xFF00FA9A), // Medium Spring Green
        Color(0xFF191970), // Midnight Blue
        Color(0xFFB8860B), // Dark Goldenrod
        Color(0xFF4169E1), // Royal Blue
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Selecciona un color",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título estilizado
                Text(
                    "Elige un color:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Cuadrícula de colores
                Column {
                    for (i in colors.indices step 5) { // 5 colores por fila
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            colors.subList(i, (i + 5).coerceAtMost(colors.size)).forEach { color ->
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(
                                            color = color,
                                            shape = RoundedCornerShape(12.dp) // Bordes redondeados
                                        )
                                        .border(
                                            width = 3.dp,
                                            color = if (selectedColor.value == color) Color.Black else Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .clickable { selectedColor.value = color }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp)) // Espaciado entre filas
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .background(Color(0xFF1976D2), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Aceptar", color = Color.White, fontSize = 16.sp)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Cancelar", color = Color.White, fontSize = 16.sp)
            }
        },
        shape = RoundedCornerShape(16.dp), // Bordes redondeados del cuadro de diálogo
        containerColor = Color(0xFFF5F5F5) // Fondo claro del cuadro de diálogo
    )
}

// Extensión para convertir Color a formato hexadecimal
fun Color.toHex(): String {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    return String.format("#%02X%02X%02X", red, green, blue)
}
