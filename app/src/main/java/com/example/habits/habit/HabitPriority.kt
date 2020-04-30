package com.example.habits.habit

enum class HabitPriority(val numberPriority: Int, val titlePriority: String) {
    LOW(0, "Low"),
    MEDIUM(1, "Medium"),
    HIGH(2, "High");

    companion object {
        fun getByTitle(title: String): HabitPriority {
            return values().first { it.titlePriority == title }
        }

        fun getByValue(value: Int?): HabitPriority {
            return HabitPriority.values().first { it.numberPriority == value }
        }
    }
}