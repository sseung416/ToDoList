package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.TaskDatabase

class ToDoListViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: TaskDAO

    init {
        val db = Room.databaseBuilder(application, TaskDatabase::class.java, "task").build()
        dao = db.getTaskDao()
    }

    fun getAll() = dao.getAll()

    /* onClick */
    fun onModifyBtnClick() {

    }

    fun onFinishBtnClick() {

    }
}