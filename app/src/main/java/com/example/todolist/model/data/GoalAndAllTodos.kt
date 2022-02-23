package com.example.todolist.model.data

import androidx.room.Embedded
import androidx.room.Relation

class GoalAndAllTodos {
    @Embedded var goal: Goal? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "goal_id"
    ) var todos: List<Todo> = arrayListOf()
}
