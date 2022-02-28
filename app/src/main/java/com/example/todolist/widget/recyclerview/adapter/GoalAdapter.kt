package com.example.todolist.widget.recyclerview.adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemGoalBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.extension.formatToString
import java.util.*

class GoalAdapter(
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    var onLongClickTodoList: ((Todo)->Boolean)? = null
    var date: String = Calendar.getInstance().time.formatToString()

    private val list = arrayListOf<GoalAndAllTodos>()

    inner class ViewHolder(
        private val binding: ItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val todoAdapter = TodoAdapter(homeViewModel)

        fun bind(data: GoalAndAllTodos) {
            val goal = data.goal

            binding.btnGoal.apply {
                text = goal.goal
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, goal.color))
            }

            binding.btnAdd.apply {
                setColorFilter(ContextCompat.getColor(context, goal.color), PorterDuff.Mode.SRC_IN)
                setOnClickListener {
                    todoAdapter.addTodo(goal, date)
                }
            }

            binding.rvTodo.adapter = todoAdapter.apply {
                onLongClickTodo = onLongClickTodoList
                setList(data.todos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGoalBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<GoalAndAllTodos>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getList() = list

    fun addItem(goal: Goal) {
        list.add(GoalAndAllTodos(goal, listOf()))
        notifyItemChanged(list.lastIndex)
    }
}