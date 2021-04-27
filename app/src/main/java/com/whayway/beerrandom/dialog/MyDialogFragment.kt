package com.whayway.beerrandom.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.databinding.FragmentMydialogBinding
import com.whayway.beerrandom.fragments.ResultFragmentArgs

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


    @SuppressLint("StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = MyDialogViewModelFactory(dataSource!!, application)

        val myDialogViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MyDialogViewModel::class.java)

        //sleepQualityViewModel = sleepQualityViewModel

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val score: Int = args.score

        binding.scoreViewMydialog.text = getString(R.string.score_msg, "$score")
/*      val bool =   binding.editText.requestFocus()
        val focus = binding.editText.isFocusable*/
        binding.editText.showKeyboard()


        binding.btnOkMydialog.setOnClickListener {
            val edit = binding.editText.text.toString()
            if (edit == "") {
                showPopUp()
            } else {
                //todo maybe add Loading Spinner?
                    binding.btnOkMydialog.hideKeyboard()
                myDialogViewModel.saveScore(args.score, edit, getPreferences())
                //  sleepQualityViewModel.onStop()
                findNavController().navigate(
                    MyDialogFragmentDirections.actionMyDialogToResultFragment(
                        args.score
                    )
                )
            }

        }
        //after press enter moze to another fragment
        binding.editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                findNavController().navigate(
                    MyDialogFragmentDirections.actionMyDialogToResultFragment(
                        args.score
                    )
                )
                true
            } else {
                false
            }
        }
        //todo
       // myDialogViewModel.callMSG()
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


    fun View.showKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput( InputMethodManager.SHOW_FORCED, 0)
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }




}
