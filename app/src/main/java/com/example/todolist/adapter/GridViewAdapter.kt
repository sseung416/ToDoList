package com.example.todolist.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class GridViewAdapter(private var colorList: ArrayList<Int>) : BaseAdapter() {
    override fun getCount(): Int = colorList.size

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView!!
    }
}