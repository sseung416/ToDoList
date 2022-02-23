package com.example.todolist.view.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.data.Todo
import com.example.todolist.view.dialog.CreateGoalDialog
import com.example.todolist.view.dialog.HomeEditDialog
import com.example.todolist.viewmodel.dialog.CreateGoalViewModel
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.adapter.GoalAdapter
import com.example.todolist.widget.adapter.WeeklyAdapter
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @Inject lateinit var homeEditViewModel: HomeEditViewModel
    private val createGoalViewModel: CreateGoalViewModel by activityViewModels()

    private val goalAdapter by lazy { GoalAdapter(viewModel) }

    override fun init() {
        setTodayDate()
        viewModel.getGoalAndTodosByDate("20220222")  // todo 날짜 바꾸는 거.. 알지?

        binding.btnMenu.setOnClickListener {
            CreateGoalDialog().show(parentFragmentManager, CreateGoalDialog.TAG)
        }

        binding.rvWeekly.adapter = WeeklyAdapter().apply {
        }

        binding.rvTodo.adapter = goalAdapter.apply {
            onLongClickTodoList = onLongClickTodoList@{
                HomeEditDialog(it).show(parentFragmentManager, HomeEditDialog.TAG)
                return@onLongClickTodoList true
            }
        }
    }

    override fun observeViewModel() {
        viewModel.isSuccessGetGoalAndTodosByDate.observe(viewLifecycleOwner) {
            it.map { it.goal?.id = viewModel.list.iterator().next() }
            goalAdapter.setList(it)
        }

        createGoalViewModel.insertSuccessEvent.observe(viewLifecycleOwner) {
            viewModel.getGoalAndTodosByDate(Calendar.getInstance().time.formatToString())
        }

        homeEditViewModel.editEvent.observe(viewLifecycleOwner) {
        }
    }

    private fun setTodayDate() {
        Calendar.getInstance(Locale.KOREA).time.formatToString().apply {
            binding.tvDate.text = "${substring(0, 4)}년 ${substring(5, 6)}월 ${substring(6)}일"
        }
    }
}