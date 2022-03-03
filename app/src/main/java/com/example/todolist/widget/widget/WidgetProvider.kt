package com.example.todolist.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.todolist.R

class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val serviceIntent = Intent(context, RemoteViewService::class.java)
        val widget = RemoteViews(context!!.packageName, R.layout.widget_todo_simple)
        widget.setRemoteAdapter(R.id.listView_widget, serviceIntent)

        appWidgetManager!!.updateAppWidget(appWidgetIds, widget)
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }
}