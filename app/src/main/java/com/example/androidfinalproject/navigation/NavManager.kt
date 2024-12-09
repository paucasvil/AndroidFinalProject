package com.example.androidfinalproject.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalproject.views.CreateTeamView
import com.example.androidfinalproject.views.EditTeamView
import com.example.androidfinalproject.views.EnterTeamNamesView
import com.example.androidfinalproject.views.MenuView
import com.example.androidfinalproject.views.NewMatchView
import com.example.androidfinalproject.views.ShowView
import com.example.androidfinalproject.views.TeamListView


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
        composable("NewMatch/{teamAName}/{teamBName}") { navBackStackEntry ->
            // Aquí recuperamos los parámetros
            val teamAName = navBackStackEntry.arguments?.getString("teamAName") ?: "Equipo A"
            val teamBName = navBackStackEntry.arguments?.getString("teamBName") ?: "Equipo B"
            // Llama a la función de la pantalla NewMatch
            NewMatchView(navController, teamAName, teamBName)
        }




        composable("TeamList") {
            TeamListView(navController)
        }
        composable("EditTeam/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toInt() ?: 0
            EditTeamView(navController, teamId)
        }
        composable("CreateTeam") {
            CreateTeamView(navController)
        }

    }
}