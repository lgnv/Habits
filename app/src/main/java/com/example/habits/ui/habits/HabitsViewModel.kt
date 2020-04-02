package com.example.habits.ui.habits

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.repositories.InMemoryHabitsRepository
import com.example.habits.habit.Habit

class HabitsViewModel(val model: InMemoryHabitsRepository) : ViewModel() {
    val showHabits: MediatorLiveData<List<Habit>> = MediatorLiveData()

    val nameFilter: MutableLiveData<String> = MutableLiveData()

    init {
        showHabits.addSource(nameFilter) { name -> showHabits.value = model.mutableHabits.value!!.values.toList().filter {
                    habit -> (nameFilter.value.isNullOrEmpty() || name in habit.name)
            }
        }
    }

    companion object {
        private var instance: HabitsViewModel? = null
        fun getInstance(model: InMemoryHabitsRepository): HabitsViewModel {
            if (instance == null) {
                instance =
                    HabitsViewModel(model)
            }
            return instance!!
        }
    }
}

