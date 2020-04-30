package com.example.habits.habit.dbAdapters

import androidx.room.TypeConverter
import com.example.habits.habit.HabitPriority

class HabitPriorityAdapter {
    @TypeConverter
    fun fromHabitType(habitPriority: HabitPriority?): Int? = habitPriority?.numberPriority

    @TypeConverter
    fun intToHabitType(value: Int?): HabitPriority? = HabitPriority.getByValue(value)
}

