package com.example.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.databinding.FragmentAddTaskDialogBinding
import com.example.todolist.viewmodel.AddTaskViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentDialog : DialogFragment() {

    val colors = listOf(
        (R.color.red),
        (R.color.pink),
        (R.color.yellow),
        (R.color.green),
        (R.color.blue),
        (R.color.grey),
    )

    init {
        dialog!!.setCancelable(false);
    }

    private val viewModel: AddTaskViewModel by viewModels()
    private lateinit var binding: FragmentAddTaskDialogBinding

    var now: Long = 0
    lateinit var date: Date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task_dialog, container, false)
        val rootView = binding.root

        initRecyclerView()

        viewModel.content.observe(viewLifecycleOwner, {
            binding.etContentAddTask.text = it.toString()
        })



//        val addRunnable = Runnable {
//            val newTask = Task()
//
//            newTask.color = adapter.selectedPos
//            newTask.content = contentEditText.text.toString()
//            newTask.date = dateFormat.format(date)
//
//            db.getTaskDao().insert(newTask)
//        }

//        backBtn.setOnClickListener {
//            dismiss()
//        }
//        checkBtn.setOnClickListener {
//            now = System.currentTimeMillis()
//            date = Date(now)
//
//            val addThread = Thread(addRunnable)
//            addThread.start()
//            dismiss()
//        }


        return rootView
    }

    private fun initRecyclerView() {
        binding.rvColorPickerAddTask.adapter = ColorPickerAdapter(colors)
        binding.rvColorPickerAddTask.setHasFixedSize(true)
    }

    fun addTask() {
        now = System.currentTimeMillis()
        date = Date(now)

        val newTask = Task()
        newTask.content = binding.etContentAddTask.text.toString()
        newTask.color
        newTask.date = date.toString()

    }
}