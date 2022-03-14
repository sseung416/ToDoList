package com.example.todolist.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemWeeklyBinding
import com.example.todolist.model.data.Weekly
import com.example.todolist.widget.extension.formatToDate
import com.example.todolist.widget.extension.getCalendar
import java.text.SimpleDateFormat
import java.util.*

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {
    var onClickDay: ((Calendar) -> Unit)? = null
    private var selectedPosition = 0

    private val hasTodoDateQueue = arrayListOf<String>()
    private val list = arrayListOf<Weekly>()

    inner class ViewHolder(
        private val binding: ItemWeeklyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            list[position].apply {
                binding.btnDay.setOnClickListener {
                    if (selectedPosition != position) {
                        isSelected = true
                        notifyItemChanged(selectedPosition)
                        selectedPosition = position

                        onClickDay?.invoke(getTodayDate(position).toInt().getCalendar())
                        executeBindings(list[position])
                    }
                }

                isSelected = false

                if (hasTodo(date)) {
                    hasTodo = true
                    hasTodoDateQueue.removeAt(0)
                }
            }

            executeBindings(list[position])
        }

        private fun executeBindings(item: Weekly) {
            binding.data = item
            binding.executePendingBindings()
        }

        private fun hasTodo(date: String) =
            hasTodoDateQueue.isNotEmpty() && date == SimpleDateFormat("d").format(hasTodoDateQueue[0].formatToDate())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWeeklyBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 7

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

    private fun getTodayDate(position: Int): String {
        val today = Calendar.getInstance()
        var dayNum = position + 2

        if (dayNum == 8) {
            today.add(Calendar.DAY_OF_WEEK, 7)
            dayNum = 1
        }

        today.set(Calendar.DAY_OF_WEEK, dayNum)

        return SimpleDateFormat("d").format(today.time)
    }

    fun hasTodoDateQueueAddAll(list: List<String>) {
        hasTodoDateQueue.addAll(list)
        notifyDataSetChanged()
    }

    init {
        for (i in (0..7)) {
            list.add(Weekly(getTodayDate(i), getToday(i)))
        }
    }
}