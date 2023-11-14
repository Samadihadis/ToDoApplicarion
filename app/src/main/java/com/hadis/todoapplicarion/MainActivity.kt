package com.hadis.todoapplicarion

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var postNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!Manifest.permission.POST_NOTIFICATIONS.isPermissionGranted(this))
                postNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun String.isPermissionGranted(context: Context) =
        ContextCompat.checkSelfPermission(
            context, this
        ) == PackageManager.PERMISSION_GRANTED

}