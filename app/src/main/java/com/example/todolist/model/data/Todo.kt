package com.example.todolist.model.data

import androidx.room.*
import com.example.todolist.widget.recyclerview.adapter.TodoAdapter

@Entity(foreignKeys = [ForeignKey(
    entity = Goal::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("goal_id")
)])
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "goal_id") val goalId: Int,
    var todo: String = "",
    var date: String,
    var isCompleted: Boolean = false,
    var isRepeat: Boolean = false,
) {
    // 할일을 출력하는 OUTPUT 타입과 할일을 입력하는 INPUT 타입이 있음
    @Ignore var type: Int = TodoAdapter.OUTPUT
}
