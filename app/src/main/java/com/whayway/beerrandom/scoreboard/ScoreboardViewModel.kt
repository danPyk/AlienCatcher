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

class ScoreboardViewModel(dataSource: ScoreBoardDao) : ViewModel() {

     private val database = dataSource

/*
    val allResults = database.getAllScores()
*/


}