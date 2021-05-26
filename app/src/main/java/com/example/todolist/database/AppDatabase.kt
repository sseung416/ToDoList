package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TaskDAO(): TaskDAO
}