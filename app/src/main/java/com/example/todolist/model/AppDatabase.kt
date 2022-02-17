package com.example.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun TaskDAO() : TaskDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase? {
            if(instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "TaskDatabase").build()
            }

            return instance
        }
    }
}
