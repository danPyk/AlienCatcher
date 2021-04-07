package com.whayway.beerrandom.scoreboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreBoard
import com.whayway.beerrandom.data.ScoreBoardAdapter
import com.whayway.beerrandom.data.ScoreBoardDao
import com.whayway.beerrandom.data.ScoreDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
//todo zubr poszukiwanie
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ScoreboardFragment : Fragment() {
private var listNew= listOf<ScoreBoard>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: com.whayway.beerrandom.databinding.FragmentScoreboardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scoreboard, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = ScoreboardViewModelFactory(dataSource!!, application)

        val scoreboardyViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ScoreboardViewModel::class.java)
        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.scoreboardyViewModel = scoreboardyViewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        val list = scoreboardyViewModel.generateList()
        val adapter = ScoreBoardAdapter(listNew)

        list.observe(
            viewLifecycleOwner,
            Observer { newLIst ->
                listNew = newLIst }
        )
        list.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        //RecyclerView needs to know about the adapter to use to get view holders.
        // associate the adapter with the RecyclerView.
         binding.scoreList.adapter = adapter

        binding.setLifecycleOwner(this)


        //By supplying the fragment's viewLifecycleOwner as the lifecycle owner, you can make sure this observer is only active when the RecyclerView is on the screen.
   /*     scoreboardyViewModel.nights.observe(viewLifecycleOwner, Observer {
            // whenever you get a non-null value (for nights), assign the value to the adapter's data
            it?.let {
                 adapter.data
            }

        })*/

        return binding.root
    }



}


