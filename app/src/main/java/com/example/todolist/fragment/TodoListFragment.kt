package com.example.todolist.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.database.TaskDatabase
import com.example.todolist.databinding.FragmentAddTaskDialogBinding
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.viewmodel.ToDoListViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*


class TodoListFragment : Fragment() {

    private var taskList = mutableListOf<Task>()

    private val viewModel: ToDoListViewModel by viewModels()
    private lateinit var binding: FragmentTodoListBinding

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)

        val rootView = binding.root

        initRecyclerView()

        viewModel.getAll().observe(viewLifecycleOwner, {

        })

        return rootView
    }

    private fun initRecyclerView() {
        binding.rvTodolist.adapter = ToDoListAdapter(listOf()) //값 전달
        binding.rvTodolist.setHasFixedSize(true)
    }

//    fun getTaskData() {
//        Thread {
//            var savedTask = taskDb.getTaskDao().getAll()
//            if(savedTask.isNotEmpty())
//                taskList.addAll(savedTask)
////            for(i in 0 until taskList.size)
////                Log.d("웨않돼?", taskList[i].id.toString())
//        }.start()
//
//    }


}