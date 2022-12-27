package com.example.demoproject.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.example.demoproject.api_network.jokedto.JokesSecondaryModel
import com.example.demoproject.repository.OfflineJokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfflineJokesViewModel @Inject constructor(application: Application) : AndroidViewModel(
    application
) {

    @Inject
    lateinit var offlineJokesRepository: OfflineJokesRepository

    fun initializeDatabase(application: Application) {
        offlineJokesRepository.initializeDatabase(application)
    }

    fun getJokesList() = liveData {
        try {
            emit(offlineJokesRepository.saveTheJokes())
        } catch (exception: Exception) {
            exception.message
        }
    }

    fun getSavedJokes(): LiveData<List<JokesSecondaryModel>> {
        return offlineJokesRepository.getSavedJokes()
    }


}