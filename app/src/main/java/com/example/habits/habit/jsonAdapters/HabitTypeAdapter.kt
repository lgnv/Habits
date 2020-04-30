package com.example.habits.habit.jsonAdapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.example.habits.habit.HabitType

class HabitTypeAdapter: TypeAdapter<HabitType>() {
    override fun write(writer: JsonWriter?, habitType: HabitType?) {
        writer?.value(habitType?.numberType)
    }

    override fun read(reader: JsonReader?): HabitType {
        return HabitType.getByValue(reader?.nextInt())
    }
}