package com.example.todolist.widget.adapter

import android.util.Log
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemWeeklyBinding
import java.text.SimpleDateFormat
import java.util.*

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {
    var onClickDay: ((Int, Int)->Unit)? = null

    inner class ViewHolder(
        private val binding: ItemWeeklyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val today = Calendar.getInstance()
            var dayNum = position + 2

            binding.tvDay.text = when (dayNum) {
                Calendar.MONDAY -> "월"
                Calendar.TUESDAY -> "화"
                Calendar.WEDNESDAY -> "수"
                Calendar.THURSDAY -> "목"
                Calendar.FRIDAY -> "금"
                Calendar.SATURDAY -> "토"
                else -> "일"
            }

            if (dayNum == 8) dayNum = 1

            today.set(Calendar.DAY_OF_WEEK, dayNum)
            binding.tvDate.text = SimpleDateFormat("dd").format(today.time)

            binding.btnDay.setOnClickListener {
//                onClickDay?.invoke()
            }
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