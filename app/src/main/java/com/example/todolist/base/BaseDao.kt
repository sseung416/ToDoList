package com.example.todolist.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

interface BaseDao<T> {
    @Insert
    fun insert(obj: T): Completable

    @Update
    fun update(obj: T): Completable

    @Delete
    fun delete(obj: T): Completable
}