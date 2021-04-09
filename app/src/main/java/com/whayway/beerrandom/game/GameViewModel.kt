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
    //list with images
    lateinit var viewList:  ArrayList<ImageView>

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
                for (image in viewList) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(8 - 1)
                viewList[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, 450)
            }
        }
        handler.post(runnable)
    }
    //random image bind to view
    fun setImage(view: ImageView){
        var drawableList = arrayListOf(
            R.drawable.shipbeige_manned,
            R.drawable.shipblue_manned,
            R.drawable.shipgreen_manned,
            R.drawable.shippink_manned,
            R.drawable.shipyellow_manned,
            R.drawable.shipblue_cowed,
            R.drawable.shipblue_cowed

        )
        val random = Random()
        val indexDrawables = random.nextInt(7 - 1)
       view.setImageResource(drawableList[indexDrawables])

    }
     fun increaseScore() {
         _score.value = (_score.value)?.plus(1)
    }
     fun increaseScoreBoss() {
         _score.value = (_score.value)?.plus(2)
    }
     fun decreaseScore() {
         _score.value = (_score.value)?.minus(3)
    }
    override fun onCleared() {
        super.onCleared()
    }
}
