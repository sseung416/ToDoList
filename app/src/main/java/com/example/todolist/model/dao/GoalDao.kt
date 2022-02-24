package com.example.todolist.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.GoalAndAllTodos
import io.reactivex.rxjava3.core.Single

@Dao
interface GoalDao : BaseDao<Goal> {
    @Query("SELECT * FROM goal")
    fun getGoalList(): Single<List<Goal>>

    @Transaction
    @Query("SELECT * FROM goal LEFT JOIN todo ON goal.id = todo.goal_id AND todo.date = :date")
    fun getGoalAndTodosByDate(date: String): Single<List<GoalAndAllTodos>>

    @Query("SELECT id FROM goal")
    fun getGoalIds(): Single<List<Int>>
}