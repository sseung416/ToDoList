package com.example.todolist.widget.extension

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT = "yyyyMMdd"

fun getTodayString(): String = Calendar.getInstance().time.formatToString()

fun Date.formatToString(): String = SimpleDateFormat(FORMAT).format(this)

fun String.formatToDate(): Date = SimpleDateFormat(FORMAT).parse(this)

fun Date.sum(num: Int): Date {
    Calendar.getInstance().apply {
        time = this@sum
        add(Calendar.DATE, num)
        return time
    }
}