package com.example.todolist.widget.recyclerview.adapter

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemWeeklyBinding
import com.example.todolist.widget.extension.getColor
import java.text.SimpleDateFormat
import java.util.*

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {
    var onClickDay: ((Int, Int)->Unit)? = null

    private var selectedPosition = 0

    inner class ViewHolder(
        private val binding: ItemWeeklyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvDay.text = getToday(position)
            binding.tvDate.text = getTodayDate(position)

            binding.btnDay.setOnClickListener {
                if (selectedPosition != position) {
                    binding.tvDay.apply { setTextColor((R.color.black).getColor(context)) }
                    binding.tvDate.apply { setTextColor((R.color.black).getColor(context)) }
                    binding.btnDay.apply { setColorFilter((R.color.black).getColor(context)) }
                }
//                onClickDay?.invoke()
            }
        }

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
            val dayNum = if ((position+2) == 8) 1 else position+2

            today.set(Calendar.DAY_OF_WEEK, dayNum)
            return SimpleDateFormat("dd").format(today.time)
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