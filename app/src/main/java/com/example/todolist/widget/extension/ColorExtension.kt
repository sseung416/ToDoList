package com.example.todolist.widget.extension

import android.content.Context
import androidx.core.content.ContextCompat

fun Int.getColor(context: Context): Int =
    ContextCompat.getColor(context, this)