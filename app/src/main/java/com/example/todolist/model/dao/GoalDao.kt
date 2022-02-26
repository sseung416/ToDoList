package com.example.todolist.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import io.reactivex.rxjava3.core.Single

@Dao
interface GoalDao : BaseDao<Goal> {
    @Query("SELECT * FROM goal")
    fun getGoalList(): Single<List<Goal>>

    @Query("SELECT * FROM goal WHERE id = :id")
    fun getGoalById(id: Int): Single<Goal>
}
