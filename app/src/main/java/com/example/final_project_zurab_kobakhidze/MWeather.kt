package com.example.final_project_zurab_kobakhidze

class MWeather {

    var weather: List<MWeatherDescription>? = null
    var main: MWeatherTemperature? = null
    var name: String? = null

    override fun toString(): String {
        return "MWeather(weather=$weather, main=$main, name=$name)"
    }

}

class MWeatherDescription {

    var main: String? = null
    var icon: String? = null

    override fun toString(): String {
        return "MWeatherDescription(main=$main, icon=$icon)"
    }

}

class MWeatherTemperature {

    var temp: Double? = null

    override fun toString(): String {
        return "MWeatherTemperature(temp=$temp)"
    }

}