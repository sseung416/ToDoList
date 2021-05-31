package com.example.todolist.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.database.data.Task
import com.example.todolist.database.TaskDatabase
import com.example.todolist.viewmodel.ToDoListViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class TodoListFragment : Fragment() {

    private var taskList = mutableListOf<Task>()

    private lateinit var taskDb: TaskDatabase

    private val viewModel: ToDoListViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_list, container, false)
        taskDb = TaskDatabase.getInstance(rootView.context)!!
        getTaskData()

        val recyclerView: RecyclerView = rootView.findViewById(R.id.rv_todolist)
        val adapter = ToDoListAdapter(taskList)
        val layout = GridLayoutManager(context, 2)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)




        return rootView
    }

    fun getTaskData() {
        Thread {
            var savedTask = taskDb.getTaskDao().getAll()
            if(savedTask.isNotEmpty())
                taskList.addAll(savedTask)
//            for(i in 0 until taskList.size)
//                Log.d("웨않돼?", taskList[i].id.toString())
        }.start()

    }
}