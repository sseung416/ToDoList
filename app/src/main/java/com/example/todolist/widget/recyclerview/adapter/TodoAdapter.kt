package com.example.todolist.widget.recyclerview.adapter

import android.os.IBinder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.databinding.ItemTodoInputBinding
import com.example.todolist.model.data.Goal
import com.example.todolist.model.data.Todo
import com.example.todolist.widget.recyclerview.viewmodel.TodoViewModel

class TodoAdapter(
    private val todoViewModel: TodoViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = arrayListOf<Todo>()

    var onLongClickTodo: ((Todo) -> Boolean)? = null
    var onKeyDone: ((IBinder) -> Unit)? = null

    inner class TextViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Todo) {
            binding.checkBox.apply {
                isChecked = data.isCompleted
                text = data.todo
                setOnLongClickListener { onLongClickTodo?.invoke(data)!! }
                setOnCheckedChangeListener { _, _ ->
                    todoViewModel.updateTodo(data.apply { isCompleted = true })
                }
            }
        }
    }

    inner class InputInsertViewHolder(binding: ItemTodoInputBinding)
        : InputViewHolder(binding, INPUT_INSERT) {
        fun bind(todo: Todo) {
            setEditTextEvent(todo)
        }
    }

    inner class InputUpdateViewHolder(binding: ItemTodoInputBinding)
        : InputViewHolder(binding, INPUT_UPDATE) {
        fun bind(todo: Todo) {
            setEditTextEvent(todo)
        }
    }

    abstract inner class InputViewHolder(
        private val binding: ItemTodoInputBinding,
        private val inputType: Int
    ) : RecyclerView.ViewHolder(binding.root) {
        protected fun setEditTextEvent(todo: Todo) {
            binding.etTodo.apply {
                requestFocus()
                setOnEditorActionListener { _, actionId, _ ->
                    return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onKeyDone?.invoke(windowToken)

                        todo.apply {
                            this.todo = text.toString()
                            this.type = OUTPUT
                            queryByInputType(todo)
                        }
                        true
                    } else false
                }
            }
        }

        private fun queryByInputType(todo: Todo) {
            when (inputType) {
                INPUT_INSERT -> todoViewModel.insertTodo(todo)
                INPUT_UPDATE -> {
                    todoViewModel.updateTodo(todo)
                    updateTodo(todo.apply { type = OUTPUT })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            INPUT_INSERT -> InputInsertViewHolder(
                ItemTodoInputBinding.inflate(LayoutInflater.from(parent.context), null, false)
                    .apply { setParentViewGroup(this) }
            )
            INPUT_UPDATE -> InputUpdateViewHolder(
                ItemTodoInputBinding.inflate(LayoutInflater.from(parent.context), null, false)
                    .apply { setParentViewGroup(this) }
            )
            else -> TextViewHolder(
                ItemTodoBinding.inflate(LayoutInflater.from(parent.context), null, false)
                    .apply { setParentViewGroup(this) }
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bind(list[position])
            is InputInsertViewHolder -> holder.bind(list[position])
            is InputUpdateViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].type

    fun setList(list: List<Todo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addTodo(goal: Goal, date: String) {
        val todo = Todo(goalId = goal.id!!, date = date).apply { type = INPUT_INSERT }
        list.add(todo)
        notifyItemInserted(list.lastIndex)
    }

    fun updateTodo(todo: Todo) {
        notifyItemChanged(list.indexOf(todo))
    }

    fun deleteTodo(todo: Todo) {
        val position = list.indexOf(todo.apply { isRepeat = false })
        list.removeAt(position)
        notifyItemRangeChanged(position, list.lastIndex)
    }

    private fun <B : ViewBinding> setParentViewGroup(binding: B) {
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val OUTPUT = 0
        private const val INPUT_INSERT = 1
        const val INPUT_UPDATE = 2
    }
}