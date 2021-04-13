package com.whayway.beerrandom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.whayway.beerrandom.R


class InstructionFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: com.whayway.beerrandom.databinding.FragmentInstructionBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_instruction, container, false
            )

        binding.okBtn4.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_instructionFragment_to_levelFragment)
        }


        return binding.root
    }
}



