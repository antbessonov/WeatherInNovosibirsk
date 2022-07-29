package com.example.weatherinnovosibirsk.domain

class GetWeatherInfoUseCase(
    private val repository: WeatherRepository
) {

    operator fun invoke() = repository.getWeatherInfo()
}