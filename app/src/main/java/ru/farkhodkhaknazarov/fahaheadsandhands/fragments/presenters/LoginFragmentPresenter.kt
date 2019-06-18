package ru.farkhodkhaknazarov.fahaheadsandhands.fragments.presenters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.farkhodkhaknazarov.fahaheadsandhands.App
import ru.farkhodkhaknazarov.fahaheadsandhands.Constants
import ru.farkhodkhaknazarov.fahaheadsandhands.R
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.LoginFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.openweathermapapi.OpenWeatherApi
import javax.inject.Inject
import javax.inject.Singleton

class LoginFragmentPresenter(var loginFragment: LoginFragment) : TextView.OnEditorActionListener {
    constructor() : this(LoginFragment())

    @Singleton
    @Inject
    lateinit var service: OpenWeatherApi
    lateinit var loginFragmentView: View
    var disposable: Disposable? =null

    lateinit var til_email: TextInputLayout
    lateinit var til_password: TextInputLayout
    lateinit var tie_password: EditText
    lateinit var tie_email: TextInputEditText
    lateinit var btnPassword: Button
    lateinit var btnEnter: Button

    fun prepareView(inflater: LayoutInflater, container: ViewGroup?): View {
        App.getComponent().injectLoginFragmentPresenter(this)
        loginFragmentView = inflater.inflate(R.layout.fragment_login, container, false)

        til_password = loginFragmentView.findViewById(R.id.til_password)
        tie_password = loginFragmentView.findViewById(R.id.tie_password)
        btnPassword = loginFragmentView.findViewById(R.id.btnPassword)
        btnEnter = loginFragmentView.findViewById(R.id.btnEnter)

        til_email = loginFragmentView.findViewById(R.id.til_email)
        tie_email = loginFragmentView.findViewById(R.id.tie_email)

        tie_password.setOnEditorActionListener(this)
        tie_email.setOnEditorActionListener(this)

        btnPassword.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(
                loginFragment.context,
                loginFragment.getString(R.string.pass_description),
                Toast.LENGTH_LONG
            ).show()
        })

        btnEnter.setOnClickListener(View.OnClickListener { view ->

            if (!hasConnection()) {
                showNetworkNotConnectedAlert()
            } else {

                var error = false
                loginFragmentView.clearFocus()

                val imm = loginFragment.context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(loginFragmentView.windowToken, 0)

                if (checkPassword(tie_password.text.toString())) {
                    Toast.makeText(
                        loginFragment.context,
                        loginFragment.getString(R.string.pass_description),
                        Toast.LENGTH_LONG
                    ).show()
                    error = true
                }

                if (checkEMail(tie_email.text.toString())) {
                    Toast.makeText(
                        loginFragment.context,
                        loginFragment.getString(R.string.email_error_text),
                        Toast.LENGTH_LONG
                    ).show()
                    error = true
                }

                if (!error) {
                    getWeather()
                }
            }
        })

        return loginFragmentView
    }

    private fun showNetworkNotConnectedAlert() {
        val dialogBuilder = AlertDialog.Builder(loginFragment.context)

        dialogBuilder.setMessage(loginFragment.getString(R.string.network_alert_message))
            .setCancelable(false)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                val viewIntent: Intent = Intent(android.provider.Settings.ACTION_SETTINGS)
                loginFragment.activity?.startActivity(viewIntent)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Настройки сети")
        alert.show()
    }

    fun getWeather() {
        disposable = service
            .getWheatherForHour(Constants.APIID, "Moscow")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Snackbar
                        .make(
                            loginFragmentView, "Погода сейчас для ${result.name}: " +
                                    "скорость ветра ${result.wind.speed} м/с " +
                                    "температура ${result.main.temp} градусов " +
                                    "осадки ${result.weather[0].main}",
                            Snackbar.LENGTH_LONG
                        )
                        .setAction("Action", null).show()
                },
                { error ->
                    error.printStackTrace()
                }
            )
    }

    override fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean = when (view?.id) {
        tie_password.id -> checkPassword(view.text.toString())
        tie_email.id -> checkEMail(view.text.toString())
        else -> false
    }

    fun hasConnection(): Boolean {
        val context = loginFragment.context
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        if (wifiInfo != null)
            return wifiInfo.isConnected

        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifiInfo != null)
            return wifiInfo.isConnected

        wifiInfo = cm.activeNetworkInfo

        if (wifiInfo == null)
            return false
        else return wifiInfo.isConnected
    }

    fun checkEMail(email: String): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            til_email.error = loginFragment.getString(R.string.email_error_text)
            return true
        } else {
            til_email.error = ""
            return false
        }
    }

    fun checkPassword(pass: String): Boolean {
        if (!pass.matches(Constants.PAT.toRegex())) {
            til_password.error = loginFragment.getString(R.string.pass_description)
            return true
        } else {
            til_password.error = ""
            return false
        }
    }

    fun onDestroyView() = disposable?.dispose()
}