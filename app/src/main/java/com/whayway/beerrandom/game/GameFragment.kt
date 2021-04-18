package com.whayway.beerrandom.game
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentGameBinding
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_game.*
import com.whayway.beerrandom.utils.toPixels

@FragmentScoped
class GameFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentGameBinding

    val viewModel: GameViewModel by viewModels()

    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                //todo this btn crashing app     java.lang.NullPointerException: btn_ok must not be null
                //create separate binding for this?
                btn_ok.visibility = View.INVISIBLE
                val timeDivided = p0 / 1000
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

        viewModel.score.observe(viewLifecycleOwner, { newScore ->
            binding.scoreText.text = getString(R.string.score_msg, "$newScore")
        }
        )
        //when game is finished, mocw to MyDialog
        viewModel.gameTime.observe(viewLifecycleOwner,  { newScore ->
            if (newScore == 0L) {
                findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToMyDialog(
                        viewModel.score.value!!
                    )
                )
            }
        }
        )
        binding.btnOk.setOnClickListener {
            findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToMyDialog(
                    viewModel.score.value!!
                )
            )
        }

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
        val cowMarker = ResourcesCompat.getDrawable(resources, R.drawable.shipblue_cowed, null)
        val bossMarker = ResourcesCompat.getDrawable(resources, R.drawable.shippink_manned, null)

        val bossBitMap =  viewModel.toBitmap(bossMarker!!)
        val cowBitmap =  viewModel.toBitmap(cowMarker!!)

        imageView8.setOnClickListener {
            imageViewSrc(imageView8, bossBitMap, cowBitmap)
            viewModel.fader(imageView6)
        }
        imageView7.setOnClickListener {
            imageViewSrc(imageView7, bossBitMap, cowBitmap)
            viewModel.fader(imageView7)
        }

        imageView6.setOnClickListener {
            imageViewSrc(imageView6, bossBitMap, cowBitmap)
            viewModel.fader(imageView6)
        }
        imageView5.setOnClickListener {
            imageViewSrc(imageView5, bossBitMap, cowBitmap)
            viewModel.fader(imageView5)

        }
        imageView4.setOnClickListener {
            imageViewSrc(imageView4, bossBitMap, cowBitmap)
            viewModel.fader(imageView4)
        }
        imageView3.setOnClickListener {
            imageViewSrc(imageView3, bossBitMap, cowBitmap)
            viewModel.fader(imageView3)
        }
        imageViewCow.setOnClickListener {
            imageViewSrc(imageViewCow, bossBitMap, cowBitmap)
            viewModel.fader(imageViewCow)
        }

        imageView1.setOnClickListener {
            imageViewSrc(imageView1, bossBitMap, cowBitmap)
            viewModel.fader(imageView1)
        }

        super.onViewCreated(view, savedInstanceState)
        //separate views frome data?
        setHasOptionsMenu(true)
    }

    private fun imageViewSrc(imageView: ImageView, boss: Bitmap?, cow: Bitmap?) {
        val drawable = viewModel.toBitmap(imageView.drawable)

        when {
            drawable.pixelsEqualTo(cow) -> {
                updateScoreCow()
                viewModel.setImage(imageView)
            }
            drawable.pixelsEqualTo(boss) -> {
                updateScoreBoss()
                viewModel.setImage(imageView)
            }
            else -> {
                updateScore()
            }
        }
    }
    //I can get rig of that 3 fun, becouse LIveData observes and updates score
    // binding.scoreText.text = viewModel.score.value.toString()
    private fun updateScore() {
        viewModel.increaseScore()
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
    private fun Bitmap.pixelsEqualTo(otherBitmap: Bitmap?, shouldRecycle: Boolean = false) =
        otherBitmap?.let { other ->
            if (width == other.width && height == other.height) {
                val res = toPixels().contentEquals(other.toPixels())
                if (shouldRecycle) {
                    doRecycle().also { otherBitmap.doRecycle() }
                }
                res
            } else false
        } ?: kotlin.run { false }

    private fun Bitmap.doRecycle() {
        if (!isRecycled) recycle()
    }



}