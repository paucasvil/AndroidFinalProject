package com.example.androidfinalproject.repository


import com.example.androidfinalproject.model.Match
import com.example.androidfinalproject.room.MatchDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MatchRepository @Inject constructor(private val matchDatabaseDao: MatchDatabaseDao) {

    // Agregar un partido
    suspend fun addMatch(match: Match) = matchDatabaseDao.insert(match)

    // Actualizar un partido
    suspend fun updateMatch(match: Match) = matchDatabaseDao.update(match)

    // Eliminar un partido
    suspend fun deleteMatch(match: Match) = matchDatabaseDao.delete(match)

    // Obtener todos los partidos
    fun getAllMatches(): Flow<List<Match>> = matchDatabaseDao.getMatches()
        .flowOn(Dispatchers.IO)
        .conflate()

    // Obtener un partido por ID
    fun getMatchById(id: Int): Flow<Match> = matchDatabaseDao.getMatchById(id)
        .flowOn(Dispatchers.IO)
        .conflate()
}