package com.example.todolist.fragment

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.viewmodel.AddTaskViewModel

class TodoListFragment : Fragment() {
    private lateinit var application: Application

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var binding: FragmentTodoListBinding

    private lateinit var adapter: ToDoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewModel.getAllTask()
        observe()
    }

    private fun init() {
        application = requireActivity().application
        viewModel = ViewModelProvider(this, AddTaskViewModel.Factory(application))[AddTaskViewModel::class.java]
        adapter = ToDoListAdapter(application)
    }

    private fun initRecyclerView() {
        binding.rvTodolist.adapter = adapter
        binding.rvTodolist.setHasFixedSize(true)
    }

    private fun observe() {
        viewModel.tasks.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })
    }
}