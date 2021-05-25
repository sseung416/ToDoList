package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.fragment.TodoListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTaskBtn: ImageView = findViewById(R.id.ib_add_main)
        val calendarBtn: ImageView = findViewById(R.id.ib_calendar_main)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.fragmentContainerView))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, TodoListFragment())
            .commit()

        addTaskBtn.setOnClickListener {
            AddTaskFragmentDialog().show(supportFragmentManager, "test")
        }
    }
}