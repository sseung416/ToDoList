package com.example.todolist.view.fragment

import androidx.fragment.app.activityViewModels
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.view.dialog.CreateGoalDialog
import com.example.todolist.view.dialog.HomeEditDialog
import com.example.todolist.viewmodel.dialog.CreateGoalViewModel
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.adapter.GoalAdapter
import com.example.todolist.widget.adapter.WeeklyAdapter
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.livedata.EventObserver
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
        viewModel.getAllGoals()

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
        with (viewModel) {
            getAllGoalsEvent.observe(viewLifecycleOwner, EventObserver {
                repeat(it.size) { getTodosByDate("20220223") }  // todo date 바꿔주기
            })

            test.observe(viewLifecycleOwner, EventObserver {
                val list = arrayListOf<GoalAndAllTodos>()
                val todoMap = it.groupBy { todo -> todo.goalId }
                getAllGoalsEvent.value?.peekContent()?.forEach { goal ->
                    list.add(GoalAndAllTodos(goal, todoMap[goal.id]?: listOf()))
                }

                goalAdapter.setList(list)
            })
        }


        createGoalViewModel.insertSuccessEvent.observe(viewLifecycleOwner) {
//            viewModel.getGoalAndTodosByDate(Calendar.getInstance().time.formatToString())
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