package com.example.demoproject.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [JokesSecondaryModel::class],
    version = 2
)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun jokes(): JokesDao

    companion object {

        @Volatile
        private var Instance: JokesDatabase? = null

        fun getDatabase(application: Application): JokesDatabase {
            if (Instance == null) {
                kotlin.synchronized(this) {
                    Instance = Room.databaseBuilder(
                        application.applicationContext,
                        JokesDatabase::class.java, "Jokes_Table"
                    ).build()
                }
            }
            return Instance!!
        }
    }
}