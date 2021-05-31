package com.example.todolist.database

import androidx.room.*
import com.example.todolist.database.data.Task

@Dao
interface TaskDAO {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("DELETE FROM task")
    fun deleteAll()
}