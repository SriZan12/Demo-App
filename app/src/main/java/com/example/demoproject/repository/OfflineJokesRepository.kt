package com.example.demoproject.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demoproject.api_network.ApiInterface
import com.example.demoproject.api_network.jokedto.JokesSecondaryModel
import com.example.demoproject.database.JokesDao
import com.example.demoproject.database.JokesDatabase
import com.example.demoproject.di.NetworkModule
import javax.inject.Inject

class OfflineJokesRepository @Inject constructor() {

    private lateinit var apiInterface: ApiInterface
    private lateinit var jokesDao: JokesDao
    private lateinit var jokesDatabase: JokesDatabase
    private lateinit var jokesList: List<JokesSecondaryModel>

    fun initializeDatabase(application: Application) {
        jokesDatabase = JokesDatabase.getDatabase(application)
        jokesDao = jokesDatabase.jokes()
    }

    suspend fun saveTheJokes() {
        apiInterface =
            NetworkModule.getRetrofitInstance()
        jokesList = apiInterface.getAnyJokes(10).jokesList!!
        jokesDao.saveJokes(jokesList)
    }

    fun getSavedJokes(): LiveData<List<JokesSecondaryModel>> {
        return jokesDao.getJokes()
    }


}