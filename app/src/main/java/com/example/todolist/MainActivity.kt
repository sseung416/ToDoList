package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.room.Database
import androidx.room.Room
import com.example.todolist.database.TaskDatabase
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.fragment.CalendarFragmentDialog
import com.example.todolist.fragment.TodoListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var addTaskBtn: ImageButton
    private lateinit var calendarBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.fragmentContainerView))

        addTaskBtn = findViewById(R.id.ib_add_main)
        calendarBtn = findViewById(R.id.ib_calendar_main)

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