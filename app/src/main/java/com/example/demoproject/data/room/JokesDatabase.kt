package com.example.demoproject.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoproject.data.models.JokesMainModel


@Database(
    entities = [JokesMainModel.JokesSecondaryModel::class],
    version = 2
)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun jokesDao(): JokesDao
}