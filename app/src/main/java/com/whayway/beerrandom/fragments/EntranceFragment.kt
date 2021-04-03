package com.whayway.beerrandom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_entrance.*

// TODO: Rename parameter arguments, choose names that match
//todo zubr poszukiwanie
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class EntranceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: com.whayway.beerrandom.databinding.FragmentEntranceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_entrance, container, false)
        binding.okBtn3.setOnClickListener { view : View ->
            view.findNavController().navigate(EntranceFragmentDirections.actionEntranceFragmentToInstructionFragment())
        }

        return binding.root}
    }


