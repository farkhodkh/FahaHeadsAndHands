package ru.farkhodkhaknazarov.fahaheadsandhands.modules

import dagger.Component
import ru.farkhodkhaknazarov.fahaheadsandhands.activities.LoginActivity
import ru.farkhodkhaknazarov.fahaheadsandhands.activities.MainActivity
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.LoginFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.presenters.LoginFragmentPresenter
import javax.inject.Singleton

@Component(modules = arrayOf(MainModule::class))
@Singleton
interface AppComponent {

    fun injectLoginActivity(activity: LoginActivity)

    fun injectsMainActivity(mainActivity: MainActivity)

    fun injectLoginFragment(loginFragment: LoginFragment)

    fun injectLoginFragmentPresenter(loginFragmentPresenter: LoginFragmentPresenter)
}