package com.whayway.beerrandom.scoreboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreBoardDao
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.dialog.MyDialogViewModel
import com.whayway.beerrandom.game.GameViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//@Module
class ScoreboardFragment: Fragment() {
    // constructor(@Inject var dataSource: ScoreBoardDao
    //)

    //@Inject lateinit var dataSource: ScoreBoardDao
/*
    @Provides
    fun provideIf(): ScoreBoardDao{
        return ScoreBoardDao()
    }
*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: com.whayway.beerrandom.databinding.FragmentScoreboardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scoreboard, container, false
        )

        val application = requireNotNull(this.activity).application

       //dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao!!
       // val viewModelFactory = ScoreboardViewModelFactory(dataSource!!)

        val scoreboardyViewModel: ScoreboardViewModel by viewModels()

/*    val scoreboardyViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ScoreboardViewModel::class.java)*/

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
       binding.scoreboardyViewModel = scoreboardyViewModel

       // val myDialogViewModel: MyDialogViewModel by viewModels()

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        val adapter = ScoreBoardAdapter()
        //By supplying the fragment's viewLifecycleOwner as the lifecycle owner, you can make sure this observer is only active when the RecyclerView is on the screen.
  /*      dataSource.getAllScores().observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })
*/
        //RecyclerView needs to know about the adapter to use to get view holders.
        // associate the adapter with the RecyclerView.
         binding.scoreList.adapter = adapter

        binding.lifecycleOwner = this

        binding.backBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_scoreboardFragment_to_menuFragment)
        }

        return binding.root
    }



}


