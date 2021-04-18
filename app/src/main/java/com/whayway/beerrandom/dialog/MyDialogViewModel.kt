package com.whayway.beerrandom.dialog

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDialogViewModel(private val database: ScoreBoardDao) : ViewModel() {


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

/*

    private suspend fun update(night: ScoreBoard) {
        database.update(night)
    }
*/



}