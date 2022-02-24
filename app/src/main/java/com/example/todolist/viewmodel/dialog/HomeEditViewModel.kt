package com.example.todolist.viewmodel.dialog

import android.util.Log
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.TodoRepository
import com.example.todolist.widget.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeEditViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    val editEvent = SingleLiveEvent<Unit>()

    fun updateTodo(todo: Todo) {
        addDisposable(todoRepository.update(todo), {}, {
            Log.e(TAG, "updateTodo: $it")
        })
    }

    fun deleteTodo(todo: Todo) {
        addDisposable(todoRepository.delete(todo), {}, {})
    }

    companion object { private const val TAG = "HomeEditViewModel" }
}