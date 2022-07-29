package com.example.weatherinnovosibirsk.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherinnovosibirsk.data.repository.WeatherRepositoryImpl
import com.example.weatherinnovosibirsk.domain.GetWeatherInfoUseCase
import com.example.weatherinnovosibirsk.domain.LoadDataUseCase

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val repository = WeatherRepositoryImpl(application)

    private val getWeatherInfoUseCase = GetWeatherInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val weatherInfo = getWeatherInfoUseCase()

    init {
        loadDataUseCase()
    }
}