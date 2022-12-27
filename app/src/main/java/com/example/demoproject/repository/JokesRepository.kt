package com.example.demoproject.repository

import android.util.Log
import com.example.demoproject.api_network.ApiInterface
import com.example.demoproject.api_network.jokedto.JokesMainModel
import com.example.demoproject.di.NetworkModule
import javax.inject.Inject

class JokesRepository @Inject constructor() {

    private lateinit var apiInterface: ApiInterface
    private val TAG = "JokesRepo"

    suspend fun getAnyJokesList(): JokesMainModel {
        apiInterface =
            NetworkModule.getRetrofitInstance()
        return apiInterface.getAnyJokes(10)
    }


    suspend fun getProgrammingJokesList(): JokesMainModel {
        apiInterface =
            NetworkModule.getRetrofitInstance()
        return apiInterface.getProgrammingJokes(10)
    }

    suspend fun getMiscJokes(): JokesMainModel {
        apiInterface =
            NetworkModule.getRetrofitInstance()
        Log.d(TAG, "getAnyJokesList: ${apiInterface.getAnyJokes(10).amount}")
        return apiInterface.getMiscJokes(10)
    }

}