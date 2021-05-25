package com.example.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.adapter.GridViewAdapter

class AddTaskFragmentDialog : DialogFragment() {

    val colors = arrayListOf(
        (R.color.red),
        (R.color.pink),
        (R.color.yellow),
        (R.color.green),
        (R.color.blue),
        (R.color.grey),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_task_dialog, container, false)

        val gridView: GridView = rootView.findViewById(R.id.gv_colorPicker_addTask)
        val adapter = GridViewAdapter(rootView.context ,colors)

        gridView.adapter = adapter

        return rootView
    }

}