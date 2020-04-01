package com.example.myapplication.habit

import java.io.Serializable

class Habit(val name: String,
            val description: String,
            val priority: String,
            val type: String,
            val intensity: Int,
            val periodicity: Int) : Serializable