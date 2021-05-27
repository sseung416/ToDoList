package com.example.todolist.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.todolist.R

class GridViewAdapter(private var context: Context, private var colorList: ArrayList<Int>) : BaseAdapter() {
    var colorTemp: Int = 0

    override fun getCount(): Int = colorList.size

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.gv_item_color_picker, null)

        val ib_color: Button = view.findViewById(R.id.btn_color)
        ib_color.setBackgroundColor(ContextCompat.getColor(context, colorList[position]))

        view.setOnClickListener {
            Log.d("아잉", position.toString() + " clicked")
            colorTemp = position
//            colorTemp = when(position) {
//                0 -> R.color.red
//                1 -> R.color.pink
//                2 -> R.color.yellow
//                3 -> R.color.green
//                4 -> R.color.blue
//                5 -> R.color.grey
//                else -> R.color.white
//            }
        }

        return view
    }
}