package com.example.todolist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class AddTaskViewModel : ViewModel() {
    var content = ObservableField("Main")
}