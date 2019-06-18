package ru.farkhodkhaknazarov.fahaheadsandhands

import android.app.Application
import ru.farkhodkhaknazarov.fahaheadsandhands.modules.AppComponent
import ru.farkhodkhaknazarov.fahaheadsandhands.modules.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDagger();
    }

    private fun initializeDagger() {
        component = DaggerAppComponent.create();
    }

    companion object {
        private lateinit var component: AppComponent

        fun getComponent(): AppComponent {

            component = DaggerAppComponent.create();

            return component;
        }
    }
}