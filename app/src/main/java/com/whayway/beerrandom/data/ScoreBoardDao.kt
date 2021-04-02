package com.whayway.beerrandom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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
    @Query("SELECT * FROM score_board_table ORDER BY scoreId DESC LIMIT 1")
    fun getTonight(): ScoreBoard?
    //desc = descending order
    @Query("SELECT * FROM score_board_table ORDER BY scoreId DESC")
    fun getAllNights(): LiveData<List<ScoreBoard>>
}
