package com.example.habits.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habits.habit.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAll(): LiveData<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(habit: Habit)

    @Query("SELECT * FROM habit WHERE uid=:uid")
    fun getById(uid: String?) : LiveData<Habit?>

    @Update
    fun update(habit: Habit)

    @Delete
    fun delete(habit: Habit)
}