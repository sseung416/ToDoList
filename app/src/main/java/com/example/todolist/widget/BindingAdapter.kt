package com.example.todolist.widget

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.widget.extension.formatToString
import java.util.*

@BindingAdapter("dateText")
fun setDateText(view: View, calendar: Calendar) {
    if (view is TextView) { view.text = formatStringDate(calendar) }
}

private fun formatStringDate(calendar: Calendar): String {
    calendar.time.formatToString().apply {
        return "${substring(0, 4)}년 ${substring(5, 6)}월 ${substring(6)}일"
    }
}
