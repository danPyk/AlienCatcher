package com.whayway.beerrandom.repository

import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository(private val database:  ScoreDatabase) {

    suspend fun repoCall( scoreBoard: ScoreBoard) {
        withContext(Dispatchers.IO) {
            // fetch the DevByte video playlist from the network using the Retrofit service instance, DevByteNetwork.

                    database.scoreDatabaseDao?.insert(scoreBoard)

        }
    }
}