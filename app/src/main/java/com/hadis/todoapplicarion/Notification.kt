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
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY , (todo.time).split(":")[0].toInt())
        calender.set(Calendar.MINUTE , (todo.time).split(":")[1].toInt())
        calender.set(Calendar.SECOND , 0)
        calender.set(Calendar.MILLISECOND , 0)
        calender.set(Calendar.YEAR , (todo.time).split("/")[2].toInt())
        calender.set(Calendar.MONDAY , (todo.time).split("/")[1].toInt()-1)
        calender.set(Calendar.DAY_OF_MONTH , (todo.time).split("/")[0].toInt())

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