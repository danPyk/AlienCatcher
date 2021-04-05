package com.whayway.beerrandom

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardDao
import com.whayway.beerrandom.data.ScoreDatabase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var sleepDao: ScoreBoardDao
    private lateinit var db: ScoreDatabase
    //During setup, the function annotated with @Before is executed, and it creates an in-memory SleepDatabase with the SleepDatabaseDao.
// "In-memory" means that this database is not saved on the file system and will be deleted after the tests run.
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ScoreDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        sleepDao = db.sleepDatabaseDao!!
    }
    //CLOSE DB
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    //we create, insert, and retrieve a SleepNight, and assert that they are the same.
    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val night = ScoreBoard()
        sleepDao.insert(night)
        sleepDao.getAllScores()
        val tonight = sleepDao.getTonight()
       // assertEquals(tonight?.sleepQuality, -1)
    }
}