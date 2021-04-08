package com.whayway.beerrandom.scoreboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class ScoreboardViewModel(dataSource: ScoreBoardDao, application: Application) : ViewModel() {

     val database = dataSource

    val allResults = database.getAllScores()

    init{

    }

    val xd: Int
        get() {
            TODO()
        }

/*
    private fun getScore(db: ScoreBoardDao){
        viewModelScope.launch(Dispatchers.IO){
            getScoreFromDB(db)

        }
    }
     suspend fun getScoreFromDB(dataBase: ScoreBoardDao): LiveData<List<ScoreBoard>> {

        var list = dataBase?.getAllScores()
        var myDataLists: List<ScoreBoard>

        var dupa: String
        return list
    }*/
     fun generateList(): LiveData<List<ScoreBoard>>{
        val list = database.getAllScores()

        return list

    }



}