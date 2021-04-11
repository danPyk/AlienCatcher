package com.whayway.beerrandom.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.whayway.beerrandom.R
import com.whayway.beerrandom.databinding.FragmentResultBinding

//todo correct back navigation
class ResultFragment  : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result, container, false
        )

        binding.playAgainButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
        }
        val args = ResultFragmentArgs.fromBundle(requireArguments())
        if (args.score > 30) {
            binding.starView.setImageResource(R.drawable.color_star_6)
            binding.starView2.setImageResource(R.drawable.color_star_6)
            binding.starView3.setImageResource(R.drawable.color_star_6)
            binding.textView.text = getString(R.string.great_result)
        } else if ((args.score > 15) && (args.score < 30)) {
            binding.starView.setImageResource(R.drawable.color_star_6)
            binding.starView2.setImageResource(R.drawable.color_star_6)
            binding.starView3.setImageResource(R.drawable.white_board)
            binding.textView.text = getString(R.string.good_result)

        } else {
            binding.starView.setImageResource(R.drawable.color_star_6)
            binding.starView2.setImageResource(R.drawable.white_board)
            binding.starView3.setImageResource(R.drawable.white_board)
            binding.textView.text = getString(R.string.bad_result)

        }

        binding.menuBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_menuFragment)
        }
        //binding.resultViewModel = resultViewModel
        return binding.root
    }

/*    // Creating our Share Intent
    private fun getShareIntent(): Intent {
        //retriving data, like for toas massage
        val args = ResultFragmentArgs.fromBundle(requireArguments())
        //to deliver the message that the user wants to share.

        val shareIntent = Intent(Intent.ACTION_SEND)
        //setType sets MIME type
        //The actual data to be delivered is specified in the EXTRA_TEXT. (The share_success_text string is defined in the strings.xml resource file.
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.about))
        return shareIntent
    }
    // Starting an Activity with our new Intent
    //. This method gets the Intent from getShareIntent() and calls startActivity() to begin sharing.
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.winner_menu, menu)
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }*/

    //block back btn whila game
    override fun onAttach(context: Context) {
        super.onAttach(context)
        object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                Toast.makeText(context, "handleOnBackPressed", Toast.LENGTH_SHORT).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, true) {

        }
    }


}