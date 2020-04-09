package com.example.habits.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habits.habit.Habit

@Database(entities = [Habit::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}