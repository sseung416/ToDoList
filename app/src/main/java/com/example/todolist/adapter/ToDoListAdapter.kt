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


class ToDoListAdapter(private val application: Application) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    private val list: ArrayList<Task> = ArrayList()
    private lateinit var binding: RvItemTaskBinding

    init {
        setHasStableIds(true);
    }

    class ViewHolder(
        v: View,
        private val context: Context,
        private val binding: RvItemTaskBinding,
        private val application: Application
        ) : RecyclerView.ViewHolder(v) {

        fun binding(item: Task) {
            binding.tvTaskToDoList.text = item.content
            binding.cvTaskToDoList.setBackgroundColor(ContextCompat.getColor(context, item.color))

            binding.ibFinishToDoList.setOnClickListener {
                // 완료 버튼 클릭시 중단선 설정
                binding.tvTaskToDoList.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.ibModifyToDoList.setOnClickListener {
                AddTaskFragmentDialog().show((context as FragmentActivity).supportFragmentManager, "modifyTask")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rv_item_task, parent, false)

        return ViewHolder(binding.root, parent.context, binding, application)
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