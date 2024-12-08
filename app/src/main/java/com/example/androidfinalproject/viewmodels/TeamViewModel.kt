package com.example.androidfinalproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.room.TeamDao
import com.example.androidfinalproject.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val teamDao: TeamDao) : ViewModel() {

    // MutableStateFlow que contendrá la lista de equipos
    private val _teamList = MutableStateFlow<List<Team>>(emptyList())
    val teamList: StateFlow<List<Team>> = _teamList

    init {
        // Obtener los equipos cuando se inicializa el ViewModel
        getTeams()
    }

    private fun getTeams() {
        viewModelScope.launch {
            // Recoger los equipos de la base de datos y actualizar el _teamList
            teamDao.getAllTeams().collect { teams ->
                _teamList.value = teams
            }
        }
    }

    fun addTeam(team: Team) {
        viewModelScope.launch {
            // Insertar el equipo en la base de datos
            teamDao.insertTeam(team)
        }
    }

    fun updateTeam(team: Team) {
        viewModelScope.launch {
            teamDao.updateTeam(team)
        }
    }

    // Función para obtener un equipo por su ID
    fun getTeamById(id: Int): Flow<Team> {
        return teamDao.getTeamById(id)
    }

    fun getTeamColor(name: String): Flow<String> {
        return teamDao.getTeamsByName(name).map { teams ->
            if (teams.isNotEmpty()) {
                // Si hay equipos con ese nombre, seleccionamos el color del primer equipo
                teams.first().color // O puedes usar otro criterio para seleccionar el equipo
            } else {
                "#000000" // Color predeterminado si no se encuentra ningún equipo
            }
        }
    }

}
