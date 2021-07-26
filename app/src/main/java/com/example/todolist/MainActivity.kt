package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.fragment.CalendarFragmentDialog
import com.example.todolist.fragment.TodoListFragment
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var addTaskBtn: ImageButton
    private lateinit var calendarBtn: ImageButton
    private lateinit var dateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTaskBtn = findViewById(R.id.ib_add_main)
        calendarBtn = findViewById(R.id.ib_calendar_main)
        dateTextView = findViewById(R.id.tv_date_main)

        dateTextView.text = getDate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, TodoListFragment())
            .commit()

        addTaskBtn.setOnClickListener {
            AddTaskFragmentDialog().show(supportFragmentManager, "addTask")
        }
        calendarBtn.setOnClickListener {
            CalendarFragmentDialog().show(supportFragmentManager, "calendar")
        }
    }

    private fun getDate(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)

        return dateFormat.format(date)
    }
}