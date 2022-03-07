package com.example.todolist.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.GoalRepository
import com.example.todolist.model.repository.TodoRepository
import com.example.todolist.widget.livedata.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    val selectedDate = MutableLiveData<Event<Calendar>>(Event(Calendar.getInstance()))

    val allGoalList = MutableLiveData<Event<List<Goal>>>()
    val todoListByDate = MutableLiveData<Event<List<Todo>>>()
    val repeatTodoList = MutableLiveData<Event<List<Todo>>>()
    val todoDateList = MutableLiveData<Event<List<Date>>>()

    fun getAllGoals() {
        addDisposable(goalRepository.allGoals, {
            allGoalList.postValue(Event(it as List<Goal>))
        }, {
            Log.e(TAG, "getAllGoals: ${it.message}")
        })
    }

    fun getTodosByDate(date: String) {
        addDisposable(todoRepository.getTodosByDate(date), {
            todoListByDate.postValue(Event(it as List<Todo>))
        }, {
            Log.e(TAG, "getTodosByDate: ${it.message}")
        })
    }

    fun getRepeatTodo(date: String) {
        addDisposable(todoRepository.getRepeatTodo(date), {
            repeatTodoList.postValue(Event(it as List<Todo>))
        }, {
            Log.e(TAG, "getRepeatTodo: ${it.message}")
        })
    }

    fun getTodoDateList(startDate: String, endDate: String) {

    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}