package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter

class Main {

    @Expose
    @Getter
    var temp: Double = 0.0

    @Expose
    @Getter
    var pressure: Int = 0

    @Expose
    @Getter
    var humidity: Int = 0

    @Expose
    @Getter
    var temp_min: Double = 0.0

    @Expose
    @Getter
    var temp_max: Double = 0.0

}