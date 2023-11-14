package com.hadis.todoapplicarion

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class Notification(var name : String, description: String, var context: Context, var id: String, var todo : Todo) {

    init {
        createChannel(name, description)
        startNotification()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun startNotification(){
        val calender = Calendar.getInstance().run {
            set(Calendar.HOUR_OF_DAY , (todo.time).split(":")[0].toInt())
            set(Calendar.MINUTE , (todo.time).split(":")[1].toInt())
            set(Calendar.SECOND , 0)
            set(Calendar.MILLISECOND , 0)
            set(Calendar.YEAR , (todo.date).split("/")[2].toInt())
            set(Calendar.MONDAY , (todo.date).split("/")[1].toInt()-1)
            set(Calendar.DAY_OF_MONTH , (todo.date).split("/")[0].toInt())
            this
        }

        val notificationIntent = Intent(context , NotificationReceiver::class.java)
        notificationIntent.putExtra("id" , id)

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP , calender.timeInMillis,
            PendingIntent.getBroadcast
                (context , id.toInt() , notificationIntent , PendingIntent.FLAG_IMMUTABLE))

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}