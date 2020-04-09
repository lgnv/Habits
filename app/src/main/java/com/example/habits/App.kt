package com.example.habits

import android.app.Application
import androidx.room.Room
import com.example.habits.repositories.room.AppDataBase

class App : Application() {
    companion object {
        lateinit var database: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "Habits")
            .allowMainThreadQueries()
            .build()
    }
}