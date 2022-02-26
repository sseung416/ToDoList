package com.example.todolist.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemWeeklyBinding
import com.example.todolist.widget.extension.getColor
import java.text.SimpleDateFormat
import java.util.*

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {
    var onClickDay: ((Calendar)->Unit)? = null

    private var selectedPosition = 0

    inner class ViewHolder(
        private val binding: ItemWeeklyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvDay.text = getToday(position)
            binding.tvDate.text = getTodayDate(position).toString()

            binding.btnDay.setOnClickListener {
                if (selectedPosition != position) {
                    setViewColor(R.color.black)
                    notifyItemChanged(selectedPosition)
                    selectedPosition = position
                }
                onClickDay?.invoke(getCalendar(getTodayDate(position)))
            }

            setViewColor(R.color.grey2)
        }

        private fun getCalendar(date: Int): Calendar =
            Calendar.getInstance().apply { this.set(Calendar.DATE, date) }

        private fun getToday(position: Int): String =
            when (position + 2) {
                Calendar.MONDAY -> "월"
                Calendar.TUESDAY -> "화"
                Calendar.WEDNESDAY -> "수"
                Calendar.THURSDAY -> "목"
                Calendar.FRIDAY -> "금"
                Calendar.SATURDAY -> "토"
                else -> "일"
            }

        private fun getTodayDate(position: Int): Int {
            val today = Calendar.getInstance()
            var dayNum = position + 2

            if (dayNum == 8) {
                today.add(Calendar.DAY_OF_WEEK, 7)
                dayNum = 1
            }

            today.set(Calendar.DAY_OF_WEEK, dayNum)
            return SimpleDateFormat("d").format(today.time).toInt()
        }

        private fun setViewColor(color: Int) {
            binding.tvDay.apply { setTextColor(color.getColor(context)) }
            binding.tvDate.apply { setTextColor(color.getColor(context)) }
            binding.btnDay.apply { setColorFilter(color.getColor(context)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWeeklyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 7
}