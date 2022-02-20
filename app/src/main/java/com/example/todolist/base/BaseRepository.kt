package com.example.todolist.base

import io.reactivex.rxjava3.core.Completable

abstract class BaseRepository<D, E> {
    protected abstract val dao: D

    abstract fun insert(obj: E): Completable

    abstract fun update(obj: E): Completable

    abstract fun delete(obj: E): Completable
}