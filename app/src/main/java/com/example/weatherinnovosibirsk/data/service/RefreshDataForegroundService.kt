package com.example.weatherinnovosibirsk.data.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.weatherinnovosibirsk.R
import com.example.weatherinnovosibirsk.data.database.AppDatabase
import com.example.weatherinnovosibirsk.data.mapper.WeatherMapper
import com.example.weatherinnovosibirsk.data.network.APIFactory
import kotlinx.coroutines.*

class RefreshDataForegroundService : Service() {

    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_TITLE = "Обновление данных"
        private const val NOTIFICATION_TEXT = "Получение актуальных данных о погоде"

        fun newIntent(context: Context): Intent {
            return Intent(context, RefreshDataForegroundService::class.java)
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val weatherInfoDao = AppDatabase.getInstance(this).weatherInfoDao()
    private val apiService = APIFactory.apiService

    private val mapper = WeatherMapper()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

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

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(NOTIFICATION_TEXT)
        .setSmallIcon(R.drawable.weather_icon_02d)
        .build()
}