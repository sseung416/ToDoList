package com.example.todolist.model.repository

import com.example.todolist.base.BaseRepository
import com.example.todolist.model.dao.TodoDao
import com.example.todolist.model.data.Todo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TodoRepository @Inject constructor(
    override val dao: TodoDao
) : BaseRepository<TodoDao, Todo>() {
    fun getTodosByDate(date: String) = dao.getTodosByDate(date)

    fun getRepeatTodo(date: String) = dao.getRepeatTodo(date)

    override fun insert(obj: Todo): Single<Long> = dao.insert(obj)

    override fun update(obj: Todo): Completable = dao.update(obj)

    override fun delete(obj: Todo): Completable = dao.delete(obj)
}