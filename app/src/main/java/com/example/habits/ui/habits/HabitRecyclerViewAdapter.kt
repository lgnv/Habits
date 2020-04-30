package com.example.habits.ui.habits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import com.example.habits.habit.Habit


class HabitRecyclerViewAdapter(val habits: ArrayList<Habit>) : RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {
    var onBindCallback: OnBindCallback? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habitName: TextView = itemView.findViewById(R.id.habit_name)
        private val habitDescription: TextView = itemView.findViewById(R.id.habit_description)
        private val habitType: TextView = itemView.findViewById(R.id.habit_type)
        private val habitPriority: TextView = itemView.findViewById(R.id.habit_priority)
        private val habitIntensity: TextView = itemView.findViewById(R.id.habit_intencity)
        private val habitPeriodicity: TextView = itemView.findViewById(R.id.habit_period)

        fun bind(habit: Habit) {
            habitName.text = "Habit name: ${habit.title}"
            habitDescription.text = "Description: ${habit.description}"
            habitIntensity.text = "Intensity: fill ${habit.count} times"
            habitPriority.text = "Priority: ${habit.priority}"
            habitPeriodicity.text = "Periodicity: perform in ${habit.frequency} days"
            habitType.text = "Type: ${habit.type}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.fragment_habits, parent, false)
        )
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.itemView.setOnClickListener {
            onBindCallback?.onBind(habits[position])
        }
        holder.bind(habits[position])
    }
}

interface OnBindCallback {
    fun onBind(habit: Habit)
}