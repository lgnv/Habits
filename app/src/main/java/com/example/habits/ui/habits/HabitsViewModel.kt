package com.example.habits.ui.habits

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habits.App
import com.example.habits.habit.Habit
import com.example.habits.repositories.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsViewModel(private val habitsRepository: HabitsRepository) : ViewModel() {
    private val allHabits: MediatorLiveData<List<Habit>> = MediatorLiveData()
    val showHabits: MediatorLiveData<List<Habit>> = allHabits

    val nameFilter: MutableLiveData<String> = MutableLiveData("")

    init {
        showHabits.addSource(nameFilter) { name ->
            showHabits.value = (allHabits.value ?: listOf()).toList().filter {
                habit -> (nameFilter.value.isNullOrEmpty() || name in habit.title)
            }
        }
        allHabits.addSource(habitsRepository.getAllHabits()) { habits ->
            allHabits.value = habits
        }
        viewModelScope.launch(Dispatchers.Default) {  habitsRepository.initializeHabitsInDB() }
    }


    companion object {
        private var instance: HabitsViewModel? = null
        fun getInstance(): HabitsViewModel {
            if (instance == null) {
                instance = HabitsViewModel(App.habitRepository)
            }
            return instance!!
        }
    }
}

