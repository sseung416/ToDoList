package com.example.todolist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var color: Int,
    var content: String,
    var date: String
    ) {
    constructor() : this(null, 0, "", "")
}
