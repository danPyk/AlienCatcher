package com.whayway.beerrandom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_instruction.*

class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: com.whayway.beerrandom.databinding.FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instruction, container, false)

        binding.okBtn4.setOnClickListener  { view : View ->
            view.findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToGameFragment())
        }

        return binding.root}
}



