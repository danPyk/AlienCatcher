package com.whayway.beerrandom.game

import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whayway.beerrandom.R
import java.util.*

class GameViewModel: ViewModel() {
    lateinit var imageArray:  ArrayList<ImageView>
    //score in ensapsuleted
    var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
         get() = _score

    var runnable: Runnable = Runnable { }
    var handler: Handler = Handler()

    init{
        hideImages()
        _score.value = 0
    }
    fun hideImages() {
        runnable = object: Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(8 - 1)
                imageArray[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }
    fun setImage(){
        var drawableArray  = ArrayList<Int>()
        drawableArray = arrayListOf(
            R.drawable.lech_puszka,
            R.drawable.leszek,
            R.drawable.lech_free
        )
        val random = Random()
        val index = random.nextInt(3 - 1)
        val index2 = random.nextInt(8 - 1)
        imageArray[index2].setImageResource(drawableArray[index])

    }
     fun increaseScore() {
         _score.value = (_score.value)?.plus(1)
    }
     fun increaseScorePawel() {
         _score.value = (_score.value)?.plus(5)
    }
     fun decreaseScore() {
         _score.value = (_score.value)?.minus(10)
    }
    override fun onCleared() {
        super.onCleared()
    }
}
