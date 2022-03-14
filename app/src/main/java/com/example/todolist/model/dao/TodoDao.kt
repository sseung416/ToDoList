package com.example.todolist.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.todolist.base.BaseDao
import com.example.todolist.model.data.Todo
import io.reactivex.rxjava3.core.Single

@Dao
interface TodoDao : BaseDao<Todo> {
    // 날짜로 할일 조회
    @Query("SELECT * FROM todo WHERE date = :date ORDER BY id")
    fun getTodosByDate(date: String): Single<List<Todo>>

    // 반복하는 할일 조회 (당일이 아닐때만)
    @Query("SELECT * FROM todo WHERE isRepeat = 1 AND date <> :date")
    fun getRepeatTodo(date: String): Single<List<Todo>>

    // 할일을 설정한 날짜들을 반환
    @Query("SELECT date FROM todo WHERE :startDate <= date and date <= :endDate")
    fun getTodoDate(startDate: String, endDate: String): Single<List<String>>
}