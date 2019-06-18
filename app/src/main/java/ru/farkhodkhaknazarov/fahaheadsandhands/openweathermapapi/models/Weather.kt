package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models

import com.google.gson.annotations.Expose
import lombok.Getter

class Weather {
    @Expose
    @Getter
    var id: Int = 0

    @Expose
    @Getter
    lateinit var main: String

    @Expose
    @Getter
    lateinit var description: String

    @Expose
    @Getter
    lateinit var icon: String


}