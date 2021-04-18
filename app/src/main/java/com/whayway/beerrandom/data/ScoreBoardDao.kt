package com.whayway.beerrandom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
 interface ScoreBoardDao {
    @Insert
    suspend fun insert(scoreBoard: ScoreBoard)

    @Update
    suspend fun update(scoreBoard: ScoreBoard)

    @Query("SELECT * from score_board_table WHERE scoreId = :key")
    fun get(key: Long): ScoreBoard?

    @Query("DELETE FROM score_board_table")
    fun clear()
    //query, return nightId 1
/*    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM  score_board_table  WHERE scoreId =  ")
    fun getTonight(): ScoreBoard*/
    //desc = descending order
    @Query("SELECT * FROM score_board_table ")//ORDER BY score_scoreboard DESC
     fun getAllScores(): LiveData<List<ScoreBoard>>


    @Query("SELECT * FROM score_board_table ORDER BY scoreId DESC LIMIT 1")
    suspend fun getTonight(): ScoreBoard?
}
