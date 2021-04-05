package com.whayway.beerrandom.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDialogViewModel(val database: ScoreBoardDao) : ViewModel() {


    fun onStartTracking(score: Int, name: String) {
        // launch a coroutine in the viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            val newScore = ScoreBoard()
            newScore.score_points = score
            newScore.score_name = name

            insert(newScore)

        }
    }
    private suspend fun insert(score: ScoreBoard) {
        database.insert(score)
    }

}