package com.example.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.dao.GoalDao
import com.example.todolist.model.dao.TodoDao
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo

@Database(entities = [Goal::class, Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun goalDag(): GoalDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "todo.db").build()
                INSTANCE = instance
                instance
            }

        fun destroyINSTANCE() {
            INSTANCE = null
        }
    }
}
