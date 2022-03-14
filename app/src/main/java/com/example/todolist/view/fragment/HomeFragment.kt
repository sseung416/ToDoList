package com.example.todolist.view.fragment

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
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
import com.example.todolist.widget.extension.DateExtension
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.extension.getTodayString
import com.example.todolist.widget.extension.getWeekDateString
import com.example.todolist.widget.livedata.Event
import com.example.todolist.widget.livedata.EventObserver
import com.example.todolist.widget.recyclerview.adapter.GoalAdapter
import com.example.todolist.widget.recyclerview.adapter.TodoAdapter
import com.example.todolist.widget.recyclerview.adapter.WeeklyAdapter
import com.example.todolist.widget.recyclerview.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val homeEditViewModel: HomeEditViewModel by activityViewModels()
    private val createGoalViewModel: CreateGoalViewModel by activityViewModels()
    private val todoViewModel: TodoViewModel by activityViewModels()

    private val goalAdapter by lazy { GoalAdapter(todoViewModel) }
    private val weeklyAdapter by lazy { WeeklyAdapter() }

    override fun init() {
        viewModel.getTodoDates(Calendar.MONDAY.getWeekDateString(), Calendar.SUNDAY.getWeekDateString(DateExtension.NEXT_WEEK))
        viewModel.getRepeatTodo(getTodayString())
        viewModel.getAllGoals()

        binding.btnMenu.setOnClickListener {
            CreateGoalDialog().show(parentFragmentManager, CreateGoalDialog.TAG)
        }

        binding.rvWeekly.adapter = weeklyAdapter.apply {
            onClickDay = {
                viewModel.selectedDate.value = Event(it)
                viewModel.getRepeatTodo(it.time.formatToString())
            }
        }

        binding.rvTodo.adapter = goalAdapter.apply {
            onLongClickTodoList = onLongClickTodoList@{
                HomeEditDialog(it).show(parentFragmentManager, HomeEditDialog.TAG)
                return@onLongClickTodoList true
            }
            onKeyDoneTodo = {
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it, 0)
            }
        }
    }

    override fun observeViewModel() {
        with(viewModel) {
            allGoalList.observe(viewLifecycleOwner, EventObserver {
                repeat(it.size) { getTodosByDate(selectedDate.value!!.peekContent().time.formatToString()) }
            })

            todoListByDate.observe(viewLifecycleOwner, EventObserver {
                goalAdapter.setList(convertGoalAndAllTodosList(it))
            })

            selectedDate.observe(viewLifecycleOwner, EventObserver { cal ->
                repeat(
                    allGoalList.value?.peekContent()?.size ?: 0
                ) { getTodosByDate(cal.time.formatToString()) }
                goalAdapter.selectedDate = cal.time.formatToString()
            })

            repeatTodoList.observe(viewLifecycleOwner, EventObserver {
                goalAdapter.setList(convertGoalAndAllTodosList(it))
            })

            todoDateList.observe(viewLifecycleOwner, EventObserver {
                weeklyAdapter.hasTodoDateQueueAddAll(it)
            })
        }

        createGoalViewModel.insertSuccessEvent.observe(viewLifecycleOwner) {
            viewModel.getAllGoals() // todo 모든 데이터 조회하기보다 생성한 목표의 id로 가져오기로 바꾸자
        }

        todoViewModel.insertEvent.observe(viewLifecycleOwner) {
            viewModel.getAllGoals()
            viewModel.getTodoDates(Calendar.MONDAY.getWeekDateString(), Calendar.SUNDAY.getWeekDateString(DateExtension.NEXT_WEEK))
        }

        with(homeEditViewModel) {
            editEvent.observe(viewLifecycleOwner) {
                val todo = it.peekContent()
                val todoAdapter = getGoalViewHolderForTodo(todo).todoAdapter
                if (todo.type == TodoAdapter.INPUT_UPDATE) {
                    todoAdapter.updateTodo(todo)
                } else {
                    todoAdapter.deleteTodo(todo)
                }
            }

            deleteEvent.observe(viewLifecycleOwner) {
                viewModel.getAllGoals()
            }
        }
    }

    private fun convertGoalAndAllTodosList(list: List<Todo>): List<GoalAndAllTodos> =
        arrayListOf<GoalAndAllTodos>().apply {
            val todoMap = list.groupBy { todo -> todo.goalId }
            viewModel.allGoalList.value?.peekContent()?.forEach { goal ->
                this.add(GoalAndAllTodos(goal, todoMap[goal.id] ?: listOf()))
            }
        }

    private fun getGoalViewHolderForTodo(todo: Todo): GoalAdapter.ViewHolder =
        (binding.rvTodo.findViewHolderForAdapterPosition(getGaolAdapterPosition(todo)) as GoalAdapter.ViewHolder)

    private fun getGaolAdapterPosition(todo: Todo): Int =
        goalAdapter.getList().map { goalAndAllTodos -> goalAndAllTodos.goal.id }.toList()
            .indexOf(todo.goalId)
}