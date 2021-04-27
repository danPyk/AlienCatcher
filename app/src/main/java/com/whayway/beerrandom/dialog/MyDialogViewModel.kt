package com.whayway.beerrandom.dialog

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MyDialogViewModel(private val database: ScoreBoardDao, application: Application) : ViewModel() {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val videosRepository = DatabaseRepository(ScoreDatabase.getInstance(application))
   // val playlist = videosRepository.videos


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

            try{
                videosRepository.repoCall(score)
            }catch(e: Error){

            }

        //database.insert(score)
    }

/*

    private suspend fun update(night: ScoreBoard) {
        database.update(night)
    }
*/



}