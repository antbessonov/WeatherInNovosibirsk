package com.example.weatherinnovosibirsk.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM weather ORDER BY dt DESC LIMIT 1")
    fun getWeatherInfo(): LiveData<WeatherInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfo: WeatherInfoDbModel)
}