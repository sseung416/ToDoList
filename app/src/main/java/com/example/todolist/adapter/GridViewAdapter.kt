package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.todolist.R

class GridViewAdapter(private var context: Context, private var colorList: ArrayList<Int>) : BaseAdapter() {
    override fun getCount(): Int = colorList.size

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.gv_item_color_picker, null)

        val ib_color: Button = view.findViewById(R.id.btn_color)
        //db 값 받아서 바꿔주기
        ib_color.setBackgroundColor(ContextCompat.getColor(context, colorList[position]))

        return view
    }
}