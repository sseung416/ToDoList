package com.example.todolist.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import com.example.todolist.model.repository.GoalRepository
import com.example.todolist.model.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val todoRepository: TodoRepository
) : BaseViewModel() {
    val isSuccessGetGoalAndTodosByDate = MutableLiveData<List<GoalAndAllTodos>>()
    lateinit var list: List<Int>

    fun getGoalAndTodosByDate(date: String) {
        addDisposable(goalRepository.allGoalIds, { list = it as List<Int> }, {})

        addDisposable(goalRepository.getGoalAndTodosByDate(date), {
            (it as List<GoalAndAllTodos>).forEach { it ->
                Log.e(TAG, "getGoalAndTodosByDate: ${it.goal}")
                it.todos.forEach { i ->
                    Log.e(TAG, i.toString())
                }
            }
            isSuccessGetGoalAndTodosByDate.postValue(it as List<GoalAndAllTodos>)
        }, {
            Log.e(TAG, "getGoalAndAllTodos: ${it?.message}", )
        })
    }

    fun insertTodo(todo: Todo) {
        addDisposable(todoRepository.insert(todo), {}, {
            Log.e(TAG, "insertTodo: ${it.message}", )
        })
    }
    
    fun updateTodo(todo: Todo) {
        addDisposable(todoRepository.update(todo), {}, {
            Log.e(TAG, "updateTodo: ${it.message}", )
        })
    }

    companion object { private const val TAG = "HomeViewModel" }
}