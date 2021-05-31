package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import com.example.todolist.database.TaskDatabase
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

        var now: Long = 0
        var date = Date(now)
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)
        dateTextView.text = dateFormat.format(date)




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
}