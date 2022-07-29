package com.example.weatherinnovosibirsk.domain

import androidx.lifecycle.LiveData

interface WeatherRepository {

    fun getWeatherInfo(): LiveData<WeatherInfo>

    fun loadData()
}