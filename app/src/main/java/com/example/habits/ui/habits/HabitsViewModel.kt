package com.example.habits.ui.habits

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.App
import com.example.habits.habit.Habit

class HabitsViewModel : ViewModel() {
    val allHabits: MediatorLiveData<List<Habit>> = MediatorLiveData()
    val showHabits: MediatorLiveData<List<Habit>> = allHabits

    val nameFilter: MutableLiveData<String> = MutableLiveData("")

    init {
        showHabits.addSource(nameFilter) { name ->
            showHabits.value = (allHabits.value ?: listOf()).toList().filter {
                habit -> (nameFilter.value.isNullOrEmpty() || name in habit.name)
            }
        }
        allHabits.addSource(App.database.habitDao().getAll()) { habits ->
            allHabits.value = habits
        }
    }

    companion object {
        private var instance: HabitsViewModel? = null
        fun getInstance(): HabitsViewModel {
            if (instance == null) {
                instance = HabitsViewModel()
            }
            return instance!!
        }
    }
}

