package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter

class Sys {

    @Expose
    @Getter
    var type: Int = 0

    @Expose
    @Getter
    var id: Int = 0

    @Expose
    @Getter
    var message: Double = 0.0

    @Expose
    @Getter
    lateinit var country: String

    @Expose
    @Getter
    var sunrise: Int = 0

    @Expose
    @Getter
    var sunset: Int = 0
}