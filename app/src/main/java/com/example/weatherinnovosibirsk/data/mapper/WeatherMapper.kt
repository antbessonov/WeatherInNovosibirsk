package com.example.weatherinnovosibirsk.data.mapper

import com.example.weatherinnovosibirsk.data.database.WeatherInfoDbModel
import com.example.weatherinnovosibirsk.data.network.model.WeatherInfoDto
import com.example.weatherinnovosibirsk.domain.WeatherInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherMapper {

    companion object {

        const val BASE_ICON_URL = "https://openweathermap.org/img/wn/"
        const val ICON_FORMAT = ".png"
        const val ONE_MM_HG_TO_HPA = 1.33317
    }

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

    fun mapDbModelToEntity(dbModel: WeatherInfoDbModel): WeatherInfo = WeatherInfo(
        dt = convertTimestampToTime(dbModel.dt),
        name = dbModel.name,
        speed = dbModel.speed.toString(),
        deg = convertWindDegToDirection(dbModel.deg),
        icon = BASE_ICON_URL + dbModel.icon + ICON_FORMAT,
        temp = dbModel.temp?.roundToInt().toString(),
        feelsLike = dbModel.feelsLike?.roundToInt().toString(),
        pressure = convertPressureHPaToMmHg(dbModel.pressure).toString(),
        humidity = dbModel.humidity.toString()
    )

    private fun convertTimestampToTime(timestamp: Int?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp((timestamp.toLong() * 1000))
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    private fun convertPressureHPaToMmHg(pressureHPa: Int?): Int {
        if (pressureHPa == null) return 0
        return (pressureHPa.div(ONE_MM_HG_TO_HPA)).roundToInt()
    }

    private fun convertWindDegToDirection(windDed: Int?): String {
        return when (windDed) {
            in WindDirections.N_0_11.windDed -> WindDirections.N_0_11.windDirection
            in WindDirections.NNE.windDed -> WindDirections.NNE.windDirection
            in WindDirections.NE.windDed -> WindDirections.NE.windDirection
            in WindDirections.ENE.windDed -> WindDirections.ENE.windDirection
            in WindDirections.E.windDed -> WindDirections.E.windDirection
            in WindDirections.ESE.windDed -> WindDirections.ESE.windDirection
            in WindDirections.SE.windDed -> WindDirections.SE.windDirection
            in WindDirections.SSE.windDed -> WindDirections.SSE.windDirection
            in WindDirections.S.windDed -> WindDirections.S.windDirection
            in WindDirections.SSW.windDed -> WindDirections.SSW.windDirection
            in WindDirections.SW.windDed -> WindDirections.SW.windDirection
            in WindDirections.WSW.windDed -> WindDirections.WSW.windDirection
            in WindDirections.W.windDed -> WindDirections.W.windDirection
            in WindDirections.WNW.windDed -> WindDirections.WNW.windDirection
            in WindDirections.NW.windDed -> WindDirections.NW.windDirection
            in WindDirections.NNW.windDed -> WindDirections.NNW.windDirection
            in WindDirections.N_349_360.windDed -> WindDirections.N_349_360.windDirection
            else -> ""
        }
    }
}