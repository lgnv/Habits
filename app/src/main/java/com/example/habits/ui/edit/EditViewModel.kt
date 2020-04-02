package com.example.habits.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.repositories.InMemoryHabitsRepository
import com.example.habits.habit.Habit

class EditViewModel(val model: InMemoryHabitsRepository, private val habitIndex: Int = -1): ViewModel() {
    val habit: LiveData<Habit?>
        get() = if (habitIndex != -1) {
            MutableLiveData(model.mutableHabits.value?.get(habitIndex))
        } else {
            MutableLiveData<Habit>()
        }
}