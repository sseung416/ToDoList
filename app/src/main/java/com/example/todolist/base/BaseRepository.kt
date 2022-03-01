package com.example.todolist.base

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

abstract class BaseRepository<D, E> {
    protected abstract val dao: D

    abstract fun insert(obj: E): Single<Long>

    abstract fun update(obj: E): Completable

    abstract fun delete(obj: E): Completable
}