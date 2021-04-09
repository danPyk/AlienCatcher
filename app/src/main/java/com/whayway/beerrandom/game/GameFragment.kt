package com.whayway.beerrandom.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*


const val KEY_SCORE = "score_key"

class GameFragment  : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        object : CountDownTimer(30000, 1000) {
             override fun onTick(p0: Long) {
                 //todo this btn crashing app     java.lang.NullPointerException: btn_ok must not be null
                 //create separate binding for this?
                btn_ok.visibility = View.INVISIBLE

                timeText.text = "Time: " + (p0 / 1000)
            }
            override fun onFinish() {
                timeText.text = "Time out"
                viewModel.handler.removeCallbacks(viewModel.runnable)
                for (image in viewModel.viewList) {
                    image.visibility = View.INVISIBLE
                    btn_ok.visibility = View.VISIBLE
                }
            }
        }.start()

        viewModel.score.observe(viewLifecycleOwner, androidx.lifecycle.Observer { newScore ->
            binding.scoreText.text = "Score: "+newScore.toString()
        })

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewList = arrayListOf(
            imageView8,
            imageView7,
            imageView6,
            imageView5,
            imageView4,
            imageViewCow,
            imageView1,
            imageView3
        )
        imageView8.setOnClickListener { isThisCow(imageView8)
           }
        imageView7.setOnClickListener { isThisCow(imageView7)
          }
        imageView8.setOnClickListener{  isThisCow(imageView8)
          }
        imageView6.setOnClickListener {  isThisCow(imageView6)
           }
        imageView5.setOnClickListener {  isThisCow(imageView5)
            }
        imageView4.setOnClickListener { isThisCow(imageView4)
            }
        imageView3.setOnClickListener { isThisCow(imageView3)
         }
            //todo: do not bind view with res, check in every view if this is cow
        imageViewCow.setOnClickListener {
            isThisCow(imageViewCow)
        }

        imageView1.setOnClickListener {isThisCow(imageView1)}

        //why after adding binding here i have unresolv ref?
        btn_ok.setOnClickListener { view: View ->
            view.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToMyDialog(
                    viewModel.score.value!!
                )
            )
        }

        super.onViewCreated(view, savedInstanceState)
        //separate views frome data?
        setHasOptionsMenu(true)
    }
    private fun isThisCow(imageView: ImageView){
        //todo change to lateinit
        val marker = ResourcesCompat.getDrawable(resources, R.drawable.shipblue_cowed, null)
        var markerBitmam = marker?.toBitmap()


        val drawable = imageView.drawable.toBitmap()

        if (drawable.pixelsEqualTo(markerBitmam)){
            updateScoreCow()
            viewModel.setImage(imageView)
        }else{
            updateScore()
            viewModel.setImage(imageView)
        }
    }

    private fun updateScore() {
        viewModel.increaseScore()
            //I can get rig of that, becouse LIveData observes and updates score
       // binding.scoreText.text = viewModel.score.value.toString()
    }
    private fun updateScoreBoss() {
        viewModel.increaseScoreBoss()
    }
    private fun updateScoreCow() {
        viewModel.decreaseScore()
    }

 //block back btn
 override fun onAttach(context: Context) {
     super.onAttach(context)
     object : OnBackPressedCallback(
         true // default to enabled
     ) {
         override fun handleOnBackPressed() {
             Toast.makeText(context, "handleOnBackPressed", Toast.LENGTH_SHORT).show()
         }
     }
     requireActivity().onBackPressedDispatcher.addCallback(this, true) {

     }
 }
    //convert drawable to bitmap
    fun <T : Drawable> T.toBitmap(): Bitmap {
        if (this is BitmapDrawable) return bitmap

        val drawable: Drawable = this
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
    //compare pixels of bitmaps
    fun Bitmap.pixelsEqualTo(otherBitmap: Bitmap?, shouldRecycle: Boolean = false) = otherBitmap?.let { other ->
        if (width == other.width && height == other.height) {
            val res = Arrays.equals(toPixels(), other.toPixels())
            if (shouldRecycle) {
                doRecycle().also { otherBitmap.doRecycle() }
            }
            res
        } else false
    } ?: kotlin.run { false }

    fun Bitmap.doRecycle() {
        if (!isRecycled) recycle()
    }
    fun Bitmap.toPixels() = IntArray(width * height).apply { getPixels(this, 0, width, 0, 0, width, height) }



}