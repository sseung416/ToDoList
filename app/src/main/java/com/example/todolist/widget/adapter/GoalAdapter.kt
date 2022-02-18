package com.example.todolist.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemGoalBinding
import com.example.todolist.model.data.Goal

class GoalAdapter : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    var onLongClickTodoList: ((Int)->Boolean)? = null

    private val list = arrayListOf<Goal>()

    inner class ViewHolder(
        private val binding: ItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Goal) {
            binding.btnGoal.text = data.goal
            TodoAdapter().apply {
                binding.rvTodo.adapter = this
                onLongClickTodo = onLongClickTodoList
                setList(data.todoList)
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

    fun setList(list: List<Goal>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}