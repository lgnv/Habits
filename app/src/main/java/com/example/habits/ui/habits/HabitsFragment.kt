package com.example.habits.ui.habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.App
import com.example.habits.R
import com.example.habits.habit.Habit
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var habitsType: String = "Good"
    private lateinit var navController: NavController
    private lateinit var viewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HabitsViewModel.getInstance()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }
    private fun initRecycleView(habits: List<Habit>) {
        recyclerView = rv.apply {
            layoutManager = LinearLayoutManager(context)
            val filteredHabits = if (habitsType == "Bad") {
                habits.filter { h -> h.type == "Bad" }
            } else {
                habits.filter { h -> h.type == "Good" }
            }
            val habitsAdapter = HabitRecyclerViewAdapter(filteredHabits as ArrayList<Habit>)
            habitsAdapter.onBindCallback = object : OnBindCallback {
                override fun onBind(habit: Habit) {
                    navController.navigate(R.id.editFragment, Bundle().apply {
                        putInt(context.getString(R.string.key_id), habit.id!!)
                    })
                }
            }
            adapter = habitsAdapter
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.showHabits.observe(viewLifecycleOwner, Observer { habits ->
            initRecycleView(habits)
        })
    }

    companion object {
        fun newInstance(type: String): Fragment = HabitsFragment() .apply { habitsType = type }
    }
}
