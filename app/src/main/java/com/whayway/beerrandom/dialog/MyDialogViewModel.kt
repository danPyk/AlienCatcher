package com.whayway.beerrandom.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDialogViewModel(val database: ScoreBoardDao) : ViewModel() {

    init {
    //  initializeResult().toString()
    }
/*    val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain*/

    private var scoreResult = MutableLiveData<ScoreBoard?>()

/*    private fun initializeResult() {
        viewModelScope.launch(Dispatchers.IO) {
            scoreResult.postValue( getScoretFromDatabase())
        }
    }*/
    private suspend fun getScoretFromDatabase(): ScoreBoard? {
        var night = database.getTonight()
        return night
    }
    suspend fun callGetAllNights(){
        val singleScore = database.getAllNights()
    }
    fun onStartTracking() {
        // launch a coroutine in the viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            val newScore = ScoreBoard(1, 10)
            //THATS NOT dao FUN WITH SAME NAME
            insert(newScore)
            //update tonight.
           // scoreResult.value = getTonightFromDatabase()
        }
    }
    private suspend fun insert(score: ScoreBoard) {
        database.insert(score)
    }
 /*   fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = scoreResult.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }*/
}