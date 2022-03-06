package com.example.todolist.widget.widget

import android.content.Intent
import android.widget.RemoteViewsService

class WidgetAdapterService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory =
        TodoListRemoteViewFactory(applicationContext, intent)
}