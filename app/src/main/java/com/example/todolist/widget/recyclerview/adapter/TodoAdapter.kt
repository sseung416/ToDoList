package com.example.todolist.widget.recyclerview.adapter

import android.os.IBinder
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.databinding.ItemTodoInputBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.viewmodel.fragment.HomeViewModel
import com.example.todolist.widget.livedata.Event
import com.example.todolist.widget.livedata.SingleLiveEvent

class TodoAdapter(
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = arrayListOf<Todo>()

    var onLongClickTodo: ((Todo)->Boolean)? = null
    var onKeyDone: ((IBinder)->Unit)? = null

    inner class TextViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Todo) {
            binding.checkBox.apply {
                isChecked = data.isCompleted
                text = data.todo
                setOnLongClickListener { onLongClickTodo?.invoke(data)!! }
                setOnCheckedChangeListener { _, _ ->
                    homeViewModel.updateTodo(data.apply { isCompleted = true })
                }
             }
        }
    }

    inner class InputViewHolder(
        private val binding: ItemTodoInputBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.etTodo.apply {
                homeViewModel.insertTodo(todo)

                requestFocus()
                setOnEditorActionListener { _, actionId, _ ->
                    return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) {
                        todo.apply {
                            this.todo = text.toString()
                            this.type = OUTPUT
                            homeViewModel.updateTodo(this)
                            list[list.lastIndex] = this
                        }
                        notifyItemChanged(list.lastIndex)
                        onKeyDone?.invoke(windowToken)
                        true
                    } else false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            INPUT -> InputViewHolder(
                ItemTodoInputBinding.inflate(LayoutInflater.from(parent.context), null, false).apply { setParentViewGroup(this) }
            )
            else -> TextViewHolder(
                ItemTodoBinding.inflate(LayoutInflater.from(parent.context), null, false).apply { setParentViewGroup(this) }
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextViewHolder) holder.bind(list[position])
        else if (holder is InputViewHolder) holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].type

    fun setList(list: List<Todo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addTodo(goal: Goal, date: String) {
        val todo = Todo(goalId = goal.id!!, date = date).apply { type = INPUT }
        list.add(todo)
        notifyItemInserted(list.lastIndex)
    }

    fun editTodo(todo: Todo) {
        val position = list.indexOf(todo)
        todo.type = INPUT
        notifyItemChanged(position)
    }

    private fun <B : ViewBinding> setParentViewGroup(binding: B) {
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val OUTPUT = 0
        private const val INPUT = 1
    }
}