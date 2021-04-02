package com.whayway.beerrandom.game

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.whayway.beerrandom.GameFragmentDirections
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*


const val KEY_SCORE = "score_key"

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        object : CountDownTimer(3000, 1000) {
             override fun onTick(p0: Long) {
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
        viewModel._score.observe(viewLifecycleOwner, Observer{ newScore ->
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
            imageViewLechFree,
            imageViewPawel,
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
        imageViewLechFree.setOnClickListener {updateScoreFree()
            viewModel.setImage() }
        imageViewPawel.setOnClickListener {updateScorePawel()
            viewModel.setImage()}
        //why after adding binding here i have unresolv ref?
        btn_ok.setOnClickListener { view: View ->
            view.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToResultFragment(
                    viewModel.score.value ?: 0
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
    private fun updateScorePawel() {
        viewModel.increaseScorePawel()
    }
    private fun updateScoreFree() {
        viewModel.decreaseScore()
    }
 /*   private fun gameFinished() {
        //Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.()
    }*/

}