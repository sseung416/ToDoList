package com.example.todolist.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.viewmodel.AddTaskViewModel
import com.example.todolist.viewmodel.ToDoListViewModel


class TodoListFragment : Fragment() {

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var binding: FragmentTodoListBinding

    private var adapter = ToDoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewModel.getAllTask()
        observe()
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