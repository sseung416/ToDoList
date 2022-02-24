package com.example.todolist.viewmodel.dialog

import android.util.Log
import com.example.todolist.base.BaseViewModel
import com.example.todolist.model.data.Goal
import com.example.todolist.model.repository.GoalRepository
import com.example.todolist.widget.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository
) : BaseViewModel() {
    val insertSuccessEvent = SingleLiveEvent<Unit>()

    fun insertGoal(goal: Goal) {
        addDisposable(goalRepository.insert(goal), {
            insertSuccessEvent.call()
        }, {
            Log.e(TAG, "insertGoal: $it")
        })
    }

    companion object { private const val TAG = "CreateGoalViewModel" }
}