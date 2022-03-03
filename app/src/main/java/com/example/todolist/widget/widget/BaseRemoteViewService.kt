package com.example.todolist.widget.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.todolist.R
import com.example.todolist.model.data.Todo

class BaseRemoteViewService(private val context: Context) : RemoteViewsService.RemoteViewsFactory {
    private val list = arrayListOf<Todo>(
        Todo(goalId = 1, todo = "테스트1", date = ""),
        Todo(goalId = 1, todo = "테스트2", date = "")
    )

    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = list.size

    override fun getViewAt(position: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.item_widget_todo).apply {
            this.setTextViewText(R.id.tv_todo, list[position].todo)
        }
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false
}