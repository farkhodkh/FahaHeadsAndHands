package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi

import io.reactivex.Observable
import retrofit2.http.*
import ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.models.RegisterResponse

interface OpenWeatherApi {
    @Headers("Content-Type: application/json")
    @GET("/data/2.5/weather/")
    fun getWheatherForHour(@Query("APPID") APPID: String, @Query("q") cityName: String, @Query("units") units: String = "metric"): Observable<RegisterResponse>

}