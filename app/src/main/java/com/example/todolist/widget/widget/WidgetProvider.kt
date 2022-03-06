package com.example.todolist.widget.widget

import android.app.PendingIntent
import android.app.PendingIntent.*
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import com.example.todolist.R
import com.example.todolist.model.dao.TodoDao
import com.example.todolist.view.activity.HomeActivity
import com.example.todolist.widget.extension.getTodayString
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class WidgetProvider : AppWidgetProvider() {
    @Inject
    lateinit var todoDao: TodoDao
    private val disposable = CompositeDisposable()

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        Log.e(TAG, "onUpdate")

        RemoteViews(context!!.packageName, R.layout.widget_todo_simple).apply {
            updateWidgetListView(context, this)
            setRemoteAdapter(
                R.id.listView_widget,
                Intent(context, WidgetAdapterService::class.java)
            )
            setOnClickPendingIntent(
                R.id.btn_full_widget,
                getPendingIntent(
                    context,
                    Intent(context, HomeActivity::class.java),
                    PENDING_ACTIVITY
                )
            )
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
                    Log.e(TAG, "onReceive: onClickFullBtn")
                    context.startActivity(intent)
                }
            }
            appwidgetManager.updateAppWidget(componentName, widget)
        }
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        disposable.clear()
    }

    private fun getPendingIntent(context: Context, intent: Intent, type: Int): PendingIntent? =
        when (type) {
            PENDING_SERVICE ->
                getService(context, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            PENDING_ACTIVITY ->
                getActivity(context, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            PENDING_BROADCAST ->
                getBroadcast(context, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            else -> null
        }

    private fun updateWidgetListView(
        context: Context,
        widget: RemoteViews
    ) {
        todoDao.getTodosByDate(getTodayString()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                val intent = Intent(context, WidgetAdapterService::class.java).apply {
                    putExtra("todos", Gson().toJson(it))
                    putExtra("int", 1)
                    data = Uri.parse(this.toUri(Intent.URI_INTENT_SCHEME))
                }

                widget.setRemoteAdapter(R.id.listView_widget, intent)

                val appWidgetManager = AppWidgetManager.getInstance(context)
                val appWidgetIds = appWidgetManager?.getAppWidgetIds(ComponentName(context, this::class.java))
                appWidgetManager?.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listView_widget)
                appWidgetManager?.updateAppWidget(appWidgetIds, widget)
            }, {
                Log.e(TAG, "getTodosByDate: ${it.message}")
            }).apply { disposable.add(this) }
    }

    companion object {
        private const val TAG = "WidgetProvider"
        private const val PENDING_ACTION = "pendingAction"
        private const val ACTION_ON_CLICK_FULL_BUTTON = 2

        private const val PENDING_SERVICE = 1
        private const val PENDING_ACTIVITY = 2
        private const val PENDING_BROADCAST = 3
    }
}