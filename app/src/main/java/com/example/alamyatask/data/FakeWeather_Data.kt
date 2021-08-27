package com.example.alamyatask.data

import com.example.alamyatask.data.network.response.ListItem
import com.example.alamyatask.data.network.response.WeatherItem


object FakeWeather_Data {

    const val FAKE_NETWORK_DELAY = 1000L

    val weatheritems = arrayOf(
        ListItem("15-8-2021", listOf(
            WeatherItem("","more clouds at the end of day",
            "Cloudly",55))
        ),
        ListItem("16-8-2021", listOf(
            WeatherItem("","more sunny at the end of day",
                "Sunny",55))
        ),
        ListItem("17-8-2021", listOf(
            WeatherItem("","more Rains at the end of day",
                "Rainy",55))
        ),
        ListItem("18-8-2021", listOf(
            WeatherItem("","more warm at the end of day",
                "Warmlu",55))
        )
    )
}