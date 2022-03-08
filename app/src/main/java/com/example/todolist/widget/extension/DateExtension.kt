package com.example.todolist.widget.extension

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT = "yyyyMMdd"
private val calendar = Calendar.getInstance()

fun getTodayString(): String = calendar.time.formatToString()

fun Int.getCalendar(): Calendar =
    calendar.apply { this.set(Calendar.DATE, this@getCalendar) }

fun Int.getThisWeekDateString(): String =
    with(calendar) {
        this.set(Calendar.DAY_OF_WEEK, this@getThisWeekDateString)
        time.formatToString()
    }

fun Date.formatToString(): String = SimpleDateFormat(FORMAT).format(this)

fun String.formatToDate(): Date = SimpleDateFormat(FORMAT).parse(this)

fun Date.sum(num: Int): Date {
    Calendar.getInstance().apply {
        time = this@sum
        add(Calendar.DATE, num)
        return time
    }
}