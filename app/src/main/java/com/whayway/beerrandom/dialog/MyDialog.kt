package com.whayway.beerrandom.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreDatabase
import com.whayway.beerrandom.fragments.ResultFragmentArgs
import kotlinx.android.synthetic.main.fragment_result.*

class MyDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_popup, container, false)

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
        val editText = requireView().findViewById<EditText>(R.id.editTextView)

        view?.findViewById<Button>(R.id.btnFollow)?.setOnClickListener {
            val edit = editText.text.toString()
            if(edit==""){
                 showPopUp()
                }else{
                    //todo maybe add Loading Spinner
                    //edit
/*            findNavController().navigate (
                MyDialogDirections.actionMyDialogToResultFragment(
                    args.score
                )
            )*/
                sleepQualityViewModel.onStartTracking(args.score, edit)

            }

        }
        view?.findViewById<TextView>(R.id.txtClose)?.setOnClickListener {
          //  textView.text = dataSource.getTonight().toString()


        }

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
       //hold current score



}
