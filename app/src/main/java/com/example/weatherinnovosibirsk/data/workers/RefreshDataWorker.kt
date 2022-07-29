package com.example.weatherinnovosibirsk.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.weatherinnovosibirsk.data.database.AppDatabase
import com.example.weatherinnovosibirsk.data.mapper.WeatherMapper
import com.example.weatherinnovosibirsk.data.network.APIFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    private val weatherInfoDao = AppDatabase.getInstance(context).weatherInfoDao()
    private val apiService = APIFactory.apiService

    private val mapper = WeatherMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val weatherInfoDto = apiService.getWeatherInfo()
                val weatherInfoDbModel = mapper.mapDtoToDbModel(weatherInfoDto)
                weatherInfoDao.insertWeatherInfo(weatherInfoDbModel)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}