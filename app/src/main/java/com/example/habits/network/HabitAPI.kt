package com.example.habits.network

import com.example.habits.habit.Habit
import com.example.habits.habit.HabitUID
import retrofit2.Response
import retrofit2.http.*

interface HabitAPI {

    @GET("habit")
    suspend fun getHabits(): Response<List<Habit>>

    @PUT("habit")
    suspend fun putHabit(@Body habit: Habit): Response<HabitUID>

    @DELETE("habit")
    suspend fun deleteHabit(@Body habitUID: HabitUID): Response<Unit>
}