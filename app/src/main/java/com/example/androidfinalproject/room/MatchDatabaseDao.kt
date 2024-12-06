package com.example.androidfinalproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidfinalproject.model.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDatabaseDao {
    // CRUD

    // Obtener todos los partidos
    @Query("SELECT * FROM matches")
    fun getMatches(): Flow<List<Match>>

    // Obtener un partido por ID
    @Query("SELECT * FROM matches WHERE id = :id")
    fun getMatchById(id: Int): Flow<Match>

    // Insertar un nuevo partido
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    // Actualizar un partido existente
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(match: Match)

    // Eliminar un partido
    @Delete
    suspend fun delete(match: Match)
}