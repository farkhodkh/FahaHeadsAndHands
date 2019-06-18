package ru.farkhodkhaknazarov.fahaheadsandhands.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.farkhodkhaknazarov.fahaheadsandhands.Constants
import ru.farkhodkhaknazarov.fahaheadsandhands.R
import ru.farkhodkhaknazarov.fahaheadsandhands.tools.SessionManager

class MainActivity : AppCompatActivity() {
    lateinit var context: MainActivity

    companion object{
        fun getActivity(): Companion = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        if (!SessionManager(this).getBoolean(Constants.isLoged)){
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }
}
