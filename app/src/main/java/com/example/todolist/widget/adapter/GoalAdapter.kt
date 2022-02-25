package com.example.todolist.widget.adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemGoalBinding
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import com.example.todolist.viewmodel.fragment.HomeViewModel

class GoalAdapter(
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    var onLongClickTodoList: ((Todo)->Boolean)? = null

    private val list = arrayListOf<GoalAndAllTodos>()

    inner class ViewHolder(
        private val binding: ItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GoalAndAllTodos) {
            val goal = data.goal
            val todoAdapter = TodoAdapter(homeViewModel)

            binding.btnGoal.apply {
                text = goal.goal
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, goal.color))
            }

            binding.btnAdd.apply {
                setColorFilter(ContextCompat.getColor(context, goal.color), PorterDuff.Mode.SRC_IN)
                setOnClickListener {
                    todoAdapter.addTodo(goal)
                }
            }

            binding.rvTodo.adapter = todoAdapter.apply {
                onLongClickTodo = onLongClickTodoList
                setList(data.todos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalAdapter.ViewHolder {
        return ViewHolder(ItemGoalBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GoalAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<GoalAndAllTodos>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}