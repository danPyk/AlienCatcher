package com.whayway.beerrandom.scoreboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreDatabase

// TODO: Rename parameter arguments, choose names that match
//todo zubr poszukiwanie
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ScoreboardFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: com.whayway.beerrandom.databinding.FragmentScoreboardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scoreboard, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = ScoreboardViewModelFactory(dataSource!!)

        val scoreboardyViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ScoreboardViewModel::class.java)
        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.scoreboardyViewModel = scoreboardyViewModel
        binding.lifecycleOwner = this
        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        val adapter = ScoreBoardAdapter()
        //By supplying the fragment's viewLifecycleOwner as the lifecycle owner, you can make sure this observer is only active when the RecyclerView is on the screen.

        scoreboardyViewModel.allResults.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })
        //RecyclerView needs to know about the adapter to use to get view holders.
        // associate the adapter with the RecyclerView.
        binding.scoreList.adapter = adapter


        binding.backBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_scoreboardFragment_to_menuFragment)
        }

        return binding.root
    }



}


