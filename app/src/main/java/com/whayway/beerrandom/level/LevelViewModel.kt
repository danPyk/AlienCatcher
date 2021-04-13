package com.whayway.beerrandom.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LevelViewModel : ViewModel() {

    lateinit var difficulty: String

    var _time = MutableLiveData<Long>()
     val time: LiveData<Long>
         get() = _time

    init{
        //_time.value = 5000L
    }

}