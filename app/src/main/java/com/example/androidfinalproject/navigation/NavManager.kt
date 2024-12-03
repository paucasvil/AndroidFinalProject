package com.example.androidfinalproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalproject.views.EnterTeamNamesView
import com.example.androidfinalproject.views.MenuView
import com.example.androidfinalproject.views.NewMatchView
import com.example.androidfinalproject.views.ShowView


@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Menu") {
        composable("Menu") {
            MenuView(navController)
        }
        composable("Names") {
            EnterTeamNamesView(navController)
        }
        composable("Show") {
            ShowView(navController)
        }
        composable("NewMatch/{teamA}/{teamB}") { backStackEntry ->
            val teamA = backStackEntry.arguments?.getString("teamA") ?: "Equipo A"
            val teamB = backStackEntry.arguments?.getString("teamB") ?: "Equipo B"
            NewMatchView(navController, teamA, teamB)
        }

    }
}
