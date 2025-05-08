package com.aboj.lab5mvvm.ViewModel

import androidx.lifecycle.ViewModel
import com.aboj.lab5mvvm.Model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value.toMutableList().apply { add(task) }
    }
}