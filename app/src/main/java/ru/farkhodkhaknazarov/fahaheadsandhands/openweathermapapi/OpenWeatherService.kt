package ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.farkhodkhaknazarov.fahaheadsandhands.Constants

class OpenWeatherService {

    fun getService(): OpenWeatherApi {
        var gson: Gson = GsonBuilder().setLenient().create()
        var retrofit: Retrofit = Retrofit.Builder()
                                .baseUrl(Constants.BASE_URL)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build()

        return retrofit.create<OpenWeatherApi>(OpenWeatherApi::class.java!!);

    }
}