package ru.farkhodkhaknazarov.fahaheadsandhands.tools

import android.app.Activity
import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.farkhodkhaknazarov.fahaheadsandhands.activities.LoginActivity
import ru.farkhodkhaknazarov.fahaheadsandhands.activities.MainActivity

class SessionManager(var activity: Activity) {
    private fun getPref(): SharedPreferences {

        //TODO Переопределить функцию для поиска активити
        return PreferenceManager.getDefaultSharedPreferences(activity)
//        return if (LoginActivity.getActivity() == null) {
//            PreferenceManager.getDefaultSharedPreferences(MainActivity.)
//        } else {
//            PreferenceManager.getDefaultSharedPreferences(LoginActivity.getContext())
//        }
    }

    fun save(key: String, value: String) {
        getPref().edit().putString(key, value).apply()
    }

    fun save(key: String, value: Boolean?) {
        getPref().edit().putBoolean(key, value!!).apply()
    }

    fun save(key: String, value: Int) {
        getPref().edit().putInt(key, value).apply()
    }

    fun checkExist(key: String): Boolean {
        return getPref().contains(key)
    }

    fun getString(key: String): String? {
        return getPref().getString(key, null)
    }

    fun getBoolean(key: String): Boolean {
        return getPref().getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return getPref().getInt(key, 0)
    }

    fun clear() {
        getPref().edit().clear().apply()
    }
}