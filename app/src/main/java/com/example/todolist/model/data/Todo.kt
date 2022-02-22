package com.example.todolist.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Goal::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("goal_id")
)])
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "goal_id") val goalId: Int,
    var todo: String,
    var date: String,
    var isCompleted: Boolean,
    var isRepeat: Boolean
)
