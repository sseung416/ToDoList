package com.example.todolist.model.repository

import android.content.Context
import android.util.Log
import com.example.todolist.model.AppDatabase
import com.example.todolist.model.TaskDAO
import com.example.todolist.model.data.Task

class AddTaskRepository(private val context: Context) {

    private val taskDAO: TaskDAO by lazy {
        val db = AppDatabase.getDatabase(context)
        db!!.TaskDAO()
    }

    fun getAllTask(): List<Task> {
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