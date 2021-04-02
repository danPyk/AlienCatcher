package com.whayway.beerrandom.result

import android.app.Application
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.*
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.launch

class ResultViewModel(val database: ScoreBoardDao,
                      application: Application
) : AndroidViewModel(application){

    //hold current score
    private val scoreResult = MutableLiveData<ScoreBoard?>()
    private val allResults = database.getAllNights()

/*    val nightsString = Transformations.map(allResults) { allResults ->
        formatNights(allResults, application.resources)
    }*/
private val test =Int
    init {
        initializeResult()
    }
/*     val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain*/
    private fun initializeResult() {
        viewModelScope.launch {
            scoreResult.value = getTonightFromDatabase()
        }
    }
    private suspend fun getTonightFromDatabase(): ScoreBoard? {
        var night = database.getTonight()
        return night
    }
    suspend fun callGetAllNights(){
        val singleScore = database.getAllNights()
    }
/*    fun onStartTracking() {
        // launch a coroutine in the viewModelScope
        viewModelScope.launch {
            val newScore = ScoreBoard(1, 10, 0, 0)
            //THATS NOT dao FUN WITH SAME NAME
            insert(newScore)
            //update tonight.
            scoreResult.value = getTonightFromDatabase()
        }
    }*/
    private suspend fun insert(score: ScoreBoard) {
        database.insert(score)
    }
/*    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = scoreResult.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }*/
}