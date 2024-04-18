package com.example.cs4520_twitter.data_layer

import android.content.Context
import androidx.work.WorkManager

object WorkManagerSingleton {
    private var workManager: WorkManager? = null

    fun getInstance(context: Context): WorkManager {
        return workManager ?: synchronized(this) {
            WorkManager.getInstance(context).also {
                workManager = it
            }
        }
    }
}