package com.whayway.beerrandom.game

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*


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


        object : CountDownTimer(15000, 1000) {
             override fun onTick(p0: Long) {
                 //todo this btn crashing app     java.lang.NullPointerException: btn_ok must not be null
                 //create separate binding for this?
                btn_ok.visibility = View.INVISIBLE

                timeText.text = "Time: " + (p0 / 1000)
            }
            override fun onFinish() {
                timeText.text = "Time out"
                viewModel.handler.removeCallbacks(viewModel.runnable)
                for (image in viewModel.imageArray) {
                    image.visibility = View.INVISIBLE
                    btn_ok.visibility = View.VISIBLE
                }
            }
        }.start()

        viewModel.score.observe(viewLifecycleOwner, androidx.lifecycle.Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.imageArray = arrayListOf(
            imageView8,
            imageView7,
            imageView6,
            imageView5,
            imageView4,
            imageViewCow,
            imageView1,
            imageView3
        )
        scoreText.setOnClickListener {}
        imageView8.setOnClickListener { updateScore() }
        imageView7.setOnClickListener {updateScore()  }
        imageView8.setOnClickListener{ updateScore() }
        imageView6.setOnClickListener { updateScore()   }
        imageView5.setOnClickListener { updateScore()  }
        imageView4.setOnClickListener {updateScore()
            viewModel.setImage() }
        imageView3.setOnClickListener {updateScore()
            viewModel.setImage()}
        imageViewCow.setOnClickListener {updateScoreCow()
            viewModel.setImage() }
        imageView1.setOnClickListener {updateScoreBoss()
            viewModel.setImage()}
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

    private fun updateScore() {
        viewModel.increaseScore()
            //I can get rig of that, becouse LIveData ovserves and updates score
       // binding.scoreText.text = viewModel.score.value.toString()
    }
    private fun updateScoreBoss() {
        viewModel.increaseScoreBoss()
    }
    private fun updateScoreCow() {
        viewModel.decreaseScore()
    }

 //block back btn whila game
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


}