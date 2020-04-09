//package com.example.habits.repositories
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.habits.habit.Habit
//import com.example.habits.repositories.room.AppDataBase
//
//class RoomRepository(val db: AppDataBase) {
//    val mutableHabits: MutableLiveData<MutableMap<Int, Habit>> = MutableLiveData(mutableMapOf())
//    val habits: LiveData<MutableMap<Int, Habit>> = mutableHabits
//
//    init {
//        updateHabits(db.habitDao().getAll().value ?: listOf())
//    }
//
//    fun addHabit(habit: Habit) {
//        db.habitDao().insert(habit)
//        if (habit.id == -1) {
//            habit.id = mutableHabits.value!!.size
//        }
//        updateHabits(listOf(habit))
//    }
//
//    private fun updateHabits(habits: List<Habit>) {
//        for(habit in habits) {
//            mutableHabits.value!![habit.id] = habit
//        }
//
//    }
//
//    companion object {
//        private var instance: RoomRepository? = null
//        fun getInstance(): RoomRepository? {
//            return instance
//        }
//
//        fun getInstance(db: AppDataBase): RoomRepository {
//            if (instance == null) {
//                instance =
//                    RoomRepository(db);
//            }
//            return instance!!
//        }
//    }
//}