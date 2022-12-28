package com.example.demoproject.ui.jokelist


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoproject.data.models.JokesMainModel
import com.example.demoproject.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var jokesRepository: JokesRepository
    private val TAG: String = "JokesModelView"

    fun getAnyJokes() = liveData {
        try {
            emit(jokesRepository.getAnyJokesList())
            val res = jokesRepository.getAnyJokesList().jokesList?.size
            Log.d(TAG, "getAllJokes: Responded")
            Log.d(TAG, "getAllJokes: $res")
        } catch (exception: Exception) {
            Log.d(TAG, "getAllJokes:Not Responded")
            Log.d(TAG, "getAllJokes: error $exception")
            exception.printStackTrace()
        }
    }


    fun getProgrammingJokes() = liveData {
        try {
            emit(jokesRepository.getProgrammingJokesList())
            val res = jokesRepository.getProgrammingJokesList().jokesList?.size
            Log.d(TAG, "getAllJokes: Responded")
            Log.d(TAG, "getAllJokes: $res")
        } catch (exception: Exception) {
            Log.d(TAG, "getAllJokes:Not Responded")
            Log.d(TAG, "getAllJokes: error $exception")
            exception.printStackTrace()
        }
    }

    fun getMiscJokes() = liveData {
        try {
            emit(jokesRepository.getMiscJokes())
        } catch (exception: Exception) {
            Log.d(TAG, "getAllJokes:Not Responded")
            Log.d(TAG, "getAllJokes: error $exception")
            exception.printStackTrace()
        }
    }

    fun getJokesList() = liveData {
        try {
            emit(jokesRepository.saveTheJokes())
        } catch (exception: Exception) {
            exception.message
        }
    }

    fun getSavedJokes(): LiveData<List<JokesMainModel.JokesSecondaryModel>> {
        return jokesRepository.getSavedJokes()
    }


}

