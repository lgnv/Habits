package com.example.habits.habit.dbAdapters

import androidx.room.TypeConverter
import com.example.habits.habit.HabitType

class HabitTypeAdapter {
    @TypeConverter
    fun fromHabitType(habitType: HabitType?): Int? = habitType?.numberType

    @TypeConverter
    fun intToHabitType(value: Int?): HabitType? = HabitType.getByValue(value)
}