package com.whayway.beerrandom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_board_table")
data class ScoreBoard  (
@PrimaryKey(autoGenerate = true)
var scoreId: Int = 0,
@ColumnInfo(name ="score_scoreboard")
var score_board: Int = 0,
)