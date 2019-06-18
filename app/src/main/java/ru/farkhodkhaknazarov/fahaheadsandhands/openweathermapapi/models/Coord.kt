package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter

class Coord {
    @Expose
    @Getter
    var lon: Double = 0.0

    @Expose
    @Getter
    var lat: Double = 0.0
}