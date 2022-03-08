package com.example.todolist.widget.recyclerview.viewmodel

import java.text.SimpleDateFormat
import java.util.*

class WeeklyViewModel {
    fun getToday(position: Int): String =
        when (position + 2) {
            Calendar.MONDAY -> "월"
            Calendar.TUESDAY -> "화"
            Calendar.WEDNESDAY -> "수"
            Calendar.THURSDAY -> "목"
            Calendar.FRIDAY -> "금"
            Calendar.SATURDAY -> "토"
            else -> "일"
        }

    fun getTodayDate(position: Int): String {
        val today = Calendar.getInstance()
        var dayNum = position + 2

        if (dayNum == 8) {
            today.add(Calendar.DAY_OF_WEEK, 7)
            dayNum = 1
        }

        today.set(Calendar.DAY_OF_WEEK, dayNum)

        return SimpleDateFormat("d").format(today.time)
    }
}