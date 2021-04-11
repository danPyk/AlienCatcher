package com.whayway.beerrandom.game

import android.annotation.SuppressLint
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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentGameBinding
import com.whayway.beerrandom.dialog.MyDialogFragmentDirections
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_result.*


class GameFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        object : CountDownTimer(viewModel.gameTime.value!!, 1000) {
             override fun onTick(p0: Long) {
                 //todo this btn crashing app     java.lang.NullPointerException: btn_ok must not be null
                 //create separate binding for this?
                btn_ok.visibility = View.INVISIBLE
                 val timeDivided = p0/1000
                 timeText.text = getString(R.string.time, "$timeDivided")
            }
            override fun onFinish() {

                timeText.text = getString(R.string.time_out)
                viewModel.handler.removeCallbacks(viewModel.runnable)
                for (image in viewModel.viewList) {
                    image.visibility = View.INVISIBLE
                    btn_ok.visibility = View.VISIBLE
                }
                viewModel._gameTime.value = 0L

            }
        }.start()

        viewModel.score.observe(viewLifecycleOwner,  { newScore ->
            binding.scoreText.text = getString(R.string.score_msg, "$newScore")
            }
      )
        viewModel.gameTime.observe(viewLifecycleOwner, Observer   { newScore ->
            if(newScore == 0L){
                findNavController().navigate (
                    GameFragmentDirections.actionGameFragmentToMyDialog(
                       viewModel.score.value!!
                    )
                )
            }
        }
        )

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
        )            //todo: add boss

        imageView8.setOnClickListener { imageViewSrc(imageView8, viewModel.bossBitMap, viewModel.cowBitmatp)
           }
        imageView7.setOnClickListener { imageViewSrc(imageView7, viewModel.bossBitMap, viewModel.cowBitmatp)
          }
        imageView8.setOnClickListener{  imageViewSrc(imageView8, viewModel.bossBitMap, viewModel.cowBitmatp)
          }
        imageView6.setOnClickListener {  imageViewSrc(imageView6, viewModel.bossBitMap, viewModel.cowBitmatp)
           }
        imageView5.setOnClickListener {  imageViewSrc(imageView5, viewModel.bossBitMap, viewModel.cowBitmatp)
            }
        imageView4.setOnClickListener { imageViewSrc(imageView4, viewModel.bossBitMap, viewModel.cowBitmatp)
            }
        imageView3.setOnClickListener { imageViewSrc(imageView3, viewModel.bossBitMap, viewModel.cowBitmatp)
         }
        imageViewCow.setOnClickListener {
            imageViewSrc(imageViewCow, viewModel.bossBitMap, viewModel.cowBitmatp)
        }

        imageView1.setOnClickListener {imageViewSrc(imageView1, viewModel.bossBitMap, viewModel.cowBitmatp)}

        super.onViewCreated(view, savedInstanceState)
        //separate views frome data?
        setHasOptionsMenu(true)
    }
    private fun imageViewSrc(imageView: ImageView, boss: Bitmap?, cow: Bitmap?){
        val drawable = viewModel.toBitmap(imageView.drawable)

        if (drawable.pixelsEqualTo(cow)){
            updateScoreCow()
            viewModel.setImage(imageView)
        }else if (drawable.pixelsEqualTo(boss)){
            updateScoreBoss()
            viewModel.setImage(imageView)
        }else{
            updateScore()
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

    //compare pixels of bitmaps
    fun Bitmap.pixelsEqualTo(otherBitmap: Bitmap?, shouldRecycle: Boolean = false) = otherBitmap?.let { other ->
        if (width == other.width && height == other.height) {
            val res = toPixels().contentEquals(other.toPixels())
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