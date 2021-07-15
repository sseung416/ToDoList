package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.data.Task
import com.example.todolist.fragment.AddTaskFragmentDialog
import kotlinx.coroutines.Dispatchers

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var database: AppDatabase
//    private var taskDao: TaskDAO

    private val _task = MutableLiveData<Task>()
//    private val _content = MutableLiveData<String>() //수정 시

//    val content: LiveData<String>
//        get() = _content

    fun insert() {
        
    }

    fun delete() {

    }

    fun buildDatabase() {
    }

    private val dialog = AddTaskFragmentDialog()

    fun onFinishBtnClick() {
        val fragment = AddTaskFragmentDialog()
        fragment.addTask()

        insert()

        dialog.dismiss()
    }

    fun onBackBtnClick() {
        dialog.dismiss()
    }
}