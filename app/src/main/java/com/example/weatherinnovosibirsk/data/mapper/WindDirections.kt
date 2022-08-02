package com.example.weatherinnovosibirsk.data.mapper

enum class WindDirections(val windDirection: String, val windDed: IntRange) {
    N_0_11("С", 0..11),
    NNE("ССВ", 12..33),
    NE("СВ", 34..56),
    ENE("ВСВ", 57..78),
    E("В", 79..101),
    ESE("ВЮВ", 102..123),
    SE("ЮВ", 124..146),
    SSE("ЮЮВ", 147..168),
    S("Ю", 169..191),
    SSW("ЮЮЗ", 192..213),
    SW("ЮЗ", 214..236),
    WSW("ЗЮЗ", 237..258),
    W("З", 259..281),
    WNW("ЗСЗ", 282..303),
    NW("СЗ", 304..326),
    NNW("ССЗ", 327..348),
    N_349_360("C", 349..360)
}