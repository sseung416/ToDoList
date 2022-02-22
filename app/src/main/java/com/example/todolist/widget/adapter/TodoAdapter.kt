package com.example.todolist.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.data.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private val list = arrayListOf<Todo>()

    var onLongClickTodo: ((Todo)->Boolean)? = null

    inner class ViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Todo) {
            binding.checkBox.apply {
                isChecked = data.isCompleted
                text = data.todo
                setOnLongClickListener {
                    onLongClickTodo?.invoke(data)!!
                }
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<Todo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}