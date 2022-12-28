package com.example.demoproject.di

import android.util.Log
import androidx.databinding.ktx.BuildConfig
import com.example.demoproject.objects.Constants
import com.example.demoproject.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TAG = "Retrofit"

    @Provides
    fun getRetrofitInstance(): ApiInterface {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

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