package com.example.demoproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demoproject.api_network.jokedto.JokesSecondaryModel

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJokes(jokesList: List<JokesSecondaryModel>)

    @Query("SELECT * FROM JokesTable")
    fun getJokes(): LiveData<List<JokesSecondaryModel>>
}