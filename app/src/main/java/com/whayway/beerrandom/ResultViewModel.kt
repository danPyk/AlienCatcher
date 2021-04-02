package com.whayway.beerrandom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel: ViewModel() {
    // The final score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
     val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain
}