package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.data.Task

class ToDoListAdapter(private val items: List<Task>) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    init {
        setHasStableIds(true);
    }

    inner class ViewHolder(itemView: View, private var context: Context) : RecyclerView.ViewHolder(itemView) {
        private val cv_task: CardView = itemView.findViewById(R.id.cv_task_toDoList)
        private val tv_content: TextView = itemView.findViewById(R.id.tv_task_toDoList)

        fun binding(item: Task) {
            cv_task.setBackgroundColor(ContextCompat.getColor(context, item.color))
            tv_content.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_task, parent, false)
        return ViewHolder(inflated, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = position.toLong()
}