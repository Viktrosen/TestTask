package com.example.testtask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class StagesAdapter(private val stages: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class StageViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(position: Int){
            val counter = itemView.findViewById<TextView>(R.id.tv_counter)
            val stageName = itemView.findViewById<TextView>(R.id.tv_stage_name)
            counter.text = "${position+1}/${stages.size}"
            stageName.text = stages[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StageViewHolder(
            inflater.inflate(
                R.layout.stage_item,
                parent,
                false
            ) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as StageViewHolder
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return stages.size
    }
}