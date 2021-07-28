package com.example.todolist.viewmodel

import android.app.Application
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.example.todolist.R
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

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddTaskViewModel(application) as T
        }
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            repository = AddTaskRepository(application.applicationContext)
        }
    }

    private lateinit var newTask: Task
    private lateinit var repository: AddTaskRepository

    private var _tasks = MutableLiveData<List<Task>>()
    var tasks = _tasks


    fun addTask(content: String, selectedPos: Int) {
        newTask = Task()

        newTask.content = content
        newTask.color = selectedPos
        newTask.date = getDate()
    }

    fun getDate(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        return dateFormat.format(date)
    }

    fun getAllTask() {
        viewModelScope.launch(Dispatchers.Default) {
           _tasks.postValue(repository.getAllTask())
        }
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insert(newTask)
        }
    }

    private lateinit var soundPool: SoundPool
    fun setSound() {
        soundPool = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2) // 동시에 재생 가능 수
                .build()
        } else {
            SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        }
    }

    fun playSound() {
        val soundId = soundPool.load(getApplication(), R.raw.sound_ding, 1)
        soundPool.setOnLoadCompleteListener { soundPool, i, status ->
            val streamId = soundPool.play(soundId, 0.6f, 0.6f, 1, 0, 1f)
        }
    }
}