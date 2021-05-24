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
import com.example.todolist.data.ToDoListData

class ToDoListAdapter(private val items: ArrayList<ToDoListData>, private val context: Context) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cv_task: CardView = itemView.findViewById(R.id.cv_task_toDoList)
        private val tv_content: TextView = itemView.findViewById(R.id.tv_task_toDoList)

        fun binding(item: ToDoListData, context: Context) {
            cv_task.setBackgroundColor(ContextCompat.getColor(context, item.color))
            tv_content.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_task2, parent, false)
        return ViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items[position], context)
    }

    override fun getItemCount(): Int = items.size
}