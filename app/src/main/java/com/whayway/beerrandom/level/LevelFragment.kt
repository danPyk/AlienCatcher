package com.whayway.beerrandom.level

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentLevelBinding

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding


    private lateinit var viewModel: LevelViewModel
   // val viewModel: LevelViewModel by viewModels()

    var timex: Long = 11L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LevelViewModel::class.java)
        // Assign the component to a property in the binding class.
        binding.level = viewModel

        viewModel.time.observe(viewLifecycleOwner, { newTime ->
            timex = newTime

            viewModel.time
        })
        binding.btnEasy.setOnClickListener{
            onRadioButtonClicked(binding.btnEasy)
        }
        binding.btnMedium.setOnClickListener{
            onRadioButtonClicked(binding.btnMedium)
        }
        binding.btnHard.setOnClickListener{
            onRadioButtonClicked(binding.btnHard)
            }

         binding.btnOkLevel.setOnClickListener {
             if(viewModel.time.value == null){
                 showPopUp()
             }else{
                 findNavController().navigate (
                     LevelFragmentDirections.actionLevelFragmentToGameFragment(viewModel.time.value!!)

                 )
             }

        }
        return binding.root
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.id) {
                R.id.btn_easy->
                    if (checked) {
                        viewModel._time.value = 30000L
                        saveDifficultyLevel(R.string.difficulty_key, "Easy")
                    }
                R.id.btn_medium->
                    if (checked) {
                        viewModel._time.value = 23000L
                        saveDifficultyLevel(R.string.difficulty_key, "Medium")

                    }
                R.id.btn_hard ->
                    if (checked) {
                        viewModel._time.value = 15000L
                        saveDifficultyLevel(R.string.difficulty_key, "Hard")

                    }
            }
        }
    }

    private fun saveDifficultyLevel(key: Int, value: String){
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreferences.edit()){
            putString(getString(key), value)
            apply()
        }

    }
    private fun showPopUp() {
        val coordinatorLayout =
            requireView().findViewById(R.id.level_constraint_layout) as ConstraintLayout
        try {
            Snackbar.make(
                coordinatorLayout,
                R.string.level_snackbar_message,
                2500
            ).show()
        } catch (e: NullPointerException) {

        }
    }


}