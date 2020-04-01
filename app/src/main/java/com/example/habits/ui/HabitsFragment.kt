package com.example.habits.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.homework_3.HabitRecyclerViewAdapter
import com.example.homework_3.OnBindCallback
import com.example.myapplication.habit.Habit
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var habitsType: String
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        recyclerView = rv.apply {
            layoutManager = LinearLayoutManager(context)
            val habitsAdapter = HabitRecyclerViewAdapter((activity as MainActivity).habits.filter { it.type == habitsType} as ArrayList<Habit>)
            habitsAdapter.onBindCallback = object : OnBindCallback {
                override fun onBind(view: View, position: Int, habit: Habit) {
                    navController.navigate(R.id.editFragment, Bundle().apply {
                        putSerializable("HABIT", habit)
                    })
                }
            }
            adapter = habitsAdapter
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
    }

    companion object {
        fun newInstance(type: String): Fragment {
            return HabitsFragment().apply { habitsType = type }
        }
    }
}
