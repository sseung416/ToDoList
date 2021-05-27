package com.example.todolist.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class ColorPickerAdapter(private val context: Context, private val color: List<Int>) :
    RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>() {
    var selectedPos: Int = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isSelected = mutableListOf<Boolean>(false, false, false, false, false, false)

        val colorBtn: Button = itemView.findViewById(R.id.btn_color_colorPicker)
        var drawable: GradientDrawable = colorBtn.background as GradientDrawable //동적 색 설정



        fun binding(item: Int, position: Int) {
            //색 설정
//            drawable.setColor(item)
            colorBtn.setBackgroundColor(item)

            colorBtn.setOnClickListener {
                if(selectedPos != -1)
                    isSelected[selectedPos] = false
                isSelected[position] = true
                selectedPos = position

            }

            if(isSelected.get(position)) {
                colorBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_white_24, 0, 0,0)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflated = LayoutInflater.from(context).inflate(R.layout.rv_item_color_picker, parent, false)
        return ViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(color.get(position), position)
    }

    override fun getItemCount(): Int = color.size
}