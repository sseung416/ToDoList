package com.example.todolist.widget.recyclerview.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemColorBinding
import com.example.todolist.widget.extension.getColor

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private val colors = listOf(
        R.color.red, R.color.orange, R.color.yellow, R.color.green1, R.color.green2,
        R.color.blue1, R.color.blue2, R.color.purple, R.color.pink, R.color.grey2
    )

    private var selectedPosition = 0

    inner class ViewHolder(
        private val binding: ItemColorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Int, position: Int) {
            binding.btnColor.apply {
                setColorFilter(color.getColor(context), PorterDuff.Mode.SRC_IN)
                setOnClickListener {
                    binding.ivChecked.visibility = VISIBLE
                    if (selectedPosition != position) {
                        notifyItemChanged(selectedPosition)
                        selectedPosition = position
                    }
                }
            }

            binding.ivChecked.visibility = INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemColorBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors[position], position)
    }

    override fun getItemCount(): Int = colors.size

    fun getSelectedColor(): Int = colors[selectedPosition]
}