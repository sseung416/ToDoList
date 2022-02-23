package com.example.todolist.widget.viewmodel

import android.util.Log
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    fun addTodo(todo: Todo) {
        addDisposable(todoRepository.insert(todo), {}, {
            Log.e(TAG, "addTodo: ${it.message}", )
        })
    }

    fun updateTodo(todo: Todo) {}

    companion object { private const val TAG = "TodoViewModel" }
}