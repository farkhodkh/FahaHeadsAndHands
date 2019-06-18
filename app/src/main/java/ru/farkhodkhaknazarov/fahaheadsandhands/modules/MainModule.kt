package ru.farkhodkhaknazarov.fahaheadsandhands.modules

import dagger.Module
import dagger.Provides
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.LoginFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.StartFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.presenters.LoginFragmentPresenter
import ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.OpenWeatherApi
import ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.OpenWeatherService
import javax.inject.Singleton

@Module
class MainModule {
    @Provides
    fun providesStartFragment(): StartFragment = StartFragment()

    @Provides
    fun providesLoginFragmentPresenter(): LoginFragmentPresenter = LoginFragmentPresenter()

    @Provides
    fun providesLoginFragment(): LoginFragment = LoginFragment()

    @Provides
    fun providesOpenWeatherApi(): OpenWeatherApi = OpenWeatherService().getService()
}