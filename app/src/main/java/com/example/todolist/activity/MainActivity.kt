package com.example.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.todolist.R
import com.example.todolist.fragment.AddTaskFragmentDialog
import com.example.todolist.fragment.TodoListFragment
import com.example.todolist.viewmodel.AddTaskViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var addTaskBtn: ImageButton
    private lateinit var dateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTaskBtn = findViewById(R.id.ib_add_main)
        dateTextView = findViewById(R.id.tv_date_main)

        dateTextView.text = getDate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, TodoListFragment())
            .commit()

        addTaskBtn.setOnClickListener {
            AddTaskFragmentDialog().show(supportFragmentManager, "addTask")
        }
    }

    private fun getDate(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)

        return dateFormat.format(date)
    }
}