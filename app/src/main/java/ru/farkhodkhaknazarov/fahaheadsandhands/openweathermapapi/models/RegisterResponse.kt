package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter
import retrofit2.http.GET

class RegisterResponse {
    @Expose
    @Getter
    lateinit var coord: Coord

    @Expose
    @Getter
    lateinit var weather: Array<Weather>

    @Expose
    @Getter
    lateinit var base: String

    @Expose
    @Getter
    lateinit var main: Main

    @Expose
    @Getter
    var visibility: Int = 0

    @Expose
    @Getter
    lateinit var wind: Wind

    @Expose
    @Getter
    lateinit var clouds: Clouds

    @Expose
    @Getter
    var dt: Int = 0

    @Expose
    @Getter
    lateinit var sys: Sys

    @Expose
    @Getter
    var timezone: Int = 0

    @Expose
    @Getter
    var id: Int = 0

    @Expose
    @Getter
    lateinit var name: String

    @Expose
    @Getter
    lateinit var cod: String
}
