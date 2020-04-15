package com.example.habits.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.App
import com.example.habits.habit.Habit
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class EditViewModel(private val habitIndex: Int? = null
): ViewModel(), CoroutineScope {
    private  val job = SupervisorJob();

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, e -> throw e }

    val habit: LiveData<Habit?>
        get() = if (habitIndex != null) {
            MutableLiveData(App.database.habitDao().getById(habitIndex))
        } else {
            MutableLiveData<Habit>()
        }

    fun saveHabit(habit: Habit) = launch {
        App.database.habitDao().apply {
            if (habitIndex == null) {
                withContext(Dispatchers.IO) { insert(habit) }
            } else {
                habit.id = habitIndex
                withContext(Dispatchers.IO) { update(habit) }
            }
        }
    }
}