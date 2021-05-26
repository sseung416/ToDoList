package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var color: Int,
    var content: String,
    var date: String
    ) {
    constructor() : this(null, 0, "", "")
}
