package com.example.androidfinalproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidfinalproject.model.Match

@Database(entities = [Match::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDatabaseDao(): MatchDatabaseDao
}