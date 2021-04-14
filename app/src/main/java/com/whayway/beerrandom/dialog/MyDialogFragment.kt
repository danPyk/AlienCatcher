package com.whayway.beerrandom.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.databinding.FragmentMydialogBinding
import com.whayway.beerrandom.fragments.ResultFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MyDialogFragment: DialogFragment() {
    //todo change usage of binding like here
    private var _binding: FragmentMydialogBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMydialogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = MyDialogViewModelFactory(dataSource!!)

        val sleepQualityViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MyDialogViewModel::class.java)

        //sleepQualityViewModel = sleepQualityViewModel

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val score = args.score

        binding.scoreViewMydialog.text = getString(R.string.score_msg, "$score")


        view.findViewById<Button>(R.id.btn_follow)?.setOnClickListener {
            val edit = binding.editText.text.toString()
            if (edit == "") {
                showPopUp()
            } else {
                //todo maybe add Loading Spinner?
                sleepQualityViewModel.saveScore(args.score, edit, getPreferences())
                //  sleepQualityViewModel.onStop()
                findNavController().navigate(
                    MyDialogFragmentDirections.actionMyDialogToResultFragment(
                        args.score
                    )
                )
            }

        }
    }

    private fun showPopUp() {
        val coordinatorLayout =
            requireView().findViewById(R.id.mydialog_linerar_layout) as LinearLayout
        try {
            Snackbar.make(
                coordinatorLayout,
                R.string.mydialog_snackbar_message,
                2500
            ).show()
        } catch (e: NullPointerException) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getPreferences(): String {
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences?.getString(getString(R.string.difficulty_key), "Easy")!!
    }


}
