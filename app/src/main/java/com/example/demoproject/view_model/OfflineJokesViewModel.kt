package com.example.demoproject.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.example.demoproject.api_network.jokedto.JokesSecondaryModel
import com.example.demoproject.repository.OfflineJokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfflineJokesViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var offlineJokesRepository: OfflineJokesRepository

    fun initializeDatabase(context: Context){
        offlineJokesRepository.initializeDatabase(context)
    }

    fun getJokesList() = liveData {
        try {
            emit(offlineJokesRepository.saveTheJokes())
        }catch (exception:Exception){
            exception.message
        }
    }

    fun getSavedJokes(): LiveData<List<JokesSecondaryModel>> {
        return offlineJokesRepository.getSavedJokes()
    }


}