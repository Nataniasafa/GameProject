package com.natania.gameproject.endgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EndGameViewModelFactory(private val finalScore : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndGameViewModel::class.java)){
            return EndGameViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Anonym View Model Class")
    }
}