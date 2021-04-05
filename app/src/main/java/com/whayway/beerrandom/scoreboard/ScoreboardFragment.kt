package com.whayway.beerrandom.scoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreBoardAdapter
import com.whayway.beerrandom.data.ScoreBoardDao
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.dialog.MyDialogViewModel
import com.whayway.beerrandom.dialog.SleepQualityViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
//todo zubr poszukiwanie
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ScoreboardFragment : Fragment() {

init{

}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: com.whayway.beerrandom.databinding.FragmentScoreboardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scoreboard, container, false)



        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = ScoreboardViewModelFactory( dataSource!!)

        val scoreboardyViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ScoreboardViewModel::class.java)
        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.scoreboardyViewModel = scoreboardyViewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.lifecycleOwner = this

        val adapter = ScoreBoardAdapter()

        //By supplying the fragment's viewLifecycleOwner as the lifecycle owner, you can make sure this observer is only active when the RecyclerView is on the screen.
/*        scoreboardyViewModel.nights.observe(viewLifecycleOwner, Observer {
            // whenever you get a non-null value (for nights), assign the value to the adapter's data
            it?.let{
                adapter.data
            }

        })*/

        //RecyclerView needs to know about the adapter to use to get view holders.
        // associate the adapter with the RecyclerView.
        binding.scoreList.adapter = adapter

        return binding.root
    }

    private fun getScore(db: ScoreBoardDao){
        viewLifecycleOwner.lifecycleScope.launch (Dispatchers.IO){

          getScoreFromDB(db)

        }
    }
    private suspend fun getScoreFromDB(dataBase: ScoreBoardDao){

        dataBase?.getAllScores()


    }

}


