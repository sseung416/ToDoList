package com.example.todolist.viewmodel.dialog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.TodoRepository
import com.example.todolist.widget.livedata.Event
import com.example.todolist.widget.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeEditViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    val editEvent = MutableLiveData<Event<Todo>>()
    val deleteEvent = SingleLiveEvent<Unit>()

    fun updateTodo(todo: Todo) {
        addDisposable(todoRepository.update(todo), {
            editEvent.postValue(Event(todo))
        }, {
            Log.e(TAG, "updateTodo: $it")
        })
    }

    fun deleteTodo(todo: Todo) {
        addDisposable(todoRepository.delete(todo), {
            deleteEvent.call()
        }, {
            Log.e(TAG, "deleteTodo: ${it.message}")
        })
    }

    companion object {
        private const val TAG = "HomeEditViewModel"
    }
}