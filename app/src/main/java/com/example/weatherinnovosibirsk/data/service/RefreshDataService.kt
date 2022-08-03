package com.example.weatherinnovosibirsk.data.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.weatherinnovosibirsk.data.database.AppDatabase
import com.example.weatherinnovosibirsk.data.mapper.WeatherMapper
import com.example.weatherinnovosibirsk.data.network.APIFactory
import kotlinx.coroutines.*

class RefreshDataService : Service() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, RefreshDataService::class.java)
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val weatherInfoDao = AppDatabase.getInstance(this).weatherInfoDao()
    private val apiService = APIFactory.apiService

    private val mapper = WeatherMapper()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        coroutineScope.launch {
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
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}