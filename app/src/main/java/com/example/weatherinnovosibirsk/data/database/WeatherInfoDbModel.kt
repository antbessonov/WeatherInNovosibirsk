package com.example.weatherinnovosibirsk.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherInfoDbModel (
    @PrimaryKey
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