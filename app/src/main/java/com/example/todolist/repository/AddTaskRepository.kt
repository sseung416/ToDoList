package com.example.todolist.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.data.Task

class AddTaskRepository(private val context: Context) {

    private val taskDAO: TaskDAO by lazy {
        val db = AppDatabase.getDatabase(context)
        db!!.TaskDAO()
    }

    fun getAllTask(): LiveData<List<Task>> {
        return taskDAO.getAll()
    }

    fun insert(task: Task) {
        Log.d("AddTaskRepository-insert", task.toString())
        taskDAO.insert(task)
    }

    fun delete(task: Task) {
        Log.d("AddTaskRepository-delete", task.toString())
        taskDAO.delete(task)
    }
}