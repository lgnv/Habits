package com.example.habits.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.habits.R
import com.example.habits.ui.filter.FilterFragment
import com.example.habits.ui.viewPager.HabitsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filter = FilterFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.containerBottomSheet, filter)
            .commit();
        homeViewPager.adapter = HabitsPagerAdapter(this)
        TabLayoutMediator(tabs, homeViewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Good"
                else -> "Bad"
            }
        }.attach()
        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_editFragment))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}


