package com.example.todolist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val goal: String,
    val color: Int,
    val isCompleted: Boolean = false,
)
