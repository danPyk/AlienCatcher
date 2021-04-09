package com.whayway.beerrandom.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.databinding.FragmentMydialogBinding
import com.whayway.beerrandom.fragments.ResultFragmentArgs

class MyDialog: DialogFragment() {
    //todo change usage of binding like here
    private var _binding: FragmentMydialogBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMydialogBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory( dataSource!!)

        val sleepQualityViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MyDialogViewModel::class.java)

        //sleepQualityViewModel = sleepQualityViewModel

        val args = ResultFragmentArgs.fromBundle(requireArguments())

        binding.scoreViewMydialog.text = "Score " +args.score.toString()


        view?.findViewById<Button>(R.id.btn_follow)?.setOnClickListener {
            val edit = binding.editText.text.toString()
            if(edit==""){
                showPopUp()
            }else{
                //todo maybe add Loading Spinner?
                sleepQualityViewModel.saveScore(args.score, edit)
                sleepQualityViewModel.onStop()
                findNavController().navigate (
                    MyDialogDirections.actionMyDialogToResultFragment(
                        args.score
                    )
                )
            }

        }
      /*  view?.findViewById<TextView>(R.id.txt_close_view)?.setOnClickListener {

        }*/

    }

    private fun showPopUp() {
        val coordinatorLayout =
            requireView().findViewById(R.id.linerar_layout) as LinearLayout
        try {
            Snackbar.make(
                coordinatorLayout,
                R.string.snackbar_message,
                2500
            ).show()
        } catch (e: NullPointerException) {

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
