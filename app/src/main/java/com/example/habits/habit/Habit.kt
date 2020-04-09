package com.example.habits.habit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Habit(@ColumnInfo val name: String,
            @ColumnInfo val description: String,
            @ColumnInfo val priority: String,
            @ColumnInfo val type: String,
            @ColumnInfo val intensity: Int,
            @ColumnInfo val periodicity: Int) : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}