package com.example.todolist.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.GoalAndAllTodos
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GoalDao : BaseDao<Goal> {
    @Query("SELECT * FROM goal")
    fun getGoalList(): Flowable<List<Goal>>

    @Transaction
    @Query("SELECT * FROM goal LEFT JOIN todo ON goal.id = todo.goal_id AND todo.date = :date")
    fun getGoalAndTodosByDate(date: String): Flowable<List<GoalAndAllTodos>>

    /* id가 null 값으로 오는 오류 때문에 따로 id만 조회하는 query를 작성함 */
    @Query("SELECT id FROM goal")
    fun getGoalIds(): Flowable<List<Int>>
}