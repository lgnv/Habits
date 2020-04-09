package com.example.habits.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habits.habit.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    fun getAll(): LiveData<List<Habit>>

    @Insert
    fun insert(habit: Habit)

    @Query("SELECT * FROM habit WHERE id = (:id)")
    fun getById(id: Int) : Habit

    @Update
    fun update(habit: Habit)
}