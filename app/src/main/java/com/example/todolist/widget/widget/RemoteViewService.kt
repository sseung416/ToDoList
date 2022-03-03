package com.example.todolist.widget.widget

import android.content.Intent
import android.widget.RemoteViewsService

class RemoteViewService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory =
        BaseRemoteViewService(this.applicationContext)
}