package com.example.habits.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = """
            Трекер привычек
            Автор: Логинов Александр, ФТ-302
            Последнее обновление: 30.03.2020
        """.trimIndent()
    }
    val text: LiveData<String> = _text
}