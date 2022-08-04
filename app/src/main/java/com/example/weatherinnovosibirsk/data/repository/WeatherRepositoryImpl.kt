package com.example.weatherinnovosibirsk.data.repository

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weatherinnovosibirsk.data.database.AppDatabase
import com.example.weatherinnovosibirsk.data.mapper.WeatherMapper
import com.example.weatherinnovosibirsk.data.service.RefreshDataForegroundService
import com.example.weatherinnovosibirsk.domain.WeatherInfo
import com.example.weatherinnovosibirsk.domain.WeatherRepository

class WeatherRepositoryImpl(
    private val application: Application,
) : WeatherRepository {

    private val weatherInfoDao = AppDatabase.getInstance(application).weatherInfoDao()
    private val mapper = WeatherMapper()

    override fun getWeatherInfo(): LiveData<WeatherInfo> {
        return Transformations.map(weatherInfoDao.getWeatherInfo()) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        ContextCompat.startForegroundService(
            application,
            RefreshDataForegroundService.newIntent(application)
        )
    }
}