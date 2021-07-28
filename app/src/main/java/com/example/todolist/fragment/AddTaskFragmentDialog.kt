package com.example.todolist.fragment

import android.app.Application
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.databinding.FragmentAddTaskDialogBinding
import com.example.todolist.viewmodel.AddTaskViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentDialog : DialogFragment() {

    private lateinit var application: Application

    private lateinit var adapter: ColorPickerAdapter
    private lateinit var toDoListAdapter: ToDoListAdapter
    private lateinit var viewModel: AddTaskViewModel
    private lateinit var binding: FragmentAddTaskDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.tvDateAddTask.text = viewModel.getDate()
    }

    private fun init() {
        application = requireActivity().application
        viewModel = ViewModelProvider(this, AddTaskViewModel.Factory(application))[AddTaskViewModel::class.java]
        adapter = ColorPickerAdapter()
        toDoListAdapter = ToDoListAdapter(application)

        binding.fragment = this

        requireDialog().setCancelable(false);
    }

    private fun initRecyclerView() {
        binding.rvColorPickerAddTask.adapter = adapter
        binding.rvColorPickerAddTask.setHasFixedSize(true)
    }

    fun onClick(view: View) {
        when(view.id) {
            binding.ibFinishAddTask.id -> {
                viewModel.addTask(binding.etContentAddTask.text.toString(), adapter.selectedPos)
                viewModel.insert()
                requireDialog().dismiss()
            }
            binding.ibBackAddTask.id -> requireDialog().dismiss()
            else -> Log.d("NONE", "onClick Event 없음")
        }
    }


}