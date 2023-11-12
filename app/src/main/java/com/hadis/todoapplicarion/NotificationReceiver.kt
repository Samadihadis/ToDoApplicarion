package com.hadis.todoapplicarion

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val pendingIntent = NavDeepLinkBuilder(context!!).setGraph(R.navigation.nav_graph)
            .setDestination(R.id.currentTodo).createPendingIntent()

        val currentId = intent!!.extras!!.getString("id")
        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        runBlocking {
            val currentTodo = context.dataStore.data.first().todoList.find {
                it.hashCode() == currentId!!.toInt()
            }
            createNotification(
                context,
                currentId.toString(),
                currentTodo!!.title,
                currentTodo.description,
                notificationManager,
                pendingIntent
            )
        }
    }

    private fun createNotification(
        context: Context?,
        id: String,
        title: String,
        description: String,
        notificationManager: NotificationManager,
        pendingIntent: PendingIntent
    ) {
        val notification = NotificationCompat.Builder(context!!, id)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active).setContentTitle(title)
            .setContentTitle(description).setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent).build()
        notificationManager.notify(id.toInt(), notification)
    }
}