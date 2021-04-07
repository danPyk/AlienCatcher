package com.whayway.beerrandom.data

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.whayway.beerrandom.R
import com.whayway.beerrandom.TextItemViewHolder
import java.util.ArrayList

class   ScoreBoardAdapter(private val   list: List<ScoreBoard>): RecyclerView.Adapter<TextItemViewHolder>() {

    var data =  listOf<ScoreBoard>()
        set(value) {
            Log.i(TAG, "datadata: $data ")
            field = value
            notifyDataSetChanged()
        }


    //total number of items
    override fun getItemCount(): Int {
        val size = list.size
        return size
    }

    //called by RecyclerView to display the data for one list item on specific position
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
       val item = list.get(position)
        holder.textView.text = item?.scoreId.toString()
    }

    //viewType parameter is used when there are multiple views in the same RecyclerView
    // parent parameter, which is the view group that holds the view holder, is always the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //In an adapter for a recycler view always pass in the context of the parent view group, which is the RecyclerView
        val layoutInflater = LayoutInflater.from(parent.context)

        // create views from XML layouts
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
 
}
