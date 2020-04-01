package com.example.habits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habits.ui.edit.OnSaveHabitCallback
import com.example.myapplication.habit.Habit
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), OnSaveHabitCallback {

    private lateinit var appBarConfiguration: AppBarConfiguration
    val habits = arrayListOf<Habit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_info), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSave(newHabit: Habit, position: Int) {
        if (position == -1)
            habits.add(newHabit)
        else
            habits[position] = newHabit
    }
}
