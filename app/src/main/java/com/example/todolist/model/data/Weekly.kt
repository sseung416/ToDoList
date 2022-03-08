package com.example.todolist.model.data

data class Weekly(
    val date: String,
    val day: String,
    var isSelected: Boolean = false
)