package com.whayway.beerrandom.game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.whayway.beerrandom.R
import com.whayway.beerrandom.level.LevelFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GameViewModel
@Inject constructor(
    private val state: SavedStateHandle
    ): ViewModel() {

    //retrive safe args by hilt
    val stejt = state.get<Long>("time")

    lateinit var viewList:  ArrayList<ImageView>

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
         get() = _score

    var  _gameTime =  MutableLiveData<Long>()
    val gameTime: LiveData<Long>
        get() = _gameTime

   //var timeSetByUser = levelFragment.timex

    //val args = ResultFragmentArgs.fromBundle(requireArguments())

    var runnable: Runnable = Runnable { }
    var handler: Handler = Handler()


    init{
        hideImages()
        _score.value = 0
       // _gameTime.value = 2000
       // Log.i(TAG, "GameViewModel $timeSetByUser: ")

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
                handler.postDelayed(runnable, stejt!!)
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

     fun fader(view: ImageView) {

        // Fade the view out to completely transparent and then back to completely opaque

        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(view)
        animator.start()
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        // This extension method listens for start/end events on an animation and disables
        // the given view for the entirety of that animation.

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

}
