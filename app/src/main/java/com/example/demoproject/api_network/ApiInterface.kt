package com.example.demoproject.api_network

import com.example.demoproject.api_network.jokedto.JokesMainModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("Any?")
    suspend fun getAnyJokes(
        @Query("amount") amount: Int
    ): JokesMainModel


    @GET("Programming?")
    suspend fun getProgrammingJokes(
        @Query("amount") amount: Int
    ): JokesMainModel

    @GET("Misc?")
    suspend fun getMiscJokes(
        @Query("amount") amount: Int
    ): JokesMainModel

}

