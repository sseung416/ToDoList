package com.example.todolist.model.dao

import androidx.room.*
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Todo
import io.reactivex.rxjava3.core.Single

@Dao
interface TodoDao : BaseDao<Todo> {
    @Query("SELECT * FROM todo WHERE date = :date ORDER BY id")
    fun getTodosByDate(date: String): Single<List<Todo>>
}