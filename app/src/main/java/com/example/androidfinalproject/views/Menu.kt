package com.example.androidfinalproject.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidfinalproject.R

// Composable para el botón con animación de rebote
@Composable
fun BouncingButton(onClick: () -> Unit, text: String) {
    // Transición infinita para el efecto de rebote
    val infiniteTransition = rememberInfiniteTransition()
    val bounce by infiniteTransition.animateFloat(
        initialValue = 1f, // Escala inicial
        targetValue = 1.1f, // Escala máxima (rebote)
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse // Rebote hacia adelante y atrás
        )
    )

    // Botón con escala animada
    Button(
        onClick = onClick,
        modifier = Modifier
            .scale(bounce) // Aplica la animación de escala
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFEB3B), // Fondo amarillo
            contentColor = Color(0xFF1976D2)  // Texto azul
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MenuView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.volleyball_court),
            contentDescription = "Fondo de cancha de voleibol",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título con fuente personalizada y sombra
            Text(
                text = "Menú",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(8.dp))
                   /* .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF000000), Color(0xFF817306))
                        ), shape = RoundedCornerShape(8.dp)
                    )*/
                    .padding(16.dp)
            )

            // Usando el nuevo BouncingButton
            BouncingButton(
                onClick = { navController.navigate("Names") },
                text = "Nuevo Partido"
            )

            BouncingButton(
                onClick = { navController.navigate("Show") },
                text = "Lista de Partidos"
            )

            BouncingButton(
                onClick = { navController.navigate("TeamList") },
                text = "Lista de equipos"
            )

            BouncingButton(
                onClick = { navController.navigate("CreateTeam") },
                text = "Crear un equipo"
            )
        }
    }
}
