package com.example.habits

import android.app.Application
import androidx.room.Room
import com.example.habits.habit.HabitPriority
import com.example.habits.habit.HabitType
import com.example.habits.habit.jsonAdapters.HabitPriorityAdapter
import com.example.habits.habit.jsonAdapters.HabitTypeAdapter
import com.example.habits.network.HabitAPI
import com.example.habits.repositories.room.AppDataBase
import com.example.habits.repositories.HabitsRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        private lateinit var database: AppDataBase
        private lateinit var api: HabitAPI
        lateinit var habitRepository: HabitsRepository
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(applicationContext, AppDataBase::class.java, "Habits")
            .fallbackToDestructiveMigration()
            .build()

        val gson = GsonBuilder()
            .registerTypeAdapter(HabitType::class.java, HabitTypeAdapter())
            .registerTypeAdapter(HabitPriority::class.java, HabitPriorityAdapter())
            .create()

        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor {
                val request = it.request()
                it.proceed(
                    request
                        .newBuilder()
                        .addHeader("Authorization", getString(R.string.apikey_doubletapp))
                        .build()
                )
            }
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()

        val rf = Retrofit.Builder()
            .baseUrl("https://droid-test-server.doubletapp.ru/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = rf.create(HabitAPI::class.java)

        habitRepository = HabitsRepository(database, api)
    }
}