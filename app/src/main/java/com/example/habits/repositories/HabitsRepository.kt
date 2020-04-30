package com.example.habits.repositories

import androidx.lifecycle.LiveData
import com.example.habits.habit.Habit
import com.example.habits.network.HabitAPI
import com.example.habits.repositories.room.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class HabitsRepository(val db: AppDataBase, val api: HabitAPI) {
    suspend fun initializeHabitsInDB() {
        untilSuccess(
            { api.getHabits() },
            {
                for (habit in it) {
                    val habitDB = getHabitByUid(habit.uid).value
                    if (habitDB == null) {
                        withContext(Dispatchers.IO) { db.habitDao().insert(habit) }
                    } else if (habitDB.date < habit.date) {
                        withContext(Dispatchers.IO) { db.habitDao().update(habit) }
                    }
                }
            }
        )
    }

    suspend fun insertOrUpdate(habit: Habit) {
        untilSuccess(
            { api.putHabit(habit) },
            {
                val previousUid = habit.uid
                habit.uid = it.uid
                if (previousUid == null) {
                    withContext(Dispatchers.IO) { db.habitDao().insert(habit) }
                } else {
                    withContext(Dispatchers.IO) { db.habitDao().update(habit) }
                }
            }
        )
    }

    private suspend fun <T> untilSuccess(
        responseGetter: suspend () -> Response<T>,
        callback: suspend (T) -> Unit
    ) {
        while (true) {
            try {
                val response = responseGetter()
                if (response.isSuccessful) {
                    response.body()?.let { callback(it) }
                    break
                } else {
                    if (response.code() >= 500)  {
                        Thread.sleep(TIMEOUT)
                    } else {
                        break
                    }
                }
            } catch (e: Exception) {
                print(e)
                break
            }
        }
    }

    fun getAllHabits(): LiveData<List<Habit>> = db.habitDao().getAll()

    fun getHabitByUid(uid: String?): LiveData<Habit?> = db.habitDao().getById(uid)

    companion object {
        private const val TIMEOUT = 1000L
    }
}
