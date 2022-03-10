package com.example.todolist.widget

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
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
    view.setColorFilter(getColorBySelected(isSelected).getColor(view.context))
}

@BindingAdapter("setTextColor")
fun setTextColor(view: TextView, isSelected: Boolean) {
    view.setTextColor(getColorBySelected(isSelected).getColor(view.context))
}

@BindingAdapter("setCardBackgroundColor")
fun setCardBackgroundColor(view: CardView, isSelected: Boolean) {
    view.setCardBackgroundColor(getColorBySelected(isSelected).getColor(view.context))
}

private fun getColorBySelected(isSelected: Boolean) =
    if (isSelected) R.color.black else R.color.grey2

@BindingAdapter("setVisible")
fun setVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) VISIBLE else INVISIBLE
}