package com.example.todolist.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Goal
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
    val isSuccess = MutableLiveData<List<Goal>>()
    val isFailure = MutableLiveData<String?>()
//    val insertSuccessEvent = SingleLiveData<>()

    fun getAllGoals() {
        addDisposable(goalRepository.allGoals, {
            isSuccess.postValue(it as? List<Goal>)
        }, {
            Log.e(TAG, "getAllGoals: ${it?.message}")
        })
    }

    fun getTodosByDate(goalId: Int, date: String) {
        addDisposable(todoRepository.getTodosByDate(goalId, date), {

        }, {

        })
    }

    fun insertTodo(todo: Todo) {
        addDisposable(todoRepository.insert(todo), {}, {})
    }

    companion object { private const val TAG = "HomeViewModel" }
}