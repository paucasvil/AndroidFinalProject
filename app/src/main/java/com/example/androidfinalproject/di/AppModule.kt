package com.example.androidfinalproject.di

import android.content.Context
import androidx.room.Room
import com.example.androidfinalproject.room.MatchDatabase
import com.example.androidfinalproject.room.MatchDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proveer el DAO MatchDatabaseDao
    @Singleton
    @Provides
    fun provideMatchDao(matchDatabase: MatchDatabase): MatchDatabaseDao {
        return matchDatabase.matchDatabaseDao()
    }

    // Proveer la base de datos MatchDatabase
    @Singleton
    @Provides
    fun provideMatchDatabase(@ApplicationContext context: Context): MatchDatabase {
        return Room.databaseBuilder(
            context,
            MatchDatabase::class.java,
            "match_db" // Nombre de la base de datos
        ).fallbackToDestructiveMigration()
            .build()
    }
}