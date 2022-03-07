package com.example.todolist.widget.recyclerview.adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.IBinder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemGoalBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.GoalAndAllTodos
import com.example.todolist.model.data.Todo
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.viewmodel.TodoViewModel
import java.util.*

class GoalAdapter(
    private val todoViewModel: TodoViewModel
) : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    var onLongClickTodoList: ((Todo)->Boolean)? = null
    var onKeyDoneTodo: ((IBinder)->Unit)? = null

    var selectedDate: String = Calendar.getInstance().time.formatToString()

    private val list = arrayListOf<GoalAndAllTodos>()

    inner class ViewHolder(
        private val binding: ItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val todoAdapter = TodoAdapter(todoViewModel)

        fun bind(data: GoalAndAllTodos) {
            val goal = data.goal

            binding.btnGoal.apply {
                text = goal.goal
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, goal.color))
            }

            binding.btnAdd.apply {
                setColorFilter(ContextCompat.getColor(context, goal.color), PorterDuff.Mode.SRC_IN)
                setOnClickListener {
                    todoAdapter.addTodo(goal, selectedDate)
                }
            }

            binding.rvTodo.adapter = todoAdapter.apply {
                onLongClickTodo = onLongClickTodoList
                onKeyDone = onKeyDoneTodo
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