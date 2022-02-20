package com.example.todolist.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(foreignKeys = [ForeignKey(
    entity = Goal::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("goal_id")
)])
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "goal_id") val goalId: Int,
    val todo: String,
    val date: Date,
    val isCompleted: Boolean,
    val isRepeat: Boolean
)
