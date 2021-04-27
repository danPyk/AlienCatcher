package com.whayway.beerrandom.dialog


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whayway.beerrandom.data.ScoreBoardDao

class MyDialogViewModelFactory   (private val dataSource: ScoreBoardDao, val app: Application)
: ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyDialogViewModel::class.java)) {
            return MyDialogViewModel( dataSource, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
