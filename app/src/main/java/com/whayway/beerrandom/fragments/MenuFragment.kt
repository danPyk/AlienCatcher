package com.whayway.beerrandom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import kotlin.system.exitProcess

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: com.whayway.beerrandom.databinding.FragmentMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false
        )

        binding.btnStartGame.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_menuFragment2_to_instructionFragment)
        }
        binding.btnScoreboard.setOnClickListener { view: View ->

            view.findNavController().navigate(R.id.action_menuFragment2_to_entranceFragment2)
        }
        binding.btnExit.setOnClickListener {
            exitProcess(0)

        }
            return binding.root}
}



