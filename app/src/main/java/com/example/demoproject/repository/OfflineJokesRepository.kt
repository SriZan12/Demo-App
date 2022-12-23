package com.example.demoproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.demoproject.api_network.ApiInterface
import com.example.demoproject.api_network.RetrofitInstance
import com.example.demoproject.api_network.jokedto.JokesSecondaryModel
import com.example.demoproject.database.JokesDao
import com.example.demoproject.database.JokesDatabase
import javax.inject.Inject

class OfflineJokesRepository @Inject constructor() {

    @Inject
    lateinit var retrofitInstance: RetrofitInstance
    private lateinit var apiInterface: ApiInterface
    private lateinit var jokesDao: JokesDao
    private lateinit var jokesDatabase: JokesDatabase
    private lateinit var jokesList: List<JokesSecondaryModel>

    fun initializeDatabase(context: Context) {
        jokesDatabase = JokesDatabase.getDatabase(context = context)
        jokesDao = jokesDatabase.jokes()
    }

    suspend fun saveTheJokes() {
        apiInterface =
            retrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        jokesList = apiInterface.getAnyJokes(10).jokesList!!
        jokesDao.saveJokes(jokesList)
    }

    fun getSavedJokes(): LiveData<List<JokesSecondaryModel>> {
        return jokesDao.getJokes()
    }



}