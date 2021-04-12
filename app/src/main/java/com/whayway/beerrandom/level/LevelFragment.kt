package com.whayway.beerrandom.level

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentLevelBinding
import com.whayway.beerrandom.databinding.FragmentMydialogBinding

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding


    private lateinit var viewModel: LevelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelBinding.inflate(inflater, container, false)


        binding.btnOkLevel.setOnClickListener {
            findNavController().navigate(R.id.action_levelFragment_to_gameFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LevelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}