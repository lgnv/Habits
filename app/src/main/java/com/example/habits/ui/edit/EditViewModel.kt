package com.example.habits.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.App
import com.example.habits.habit.Habit

class EditViewModel(private val habitIndex: Int? = null): ViewModel() {
    val habit: LiveData<Habit?>
        get() = if (habitIndex != null) {
            MutableLiveData(App.database.habitDao().getById(habitIndex))
        } else {
            MutableLiveData<Habit>()
        }

    fun saveHabit(habit: Habit) {
        App.database.habitDao().apply {
            if (habitIndex == null) {
                insert(habit)
            } else {
                habit.id = habitIndex
                update(habit)
            }
        }
    }
}