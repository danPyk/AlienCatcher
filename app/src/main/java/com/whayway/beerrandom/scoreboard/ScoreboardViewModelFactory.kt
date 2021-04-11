package com.whayway.beerrandom.scoreboard


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whayway.beerrandom.data.ScoreBoardDao

class ScoreboardViewModelFactory(
    private val dataSource: ScoreBoardDao
)
: ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreboardViewModel::class.java)) {
            return ScoreboardViewModel( dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
