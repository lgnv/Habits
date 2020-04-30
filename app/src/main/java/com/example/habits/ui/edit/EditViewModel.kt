package com.example.habits.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habits.habit.Habit
import com.example.habits.repositories.HabitsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class EditViewModel(
    habitIndex: String?,
    private val habitRepository: HabitsRepository
): ViewModel() {

    val habit: LiveData<Habit?> =  habitRepository.getHabitByUid(habitIndex)

    fun saveHabit(habit: Habit) = viewModelScope.launch(Dispatchers.Default) {
        habitRepository.insertOrUpdate(habit)
    }
}