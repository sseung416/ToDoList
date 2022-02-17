package com.example.todolist.widget.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.data.Task
import com.example.todolist.databinding.RvItemTaskBinding

class ToDoListAdapter(private val application: Application) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    private lateinit var onClickFinishToDoListener: OnClickToDoListener
    private lateinit var onClickCloseToDoListener: OnClickToDoListener

    interface OnClickToDoListener {
        fun onClick(position: Int)
    }

    fun setOnClickFinishToDoListener(listener: (Int) -> Unit) {
        onClickFinishToDoListener = object : OnClickToDoListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }

    fun setOnClickCloseToDoListener(listener: (Int) -> Unit) {
        onClickCloseToDoListener = object : OnClickToDoListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }


    private val list: ArrayList<Task> = ArrayList()
    private lateinit var binding: RvItemTaskBinding

    init {
        setHasStableIds(true);
    }

    inner class ViewHolder(v: View, private val context: Context) : RecyclerView.ViewHolder(v) {

        fun binding(item: Task) {
            binding.tvTaskToDoList.text = item.content
            binding.cvTaskToDoList.setBackgroundColor(ContextCompat.getColor(context, getColor(item.color)))

            binding.ibFinishToDoList.setOnClickListener {
                onClickFinishToDoListener.onClick(adapterPosition)
            }

            binding.ibCloseToDoList.setOnClickListener {
                onClickCloseToDoListener.onClick(adapterPosition)
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

        return ViewHolder(binding.root, parent.context)
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