package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.TaskDatabase
import com.example.todolist.database.data.Task
import com.example.todolist.fragment.AddTaskFragmentDialog

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao: TaskDAO

    private val _taskMutableLiveData = MutableLiveData<Task>()
    private val _content = MutableLiveData<String>()

    val content: LiveData<String>
        get() = _content

    private val dialog = AddTaskFragmentDialog()

    init {
        val db = Room.databaseBuilder(application, TaskDatabase::class.java, "task").build()
        taskDao = db.getTaskDao()
    }

    fun insert(task: Task) {
        taskDao.insert(task)
    }

    fun delete(task: Task) {
        taskDao.delete(task)
    }

    fun onFinishBtnClcik() {


//        insert(newTask)
        dialog.dismiss()
    }

    fun onBackBtnClick() {
        dialog.dismiss()
    }


    fun getAll() : LiveData<List<Task>> = taskDao.getAll()

    fun getTask() : LiveData<Task> = _taskMutableLiveData

    fun setContent(s: String) {
        _content.value = s
    }
}