package com.example.todolist.widget.widget

import android.app.PendingIntent
import android.app.PendingIntent.*
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.example.todolist.R
import com.example.todolist.view.activity.HomeActivity

class WidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.e(TAG, "onEnabled: ", )
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.e(TAG, "onUpdate: ", )
        
        RemoteViews(context!!.packageName, R.layout.widget_todo_simple).apply {
            setRemoteAdapter(R.id.listView_widget, Intent(context, RemoteViewService::class.java))
            setOnClickPendingIntent(
                R.id.btn_full_widget,
                getPendingIntent(context, Intent(context, HomeActivity::class.java))
            )

            appWidgetManager!!.updateAppWidget(appWidgetIds, this)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context!!, intent)

        val widget = RemoteViews(context.packageName, R.layout.widget_todo_simple)
        val componentName = ComponentName(context, WidgetProvider::class.java)
        val appwidgetManager = AppWidgetManager.getInstance(context)

        if (intent?.action == PENDING_ACTION) {
            when (intent.getIntExtra("id", -1)) {
                ACTION_ON_CLICK_FULL_BUTTON -> {
                    Log.e(TAG, "onReceive: onClickFullBtn", )
                    context.startActivity(intent)
                }
            }
            appwidgetManager.updateAppWidget(componentName, widget)
        }
    }

    private fun getPendingIntent(context: Context, id: Int, flag: Int?): PendingIntent {
        val intent = Intent(context, WidgetProvider::class.java).apply {
            if (flag != null) flags = flag
            action = PENDING_ACTION
            putExtra("id", id)
        }
        return getBroadcast(context, id, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
    }

    private fun getPendingIntent(context: Context, intent: Intent): PendingIntent =
        getActivity(context, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

    companion object {
        private const val TAG = "WidgetProvider"
        private const val PENDING_ACTION = "pendingAction"
        private const val ACTION_ON_CLICK_FULL_BUTTON = 2
    }
}