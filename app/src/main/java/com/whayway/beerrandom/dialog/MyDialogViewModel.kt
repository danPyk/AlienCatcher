package com.whayway.beerrandom.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDialogViewModel(private val database: ScoreBoardDao) : ViewModel() {

    //todo
     private var  timeSpend = MutableLiveData<ScoreBoard>()

    init {

    }

    fun saveScore(score: Int, name: String, difficulty: String) {
        // launch a coroutine in the viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            val newScore = ScoreBoard()
            newScore.score_points = score
            newScore.score_name = name
            newScore.difficulty_level = difficulty

            insert(newScore)

        }
    }
    private suspend fun insert(score: ScoreBoard) {
        database.insert(score)
    }

    /**
     *  Handling the case of the stopped app or forgotten recording,
     *  the start and end times will be the same.
     *
     *  If the start time and end time are not the same, then we do not have an unfinished
     *  recording.
     */


    private suspend fun update(night: ScoreBoard) {
        database.update(night)
    }

/*
    fun onStop() {
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch().
            val oldNight = timeSpend.value ?: return@launch

            // Update the night in the database to add the end time.
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)


            // Set state to navigate to the SleepQualityFragment.
           // _navigateToSleepQuality.value = oldNight
        }
    }
*/
    /*    private suspend fun getTonightFromDatabase(): ScoreBoard? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            timeSpend.value = getTonightFromDatabase()
        }
    }*/



}