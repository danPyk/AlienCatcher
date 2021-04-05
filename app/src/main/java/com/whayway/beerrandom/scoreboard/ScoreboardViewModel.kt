package com.whayway.beerrandom.scoreboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreboardViewModel(dataSource: ScoreBoardDao) : ViewModel() {

     val database = dataSource

     val nights = onStartTracking()


     fun onStartTracking() {
          // launch a coroutine in the viewModelScope
          viewModelScope.launch(Dispatchers.IO) {
               insert()

          }
     }
     private suspend fun insert() {
          database.getAllScores()
     }


}