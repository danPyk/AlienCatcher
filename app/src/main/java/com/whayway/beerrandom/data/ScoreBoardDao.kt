package com.whayway.beerrandom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScoreBoardDao {
    @Insert
    fun insert(scoreBoard: ScoreBoard)

    @Update
    fun update(scoreBoard: ScoreBoard)

    @Query("SELECT * from score_board_table WHERE scoreId = :key")
    fun get(key: Long): ScoreBoard?

    @Query("DELETE FROM score_board_table")
    fun clear()
    //query, return nightId 1
/*    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM  score_board_table  WHERE scoreId =  ")
    fun getTonight(): ScoreBoard*/
    //desc = descending order
    @Query("SELECT * FROM score_board_table ORDER BY scoreId DESC")
    fun getAllNights(): LiveData<List<ScoreBoard>>
}
