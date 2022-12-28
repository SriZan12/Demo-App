package com.example.demoproject.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demoproject.data.models.JokesMainModel

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJokes(jokesList: List<JokesMainModel.JokesSecondaryModel>)

    @Query("SELECT * FROM JokesTable")
    fun getJokes(): LiveData<List<JokesMainModel.JokesSecondaryModel>>
}