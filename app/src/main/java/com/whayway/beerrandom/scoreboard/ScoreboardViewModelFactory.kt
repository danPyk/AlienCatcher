package com.whayway.beerrandom.scoreboard


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whayway.beerrandom.data.ScoreBoardDao

class ScoreboardViewModelFactory   (private val dataSource: ScoreBoardDao,
                                    private val application: Application
)
: ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreboardViewModel::class.java)) {
            return ScoreboardViewModel( dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
