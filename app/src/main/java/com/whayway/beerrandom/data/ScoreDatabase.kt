package com.whayway.beerrandom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScoreBoard::class], version = 17, exportSchema = false )
abstract class ScoreDatabase : RoomDatabase(){
    //database needs to know about DAO
    abstract val sleepDatabaseDao: ScoreBoardDao?

    companion object{
        //The INSTANCE variable will keep a reference to the database, once one has been created.
        // This helps you avoid repeatedly opening connections to the database, which is expensive.
        //The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        @Volatile //LOTNY/ZMIENNY
        private var INSTANCE: ScoreDatabase? = null
        fun getInstance(context: Context): ScoreDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    //use the database builder to get a database
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScoreDatabase::class.java,
                        "sleep_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }

}
