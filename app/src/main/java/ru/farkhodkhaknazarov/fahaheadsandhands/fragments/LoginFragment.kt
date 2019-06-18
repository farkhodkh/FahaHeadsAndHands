package ru.farkhodkhaknazarov.fahaheadsandhands.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.farkhodkhaknazarov.fahaheadsandhands.App
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.presenters.LoginFragmentPresenter


class LoginFragment : Fragment() {
    lateinit var loginPresenter: LoginFragmentPresenter
    lateinit var loginFragmentView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.getComponent().injectLoginFragment(this)
        loginPresenter = LoginFragmentPresenter(this)
        loginFragmentView = loginPresenter.prepareView(inflater, container)

        return loginFragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginPresenter.onDestroyView()
    }
}