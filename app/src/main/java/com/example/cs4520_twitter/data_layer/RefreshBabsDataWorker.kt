package com.example.cs4520_twitter.data_layer

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cs4520_twitter.data_layer.api.Api
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.repositories.BabRepo

class RefreshBabsDataWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val remoteSource = Api.babsApiService
            val localDB = AppDatabase.instance(appContext)
            val repo = BabRepo(localDB, remoteSource)
            val result = repo.getRandomBabs()
            Log.d("Refresh Worker", "Received resp: ${result.babs}")
            Result.success()
        } catch (e: Exception) {
            Log.d("RefreshWorker", "Errored out: $e")
            Result.retry()
        }
    }
}