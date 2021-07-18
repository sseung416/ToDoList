package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TaskDAO
import com.example.todolist.database.data.Task
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.repository.AddTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddTaskViewModel(application) as T
        }
    }

    private lateinit var newTask: Task
    private val repository = AddTaskRepository(application.applicationContext)

    private lateinit var _tasks: MutableLiveData<List<Task>>
    var tasks = _tasks

    fun addTask(content: String, selectedPos: Int) {
        newTask = Task()

        newTask.content = content
        newTask.color = selectedPos
        newTask.date = setDate()
    }

    private fun setDate(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        return dateFormat.format(date)
    }

    fun getAllTask() {
        viewModelScope.launch {
           _tasks.postValue(repository.getAllTask())
        }
    }

    private val dialog = AddTaskFragmentDialog(application)

    fun onFinishBtnClick() {
        viewModelScope.launch {
            repository.insert(newTask)
        }
    }

    fun onBackBtnClick() {
        dialog.dismiss()
    }
}