package com.example.todolist.widget.adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemGoalBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo

class GoalAdapter : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    var onLongClickTodoList: ((Todo)->Boolean)? = null
    var onClickAdd: ((Goal)->Unit)? = null

    private val list = arrayListOf<Goal>()

    inner class ViewHolder(
        private val binding: ItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Goal) {
            binding.btnGoal.apply {
                text = data.goal
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, data.color))
            }

            binding.btnAdd.apply {
                setColorFilter(ContextCompat.getColor(context, data.color), PorterDuff.Mode.SRC_IN)
                setOnClickListener { onClickAdd?.invoke(data) }
            }

            binding.rvTodo.adapter = TodoAdapter().apply {
                onLongClickTodo = onLongClickTodoList
//                setList(data.todoList)
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