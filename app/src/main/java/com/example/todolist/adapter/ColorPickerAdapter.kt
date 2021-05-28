package com.example.todolist.adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class ColorPickerAdapter(private val color: List<Int>) :
    RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    var selectedPos: Int = -1



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorBtn: Button = itemView.findViewById(R.id.btn_color_colorPicker)
        val drawable: GradientDrawable = colorBtn.background as GradientDrawable //동적 색 설정

        fun binding(item: Int, position: Int) {
            drawable.setColor(ContextCompat.getColor(itemView.context, item))

            colorBtn.setOnClickListener {
                colorBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check_white_24,0)

                if(selectedPos != -1 && selectedPos != position) notifyItemChanged(selectedPos)
                selectedPos = position
            }

            colorBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_color_picker, parent, false)
        return ViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(color[position], position)
    }

    override fun getItemCount(): Int = color.size

    override fun getItemId(position: Int): Long = position.toLong()
}