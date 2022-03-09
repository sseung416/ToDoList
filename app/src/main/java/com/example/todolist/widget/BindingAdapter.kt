package com.example.todolist.widget

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.R
import com.example.todolist.widget.extension.formatToString
import com.example.todolist.widget.extension.getColor
import java.util.*

@BindingAdapter("dateText")
fun setDateText(view: View, calendar: Calendar) {
    if (view is TextView) {
        view.text = formatStringDate(calendar)
    }
}

private fun formatStringDate(calendar: Calendar): String {
    calendar.time.formatToString().apply {
        return "${substring(0, 4)}년 ${substring(5, 6)}월 ${substring(6)}일"
    }
}

@BindingAdapter("setTint")
fun setTint(view: ImageButton, isSelected: Boolean) {
    val color = if (isSelected) R.color.black else R.color.grey2
    view.setColorFilter(color.getColor(view.context))
}

@BindingAdapter("setTextColor")
fun setTextColor(view: TextView, isSelected: Boolean) {
    val color = if (isSelected) R.color.black else R.color.grey2
    view.setTextColor(color.getColor(view.context))
}


