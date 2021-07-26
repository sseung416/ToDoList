package com.example.todolist.adapter

import android.app.Application
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.data.Task
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.databinding.RvItemTaskBinding
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.viewmodel.ToDoListViewModel


class ToDoListAdapter : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    private val list: ArrayList<Task> = ArrayList()
    private lateinit var binding: RvItemTaskBinding

    init {
        setHasStableIds(true);
    }

    class ViewHolder(
        v: View,
        private val context: Context,
        private val binding: RvItemTaskBinding,
        ) : RecyclerView.ViewHolder(v) {

        fun binding(item: Task) {
            binding.tvTaskToDoList.text = item.content
            binding.cvTaskToDoList.setBackgroundColor(ContextCompat.getColor(context, getColor(item.color)))

            binding.ibFinishToDoList.setOnClickListener {
                binding.tvTaskToDoList.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG // 중단선 설정
            }

            binding.ibModifyToDoList.setOnClickListener {
                AddTaskFragmentDialog().show((context as FragmentActivity).supportFragmentManager, "modifyTask")
            }
        }

        private fun getColor(colorPos: Int): Int {
            return when(colorPos) {
                0 -> R.color.red
                1 -> R.color.pink
                2 -> R.color.yellow
                3 -> R.color.green
                4 -> R.color.grey
                else -> R.color.light_grey
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rv_item_task, parent, false)

        return ViewHolder(binding.root, parent.context, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    fun setList(list: List<Task>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}