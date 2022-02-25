package com.example.todolist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color")
data class Color(
    @PrimaryKey val goalId: Int,
    val color: Int
)