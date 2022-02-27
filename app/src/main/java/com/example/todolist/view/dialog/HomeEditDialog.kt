package com.example.todolist.view.dialog

import androidx.fragment.app.activityViewModels
import com.example.todolist.base.BaseDialog
import com.example.todolist.databinding.DialogHomeEditBinding
import com.example.todolist.model.data.Todo
import com.example.todolist.viewmodel.dialog.HomeEditViewModel
import com.example.todolist.widget.extension.formatToDate
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.extension.sum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeEditDialog(
    private val todo: Todo
) : BaseDialog<DialogHomeEditBinding, HomeEditViewModel>() {
    override val viewModel: HomeEditViewModel by activityViewModels()

    override fun init() {
        binding.btnEdit.setOnClickListener {
            // todo 어쨋든 수정하는 동작
            viewModel.updateTodo(todo)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteTodo(todo)
            dismiss()
        }

        binding.btnTomorrow.setOnClickListener {
            todo.date = getNextDate(todo.date)
            viewModel.updateTodo(todo)
        }

        binding.btnRepeat.setOnClickListener {
            todo.isRepeat = true
            viewModel.updateTodo(todo)
        }
    }

    override fun observerViewModel() {
        with (viewModel) {

        }
    }

    private fun getNextDate(date: String): String =  date.formatToDate().sum(1).formatToString()

    companion object { const val TAG = "HomeEditDialog" }
}