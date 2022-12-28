package com.example.demoproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.demoproject.data.room.JokesDao
import com.example.demoproject.data.room.JokesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun getDatabase(application: Application): JokesDatabase {
        kotlin.synchronized(this) {
            return Room.databaseBuilder(
                application.applicationContext, JokesDatabase::class.java, "Jokes_Table"
            ).build()
        }
    }

    @Provides
    fun providesDao(jokesDatabase: JokesDatabase): JokesDao {
        return jokesDatabase.jokesDao()
    }
}