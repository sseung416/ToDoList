package com.example.todolist.widget.viewmodel

import android.util.Log
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.TodoRepository
import com.example.todolist.widget.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    val insertEvent = SingleLiveEvent<Unit>()
    val updateEvent = SingleLiveEvent<Unit>()

    fun insertTodo(todo: Todo) {
        addDisposable(todoRepository.insert(todo), {
            insertEvent.call()
        }, {
            Log.e(TAG, "insertTodo: ${it.message}")
        })
    }

    fun updateTodo(todo: Todo) {
        addDisposable(todoRepository.update(todo), {
            updateEvent.call()
        }, {
            Log.e(TAG, "updateTodo: ${it.message}")
        })
    }

    companion object { private const val TAG = "TodoViewModel" }
}