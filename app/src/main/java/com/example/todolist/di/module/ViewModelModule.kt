package com.example.todolist.di.module

import com.example.todolist.model.repository.GoalRepository
import com.example.todolist.model.repository.TodoRepository
import com.example.todolist.viewmodel.dialog.CreateGoalViewModel
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun provideCreateGoalViewModel(goalRepository: GoalRepository) =
        CreateGoalViewModel(goalRepository)

    @Singleton
    @Provides
    fun provideHomeViewModel(goalRepository: GoalRepository, todoRepository: TodoRepository) =
        HomeViewModel(goalRepository, todoRepository)

    @Singleton
    @Provides
    fun provideHomeEditViewModel(todoRepository: TodoRepository) =
        HomeEditViewModel(todoRepository)
}