package com.example.todolist.view.fragment

import android.util.Log
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo
import com.example.todolist.widget.adapter.GoalAdapter
import com.example.todolist.widget.adapter.WeeklyAdapter
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel
        get() = HomeViewModel()

    private val goalAdapter = GoalAdapter()

    override fun init() {
        binding.rvWeekly.adapter = WeeklyAdapter().apply {
//            onClickDay = {
//
//            }
        }

        goalAdapter.apply {
            binding.rvTodo.adapter = this
            onLongClickTodoList = onLongClickTodoList@ {
                Log.d("home", "init: tq")
                return@onLongClickTodoList true
            }
            setList(listOf(
                Goal(R.color.red, "asdf", listOf(Todo(false, "sibla"))),
                Goal(R.color.red, "please 오잉 대문자만 되네?", listOf(Todo(false, "sibla"), Todo(true, "johna 죽을맛"))),
            ))
        }
    }

    override fun observeViewModel() {
    }
}