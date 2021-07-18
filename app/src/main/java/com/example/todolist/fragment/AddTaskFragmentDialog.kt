package com.example.todolist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.databinding.FragmentAddTaskDialogBinding
import com.example.todolist.viewmodel.AddTaskViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentDialog : DialogFragment() {

    init {
        dialog!!.setCancelable(false);
    }

    private val adapter = ColorPickerAdapter()
    private val viewModel: AddTaskViewModel by viewModels()
    private lateinit var binding: FragmentAddTaskDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observe()

        /* 시간이 있다면 ViewModel에서 SingleLiveEvent 사용해보기 */
        binding.ibFinishAddTask.setOnClickListener {
            viewModel.addTask(binding.etContentAddTask.toString(), adapter.selectedPos)
            viewModel.onFinishBtnClick()
            dialog!!.dismiss()
        }
    }

    private fun initRecyclerView() {
        binding.rvColorPickerAddTask.adapter = adapter
        binding.rvColorPickerAddTask.setHasFixedSize(true)
    }

    private fun observe() {
    }
}