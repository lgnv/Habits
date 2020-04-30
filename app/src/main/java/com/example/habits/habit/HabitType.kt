package com.example.habits.habit

enum class HabitType(val numberType: Int, val titleType: String) {
    GOOD(0, "Good"),
    BAD(1, "Bad");

    companion object {
        fun getByTitle(title: String): HabitType {
            return HabitType.values().first { it.titleType == title }
        }

        fun getByValue(value: Int?): HabitType {
            return HabitType.values().first { it.numberType == value }
        }
    }
}