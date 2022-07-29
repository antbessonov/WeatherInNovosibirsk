package com.example.weatherinnovosibirsk.data.mapper

import com.example.weatherinnovosibirsk.data.database.WeatherInfoDbModel
import com.example.weatherinnovosibirsk.data.network.model.WeatherInfoDto
import com.example.weatherinnovosibirsk.domain.WeatherInfo

class WeatherMapper {

    fun mapDtoToDbModel(dto: WeatherInfoDto): WeatherInfoDbModel = WeatherInfoDbModel(
        dt = dto.dt,
        name = dto.name,
        speed = dto.wind?.speed,
        deg = dto.wind?.deg,
        id = dto.weather?.get(0)?.id,
        main = dto.weather?.get(0)?.main,
        description = dto.weather?.get(0)?.description,
        icon = dto.weather?.get(0)?.icon,
        temp = dto.main?.temp,
        feelsLike = dto.main?.feelsLike,
        tempMin = dto.main?.tempMin,
        tempMax = dto.main?.tempMax,
        pressure = dto.main?.pressure,
        humidity = dto.main?.humidity
    )

    fun mapDbModelToEntity(dbModel: WeatherInfoDbModel): WeatherInfo {
        return WeatherInfo(
            dt = dbModel.dt,
            name = dbModel.name,
            speed = dbModel.speed,
            deg = dbModel.deg,
            id = dbModel.id,
            main = dbModel.main,
            description = dbModel.description,
            icon = dbModel.icon,
            temp = dbModel.temp,
            feelsLike = dbModel.feelsLike,
            tempMin = dbModel.tempMin,
            tempMax = dbModel.tempMax,
            pressure = dbModel.pressure,
            humidity = dbModel.humidity
        )
    }
}