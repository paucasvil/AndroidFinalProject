package com.example.androidfinalproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidfinalproject.model.Team
import kotlinx.coroutines.flow.Flow
@Dao
interface TeamDao {
    @Query("SELECT * FROM Team")
    fun getAllTeams(): Flow<List<Team>>

    @Query("SELECT * FROM Team WHERE id = :id")
    fun getTeamById(id: Int): Flow<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM Team WHERE name = :name")
    fun getTeamsByName(name: String): Flow<List<Team>>
}
