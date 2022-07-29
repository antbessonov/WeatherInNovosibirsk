package com.example.weatherinnovosibirsk.domain

data class WeatherInfo(
    val dt: Int?,
    val name: String?,
    val speed: Int?,
    val deg: Int?,
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?,
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?,
)