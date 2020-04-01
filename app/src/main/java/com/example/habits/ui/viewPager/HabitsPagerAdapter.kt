package com.example.habits.ui.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habits.ui.HabitsFragment
import com.example.myapplication.habit.Habit


class HabitsPagerAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HabitsFragment.newInstance("Good")
            else -> HabitsFragment.newInstance("Bad")
        }
    }
}