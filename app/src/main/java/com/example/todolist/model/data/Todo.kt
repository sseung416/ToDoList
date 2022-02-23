package com.example.todolist.model.data

import androidx.room.*
import com.example.todolist.widget.adapter.TodoAdapter

@Entity(foreignKeys = [ForeignKey(
    entity = Goal::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("goal_id")
)])
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "goal_id") val goalId: Int,
    var todo: String = "",
    var date: String,
    var isCompleted: Boolean = false,
    var isRepeat: Boolean = false,
) {
    @Ignore var type: Int = TodoAdapter.TEXT
}
