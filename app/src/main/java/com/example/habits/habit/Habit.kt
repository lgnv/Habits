package com.example.habits.habit

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.habits.habit.dbAdapters.HabitPriorityAdapter
import com.example.habits.habit.dbAdapters.HabitTypeAdapter
import java.io.Serializable

@Entity
@TypeConverters(HabitPriorityAdapter::class, HabitTypeAdapter::class)
data class Habit(@ColumnInfo val title: String,
                 @ColumnInfo val description: String,
                 @ColumnInfo val priority: HabitPriority,
                 @ColumnInfo val type: HabitType,
                 @ColumnInfo val count: Int,
                 @ColumnInfo val frequency: Int,
                 @ColumnInfo val date: Int) : Serializable {
    @PrimaryKey
    @NonNull
    var uid: String? = null
}