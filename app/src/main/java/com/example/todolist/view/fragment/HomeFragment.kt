package com.example.todolist.view.fragment

import androidx.fragment.app.activityViewModels
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import com.example.todolist.view.dialog.CreateGoalDialog
import com.example.todolist.view.dialog.HomeEditDialog
import com.example.todolist.viewmodel.dialog.CreateGoalViewModel
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.recyclerview.adapter.GoalAdapter
import com.example.todolist.widget.recyclerview.adapter.WeeklyAdapter
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.livedata.Event
import com.example.todolist.widget.livedata.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @Inject lateinit var homeEditViewModel: HomeEditViewModel
    private val createGoalViewModel: CreateGoalViewModel by activityViewModels()

    private val goalAdapter by lazy { GoalAdapter(viewModel) }

    override fun init() {
        viewModel.getAllGoals()

        binding.btnMenu.setOnClickListener {
            CreateGoalDialog().show(parentFragmentManager, CreateGoalDialog.TAG)
        }

        binding.rvWeekly.adapter = WeeklyAdapter().apply {
            onClickDay = {
                viewModel.selectedDate.value = Event(it)
            }
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
            allGoalList.observe(viewLifecycleOwner, EventObserver {
                repeat(it.size) { getTodosByDate(selectedDate.value!!.peekContent().time.formatToString()) }
            })

            goalById.observe(viewLifecycleOwner, EventObserver {
                goalAdapter.addItem(it)
            })

            todoListByDate.observe(viewLifecycleOwner, EventObserver {
                goalAdapter.setList(convertGoalAndAllTodosList(it))
            })

            selectedDate.observe(viewLifecycleOwner, EventObserver { cal ->
                repeat(allGoalList.value?.peekContent()?.size?: 0) { getTodosByDate(cal.time.formatToString()) }
            })
        }

        createGoalViewModel.insertSuccessEvent.observe(viewLifecycleOwner) {
            viewModel.getAllGoals()
        }

        homeEditViewModel.editEvent.observe(viewLifecycleOwner) {
        }
    }

    private fun convertGoalAndAllTodosList(list: List<Todo>): List<GoalAndAllTodos> =
        arrayListOf<GoalAndAllTodos>().apply {
            val todoMap = list.groupBy { todo -> todo.goalId }
            viewModel.allGoalList.value?.peekContent()?.forEach { goal ->
                this.add(GoalAndAllTodos(goal, todoMap[goal.id]?: listOf()))
            }
        }
}