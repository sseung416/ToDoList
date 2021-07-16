package com.example.todolist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.data.Task

class TestRepository() {

    private val taskDAO: TaskDAO by lazy {
    }

    fun getAllTaskData(): LiveData<List<Task>> {
        return taskDAO.getAll()
    }

    fun insert(task: Task) {
        Log.d("TestRepository-insert", task.toString())
        taskDAO.insert(task)
    }

    fun delete(task: Task) {
        Log.d("TestRepository-delete", task.toString())
        taskDAO.delete(task)
    }
}