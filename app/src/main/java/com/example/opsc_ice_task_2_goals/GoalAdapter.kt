package com.example.opsc_ice_task_2_goals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoalAdapter(private val goalList: MutableList<MainActivity.Goal>) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
        return GoalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val currentGoal = goalList[position]
        holder.bind(currentGoal)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, GoalDetailActivity::class.java)
            intent.putExtra("goal_title", currentGoal.title)
            intent.putExtra("goal_description", currentGoal.description)
            intent.putExtra("goal_position", position)
            (context as MainActivity).startActivityForResult(intent, MainActivity.REQUEST_CODE_GOAL_DETAIL)
        }
    }

    override fun getItemCount() = goalList.size

    class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtGoalTitle)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtGoalDescription)

        fun bind(goal: MainActivity.Goal) {
            txtTitle.text = goal.title
            txtDescription.text = goal.description
        }
    }
}
