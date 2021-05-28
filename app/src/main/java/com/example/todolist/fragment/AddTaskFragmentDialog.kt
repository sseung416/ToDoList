package com.example.todolist.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ColorPickerAdapter
import com.example.todolist.data.Task
import com.example.todolist.database.TaskDatabase
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

    private lateinit var backBtn: ImageButton
    private lateinit var checkBtn: ImageButton
    private lateinit var contentEditText: EditText

    private lateinit var taskDb: TaskDatabase

    var now: Long = 0
    lateinit var date: Date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.setCancelable(false);

        val rootView = inflater.inflate(R.layout.fragment_add_task_dialog, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.lv_colorPicker_addTask)
        val adapter = ColorPickerAdapter(colors)

        recyclerView.adapter = adapter
        val layout = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)

        backBtn = rootView.findViewById(R.id.ib_back_addTask)
        checkBtn = rootView.findViewById(R.id.ib_finish_addTask)
        contentEditText = rootView.findViewById(R.id.et_task_addTask)

        taskDb = TaskDatabase.getInstance(rootView.context)!!

        val addRunnable = Runnable {
            val newTask = Task()

            newTask.color = adapter.selectedPos
            newTask.content = contentEditText.text.toString()
            newTask.date = dateFormat.format(date)

            taskDb.getTaskDao().insert(newTask)
        }

        /* onClick event */
        backBtn.setOnClickListener {
            dismiss()
        }
        checkBtn.setOnClickListener {
            now = System.currentTimeMillis()
            date = Date(now)

            val addThread = Thread(addRunnable)
            addThread.start()
            dismiss()
        }


        return rootView
    }


}