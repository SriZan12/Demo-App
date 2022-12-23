package com.example.demoproject.repository

import android.util.Log
import com.example.demoproject.api_network.ApiInterface
import com.example.demoproject.api_network.RetrofitInstance
import com.example.demoproject.api_network.jokedto.JokesMainModel
import javax.inject.Inject

class JokesRepository @Inject constructor() {

    @Inject
    lateinit var retrofitInstance: RetrofitInstance
    private lateinit var apiInterface: ApiInterface
    private val TAG = "JokesRepo"

    suspend fun getAnyJokesList(): JokesMainModel {
        apiInterface =
            retrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        return apiInterface.getAnyJokes(10)
    }


    suspend fun getProgrammingJokesList(): JokesMainModel {
        apiInterface =
            retrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        return apiInterface.getProgrammingJokes(10)
    }

    suspend fun getMiscJokes(): JokesMainModel {
        apiInterface =
            retrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        Log.d(TAG, "getAnyJokesList: ${apiInterface.getAnyJokes(10).amount}")
        return apiInterface.getMiscJokes(10)
    }

}