package com.example.todolist.view.fragment

import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.view.dialog.HomeEditDialog
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.adapter.GoalAdapter
import com.example.todolist.widget.adapter.WeeklyAdapter
import com.example.todolist.widget.extension.formatToString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @Inject lateinit var homeEditViewModel: HomeEditViewModel

    override fun init() {
        setTodayDate()

        binding.rvWeekly.adapter = WeeklyAdapter().apply {
//            onClickDay = { i, j ->
//
//            }
        }

        binding.rvTodo.adapter = GoalAdapter().apply {
            onLongClickTodoList = onLongClickTodoList@{
                HomeEditDialog(it).show(parentFragmentManager, "HomeEditDialog")
                return@onLongClickTodoList true
            }
        }
    }

    override fun observeViewModel() {
        with(viewModel) {}

        homeEditViewModel.editEvent.observe(viewLifecycleOwner) {

        }
    }

    private fun setTodayDate() {
        Calendar.getInstance().time.formatToString().apply {
            binding.tvDate.text = "${substring(0, 4)}년 ${substring(5, 6)}월 ${substring(6)}일"
        }
    }
}