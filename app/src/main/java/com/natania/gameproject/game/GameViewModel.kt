package com.natania.gameproject.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    private val _score = MutableLiveData<Int>()

    val scoree : LiveData<Int>
        get() = _score

    override fun onCleared(){
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    fun onWrong(){
        _score.value = (_score.value)?.minus(1)
    }
    fun onCorrectt(){
        _score.value = (_score.value)?.plus(1)
    }

    var score = 0

}