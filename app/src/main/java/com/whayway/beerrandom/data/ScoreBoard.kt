package com.whayway.beerrandom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//todo change package name
@Entity(tableName = "score_board_table")
data class ScoreBoard  (
    @PrimaryKey(autoGenerate = true)
var scoreId: Int = 0,
    @ColumnInfo(name ="score_scoreboard")
var score_points: Int = 0,
    @ColumnInfo(name ="score_name")
var score_name: String = "",
    @ColumnInfo(name ="difficulty_level")
    var difficulty_level: String = "",

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    )
