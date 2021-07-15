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

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: ToDoListAdapter

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_list, container, false)

        initRecyclerView(rootView)

        viewModel.getAll().observe(viewLifecycleOwner, { //DB값이 변경되면 todolist 추가(= UI 변경)
            //뷰모델에서 db값 받아서 taskList에 추가
//            viewModel.

        })

        return rootView
    }

    private fun initRecyclerView(v: View) {
        recyclerView = v.findViewById(R.id.rv_todolist)
        rvAdapter = ToDoListAdapter(listOf(), viewModel) //값 전달(db)
        recyclerView.adapter = rvAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun addTodoList() {

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