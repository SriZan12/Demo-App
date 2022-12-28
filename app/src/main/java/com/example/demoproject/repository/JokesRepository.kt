package com.example.demoproject.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.demoproject.data.remote.ApiInterface
import com.example.demoproject.data.models.JokesMainModel
import com.example.demoproject.data.room.JokesDao
import com.example.demoproject.data.room.JokesDatabase
import com.example.demoproject.di.DatabaseModule
import com.example.demoproject.di.NetworkModule
import javax.inject.Inject

class JokesRepository @Inject constructor() {

    @Inject
    lateinit var apiInterface: ApiInterface
    private val TAG = "JokesRepo"

    @Inject
    lateinit var jokesDao: JokesDao

    suspend fun getAnyJokesList(): JokesMainModel {
        return apiInterface.getAnyJokes(10)
    }


    suspend fun getProgrammingJokesList(): JokesMainModel {
        return apiInterface.getProgrammingJokes(10)
    }

    suspend fun getMiscJokes(): JokesMainModel {
        Log.d(TAG, "getAnyJokesList: ${apiInterface.getAnyJokes(10).amount}")
        return apiInterface.getMiscJokes(10)
    }


    suspend fun saveTheJokes() {

        jokesDao.saveJokes(apiInterface.getAnyJokes(10).jokesList!!)
    }

    fun getSavedJokes(): LiveData<List<JokesMainModel.JokesSecondaryModel>> {
        return jokesDao.getJokes()
    }

}