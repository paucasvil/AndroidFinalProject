package com.example.androidfinalproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidfinalproject.model.Match
import com.example.androidfinalproject.model.Team

@Database(entities = [Match::class, Team::class], version = 2, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDatabaseDao(): MatchDatabaseDao
    abstract fun teamDao(): TeamDao
}