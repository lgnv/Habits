package com.example.habits.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habits.habit.Habit

class InMemoryHabitsRepository {
    val mutableHabits: MutableLiveData<MutableMap<Int, Habit>> = MutableLiveData(mutableMapOf())
    val habits: LiveData<MutableMap<Int, Habit>> = mutableHabits


    fun addHabit(habit: Habit) {
        if (habit.id == -1) {
            habit.id = mutableHabits.value!!.size
        }
        updateHabit(habit)
    }

    private fun updateHabit(habit: Habit) {
        mutableHabits.value!![habit.id] = habit
    }

    companion object {
        private var instance: InMemoryHabitsRepository? = null
        fun getInstance(): InMemoryHabitsRepository {
            if (instance == null){
                instance =
                    InMemoryHabitsRepository()
            }
            return instance!!
        }
    }
}