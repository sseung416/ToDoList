package com.example.todolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.adapter.GridViewAdapter
import com.example.todolist.data.Task
import com.example.todolist.database.TaskDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentDialog : DialogFragment() {

    val colors = arrayListOf(
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

    private var taskList = listOf<Task>()

    var now: Long = 0
    lateinit var date: Date
    val dateFormat = SimpleDateFormat("yyyy-MM--dd")

    var colorTemp: Int = 0

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.setCancelable(false);

        val rootView = inflater.inflate(R.layout.fragment_add_task_dialog, container, false)

        val gridView: GridView = rootView.findViewById(R.id.gv_colorPicker_addTask)
        val adapter = GridViewAdapter(rootView.context ,colors)

        gridView.adapter = adapter

        backBtn = rootView.findViewById(R.id.ib_back_addTask)
        checkBtn = rootView.findViewById(R.id.ib_finish_addTask)
        contentEditText = rootView.findViewById(R.id.et_task_addTask)

        taskDb = TaskDatabase.getInstance(rootView.context)!!

        val addRunnable = Runnable {
            val newTask = Task()

            newTask.color = colorTemp
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

        gridView.setOnItemClickListener { parent, view, position, id ->
            colorTemp = when(position) {
                0 -> R.color.red
                1 -> R.color.pink
                2 -> R.color.yellow
                3 -> R.color.green
                4 -> R.color.blue
                5 -> R.color.grey
                else -> R.color.white
            }
        }

        return rootView
    }

}