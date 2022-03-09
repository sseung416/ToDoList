package com.example.todolist.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemWeeklyBinding
import com.example.todolist.model.data.TodoDate
import com.example.todolist.model.data.Weekly
import com.example.todolist.widget.extension.getCalendar
import com.example.todolist.widget.recyclerview.viewmodel.WeeklyViewModel
import java.util.*

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {
    private val weeklyViewModel = WeeklyViewModel()

    var onClickDay: ((Calendar) -> Unit)? = null
    private var selectedPosition = 0

    val hasTodoDateQueue = arrayListOf<TodoDate>()

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

                        onClickDay?.invoke(weeklyViewModel.getTodayDate(position).toInt().getCalendar())
                        executeBindings(list[position])
                    }
                }

                isSelected = false
            }

            executeBindings(list[position])
        }

        private fun executeBindings(item: Weekly) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWeeklyBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 7

    init {
        weeklyViewModel.apply {
            for (i in (0..7)) {
                list.add(Weekly(getTodayDate(i), getToday(i)))
            }
        }
    }
}