package com.example.habits.habit.jsonAdapters

import com.example.habits.habit.HabitPriority
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class HabitPriorityAdapter: TypeAdapter<HabitPriority>() {
    override fun write(writer: JsonWriter?, value: HabitPriority?) {
        writer?.value(value?.numberPriority)
    }

    override fun read(reader: JsonReader?): HabitPriority = HabitPriority.getByValue(reader?.nextInt())
}