package com.example.androidfinalproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var coach: String,
    var color: String
)
