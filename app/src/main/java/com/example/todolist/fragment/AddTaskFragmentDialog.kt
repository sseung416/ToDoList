package com.example.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.databinding.FragmentAddTaskDialogBinding
import com.example.todolist.viewmodel.AddTaskViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentDialog : androidx.fragment.app.DialogFragment() {

    private val colors = listOf(
        (R.color.red),
        (R.color.pink),
        (R.color.yellow),
        (R.color.green),
        (R.color.blue),
        (R.color.grey),
    )

    init {
        val dialog = AddTaskFragmentDialog()
        dialog.isCancelable = false;
    }

    private val viewModel: AddTaskViewModel by viewModels()
    private lateinit var binding: FragmentAddTaskDialogBinding

    private lateinit var adapter: ColorPickerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task_dialog, container, false)
        binding.lifecycleOwner = this //Observable 대신 LiveData사용 ㄱㄴ
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRecyclerView() {
        adapter = ColorPickerAdapter(colors)
        binding.rvColorPickerAddTask.adapter = adapter
        binding.rvColorPickerAddTask.setHasFixedSize(true)
    }

    fun addTask() {

    }

    fun setDate() {
        var now: Long = 0
        lateinit var date: Date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
}