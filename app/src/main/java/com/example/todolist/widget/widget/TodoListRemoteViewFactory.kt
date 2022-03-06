package com.example.todolist.widget.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.todolist.R
import com.example.todolist.model.data.Todo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoListRemoteViewFactory(
    private val context: Context,
    intent: Intent?
) : RemoteViewsService.RemoteViewsFactory {
    private val list = with(object : TypeToken<List<Todo>>() {}.type) {
        Gson().fromJson<List<Todo>>(intent!!.getStringExtra("todos"), this)
    } ?: arrayListOf()

    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = list.size

    override fun getViewAt(position: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.item_widget_todo).apply {
            setTextViewText(R.id.tv_todo, list[position].todo)
        }
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false
}