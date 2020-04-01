package com.example.habits.ui.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.myapplication.habit.Habit
import com.example.myapplication.habit.HabitPriority
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {

    private val priorities: List<String> = HabitPriority.values().map { e -> e.toString() }

    private lateinit var onSaveCallback: OnSaveHabitCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSaveCallback = activity as OnSaveHabitCallback
    }

    private fun initWithHabit(editHabit: Habit) {
        edit_name.setText(editHabit.name)
        edit_description.setText(editHabit.description)
        edit_intensity.setText(editHabit.intensity.toString())
        edit_periodicity.setText(editHabit.periodicity.toString())
        when(editHabit.type) {
            "Good" -> radio_good.isChecked = true
            "Bad" -> radio_bad.isChecked = true
        }
        spinner.setSelection(priorities.indexOf(editHabit.priority))
    }

    private fun initSpinner() {
        val adapter = this.context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, priorities)
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        spinner.setSelection(0)
    }

    private fun checkEditText(editText: EditText): Boolean {
        if (editText.text.toString() == "") {
            editText.error = "Required"
            return false
        }
        return true
    }

    private fun checkAllEditText(): Boolean {
        val flagEditName = checkEditText(edit_name)
        val flagEditDescription = checkEditText(edit_description)
        val flagEditIntensity = checkEditText(edit_intensity)
        val flagEditPeriodicity = checkEditText(edit_periodicity)
        return  flagEditDescription && flagEditIntensity && flagEditName && flagEditPeriodicity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        var position = -1
        if (arguments != null) {
            val habit = arguments?.get("HABIT") as Habit
            initWithHabit(habit)
            position = (activity as MainActivity).habits.indexOf(habit)
        } else {
            radio_good.isChecked = true
        }
        save_button.setOnClickListener{ view ->
            if (!checkAllEditText())
                return@setOnClickListener
            val habit = Habit(
                edit_name.text.toString().trim(),
                edit_description.text.toString().trim(),
                priorities[spinner.selectedItemPosition],
                when (radio_group.checkedRadioButtonId) {
                    radio_good.id -> radio_good.text
                    radio_bad.id -> radio_bad.text
                    else -> throw Error()
                }.toString(),
                edit_intensity.text.toString().toInt(),
                edit_periodicity.text.toString().toInt()
            )
            onSaveCallback.onSave(habit, position)
            view.findNavController().navigate(R.id.action_editFragment_to_nav_home)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }
}

interface OnSaveHabitCallback {
    fun onSave(newHabit: Habit, position: Int = -1)
}