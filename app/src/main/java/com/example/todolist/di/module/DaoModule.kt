package com.example.todolist.di.module

import com.example.todolist.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideGoalDao(appDatabase: AppDatabase) = appDatabase.goalDag()

    @Provides
    @Singleton
    fun provideTodoDao(appDatabase: AppDatabase) = appDatabase.todoDao()
}