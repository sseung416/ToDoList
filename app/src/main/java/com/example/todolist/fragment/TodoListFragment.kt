package com.example.todolist.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.data.ToDoListData


class TodoListFragment : Fragment() {

    private val testItem = arrayListOf(
        ToDoListData(R.color.red, "잠자기"),
        ToDoListData(R.color.grey, "늦게 잠자기"),
        ToDoListData(R.color.blue, "밥 먹고 잠자기")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_todo_list, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.rv_todolist)

        val adapter = ToDoListAdapter(testItem, rootView.context)
        recyclerView.adapter = adapter

        val layout = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layout

        return rootView
    }

}