package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter

class Wind {
    @Expose
    @Getter
    var speed: Double = 0.0

    @Expose
    @Getter
    var deg: Double = 0.0
}