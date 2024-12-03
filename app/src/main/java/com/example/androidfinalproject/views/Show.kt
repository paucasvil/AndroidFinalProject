package com.example.androidfinalproject.views

import Match
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun ShowView() {
    val matches = remember { mutableStateOf(listOf<Match>()) }

    LaunchedEffect(Unit) {
        matches.value = VolleyballApp.database.matchDatabaseDao().getAllMatches()
    }

    LazyColumn {
        items(matches.value) { match ->
            Text("${match.teamA} vs ${match.teamB} - ${match.teamAScore}:${match.teamBScore} (${match.date})")
        }
    }
}
