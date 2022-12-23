package com.example.demoproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoproject.api_network.jokedto.JokesSecondaryModel


@Database(
    entities = [JokesSecondaryModel::class],
    version = 2
)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun jokes(): JokesDao

    companion object {

        @Volatile
        private var Instance: JokesDatabase? = null

        fun getDatabase(context: Context): JokesDatabase {
            if (Instance == null) {
                kotlin.synchronized(this) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        JokesDatabase::class.java, "Jokes_Table"
                    ).build()
                }
            }
            return Instance!!
        }
    }
}