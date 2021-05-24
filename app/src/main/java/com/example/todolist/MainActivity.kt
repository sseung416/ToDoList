package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.todolist.fragment.TodoListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.fragmentContainerView))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, TodoListFragment())
            .commit()
    }

}