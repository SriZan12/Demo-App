package com.example.demoproject.api_network


import android.util.Log
import com.example.demoproject.Objects.Constants
import com.example.demoproject.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor() {

    private lateinit var retrofit: Retrofit
    private val TAG = "Retrofit"

    fun getRetrofitInstance(): Retrofit {
        retrofit = Retrofit.Builder().client(OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(TAG, "provideHttpLoggingInterceptor: $message")
        }
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

}