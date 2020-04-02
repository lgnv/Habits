package com.example.habits.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habits.repositories.InMemoryHabitsRepository
import com.example.habits.R
import com.example.habits.ui.habits.HabitsViewModel
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : Fragment() {
    private lateinit var viewModel: HabitsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = HabitsViewModel.getInstance(InMemoryHabitsRepository.getInstance())

        filter.setOnClickListener {
            viewModel.nameFilter.postValue(search.query.toString())
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }
}
