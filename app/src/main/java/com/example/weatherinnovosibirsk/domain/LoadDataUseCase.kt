package com.example.weatherinnovosibirsk.domain

class LoadDataUseCase(
    private val repository: WeatherRepository
) {

    operator fun invoke() = repository.loadData()
}