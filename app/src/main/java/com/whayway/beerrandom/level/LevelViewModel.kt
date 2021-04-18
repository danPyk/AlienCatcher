package com.whayway.beerrandom.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LevelViewModel : ViewModel() {


    var _difficultyTime = MutableLiveData<Long>()
     val difficultyTime: LiveData<Long>
         get() = _difficultyTime



}