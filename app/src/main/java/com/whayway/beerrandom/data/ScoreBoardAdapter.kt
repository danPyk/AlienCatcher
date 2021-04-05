package com.whayway.beerrandom.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whayway.beerrandom.R
import com.whayway.beerrandom.TextItemViewHolder
import java.util.Collections.list

class ScoreBoardAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<ScoreBoard>()
        // adapter needs to let the RecyclerView know when the data has changed, because the RecyclerView knows nothing about the data.
        //so we need to update
        //todo change notifyDataSetChanged(), becouse of hevy load
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    //total number of items
    override fun getItemCount() = data.size

    //called by RecyclerView to display the data for one list item on specific position
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.scoreId.toString()
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
