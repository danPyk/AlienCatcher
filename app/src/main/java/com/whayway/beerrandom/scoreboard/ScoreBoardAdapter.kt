package com.whayway.beerrandom.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.whayway.beerrandom.R
import com.whayway.beerrandom.data.ScoreBoard
/*//takes an old list and a new list and figures out what's different.
class SleepNightDiffCallback : DiffUtil.ItemCallback<ScoreBoard>() {
    override fun areItemsTheSame(oldItem: ScoreBoard, newItem: ScoreBoard): Boolean {
        return oldItem.scoreId == newItem.scoreId
    }

    override fun areContentsTheSame(oldItem: ScoreBoard, newItem: ScoreBoard): Boolean {
        return oldItem == newItem
    }
}*/
class   ScoreBoardAdapter(): RecyclerView.Adapter<ScoreBoardAdapter.ViewHolder>() {


    var data =  listOf<ScoreBoard>()
        set(value) {
            field = value
            //todo exted ListAdapter
            //not the best way to update data
            notifyDataSetChanged()
        }


    //total number of items
    override fun getItemCount(): Int {
        return data.size
    }

    //viewType parameter is used when there are multiple views in the same RecyclerView
    // parent parameter, which is the view group that holds the view holder, is always the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    //called by RecyclerView to display the data for one list item on specific position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        //todo change to databinding
        private val scoreNameTextView: TextView = itemView.findViewById(R.id.name_view)
        private val scoreResultTextView: TextView = itemView.findViewById(R.id.score_view)
        private val scoreIdTextView: TextView = itemView.findViewById(R.id.id_view)
        private val difficultyTextView: TextView = itemView.findViewById(R.id.difficulty_view)
        //fun extracted from onBindVIewHolder to separate logic

        fun bind(
            item: ScoreBoard
        ) {
            //todo use to make time attack
/*                val res = holder.itemView.context.resources
                holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)*/
            scoreNameTextView.text = item.score_name
            scoreResultTextView.text = item.score_points.toString()
            scoreIdTextView.text = item.scoreId.toString()
            difficultyTextView.text = item.difficulty_level
        }
    /*    private fun ViewHolder.bind(item: ScoreBoard){

        }*/
        companion object{
        //fun extracted from onCreateViewHolder
            fun from(parent: ViewGroup): ViewHolder {
                //In an adapter for a recycler view always pass in the context of the parent view group, which is the RecyclerView
                val layoutInflater = LayoutInflater.from(parent.context)

                // create views from XML layouts
                val view = layoutInflater
                    .inflate(R.layout.list_item_scoreboard, parent, false) as ConstraintLayout

                return ViewHolder(view)
            }
        }
    }



}
