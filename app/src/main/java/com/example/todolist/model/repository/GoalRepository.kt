package com.example.todolist.model.repository

import com.example.todolist.base.BaseRepository
import com.example.todolist.model.dao.GoalDao
import com.example.todolist.model.data.Goal
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class GoalRepository @Inject constructor(
    override val dao: GoalDao
) : BaseRepository<GoalDao, Goal>() {
    val allGoals = dao.getGoalList()

    fun getGoalById(id: Int) = dao.getGoalById(id)

    override fun insert(obj: Goal): Completable = dao.insert(obj)

    override fun update(obj: Goal): Completable = dao.update(obj)

    override fun delete(obj: Goal): Completable = dao.delete(obj)
}