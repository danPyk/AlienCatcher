package com.whayway.beerrandom.scoreboard

import androidx.lifecycle.ViewModel
import com.whayway.beerrandom.data.ScoreBoardDao

//todo change factory to DI
class ScoreboardViewModel (dataSource: ScoreBoardDao) : ViewModel() {

     private val database = dataSource

    val allResults = database.getAllScores()


}