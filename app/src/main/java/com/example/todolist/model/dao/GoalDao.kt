package com.example.todolist.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Goal
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GoalDao : BaseDao<Goal> {
    @Query("Select * from goal")
    fun getGoalList(): Flowable<List<Goal>>
}