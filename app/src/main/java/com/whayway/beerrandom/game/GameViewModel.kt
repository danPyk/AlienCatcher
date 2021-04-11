package com.whayway.beerrandom.game

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whayway.beerrandom.R
import java.util.*

class GameViewModel(application: Application): AndroidViewModel(application) {
    //list with images
    lateinit var viewList:  ArrayList<ImageView>

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
         get() = _score

     var  _gameTime =  MutableLiveData<Long>()
    val gameTime: LiveData<Long>
        get() = _gameTime


    var runnable: Runnable = Runnable { }
    var handler: Handler = Handler()

    var cowMarker = ResourcesCompat.getDrawable(getApplication<Application>().resources, R.drawable.shipblue_cowed, null)
    var bossMarker = ResourcesCompat.getDrawable( getApplication<Application>().resources, R.drawable.shippink_manned, null)

    val bossBitMap =  toBitmap(bossMarker!!)
    val cowBitmatp =  toBitmap(cowMarker!!)

    init{
        hideImages()
        _score.value = 0
        _gameTime.value = 2000


    }
    private fun hideImages() {
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
        val drawableList = arrayListOf(
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
    //todo update viewmodel
    override fun onCleared() {
        super.onCleared()
    }
    //convert drawable to bitmap
    fun toBitmap(drawable: Drawable): Bitmap {

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }


}
