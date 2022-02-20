package com.example.todolist.di.module

import com.example.todolist.model.dao.GoalDao
import com.example.todolist.model.dao.TodoDao
import com.example.todolist.model.repository.GoalRepository
import com.example.todolist.model.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun goalRepository(goalDao: GoalDao) = GoalRepository(goalDao)

    @Singleton
    @Provides
    fun todoRepository(todoDao: TodoDao) = TodoRepository(todoDao)
}