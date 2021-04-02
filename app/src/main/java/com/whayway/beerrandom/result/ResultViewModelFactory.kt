package com.whayway.beerrandom.result

import android.app.Application
import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whayway.beerrandom.data.ScoreBoardDao

class ResultViewModelFactory(
    private val dataSource: ScoreBoardDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(dataSource, application) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
}
}
