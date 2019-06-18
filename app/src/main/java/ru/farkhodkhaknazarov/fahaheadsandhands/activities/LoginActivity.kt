package ru.farkhodkhaknazarov.fahaheadsandhands.activities

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import ru.farkhodkhaknazarov.fahaheadsandhands.App
import ru.farkhodkhaknazarov.fahaheadsandhands.R
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.LoginFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.StartFragment
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.adapters.LoginAdapter
import ru.farkhodkhaknazarov.fahaheadsandhands.fragments.tools.NonSwipeableViewPager
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    lateinit var context: LoginActivity
    @Inject
    lateinit var startFragment: StartFragment
    @Inject
    lateinit var loginFragment: LoginFragment
    lateinit var loginViewPager: NonSwipeableViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewPager = findViewById(R.id.loginViewPager)
        App.getComponent().injectLoginActivity(this)


        setupViewPager()
        changeFragment(0)
        startFragmentChangeThread()
    }

    private fun setupViewPager() {
        val adapter = LoginAdapter(supportFragmentManager)
        adapter.addFragment(startFragment)
        adapter.addFragment(loginFragment)
        loginViewPager.adapter = adapter
    }

    fun changeFragment(fragmentId: Int) {
        loginViewPager.currentItem = fragmentId
    }

    override fun onBackPressed() = when (loginViewPager.currentItem) {
        2 -> loginViewPager.setCurrentItem(1, true)
        1 -> finish()
        else -> loginViewPager.setCurrentItem(1, true)
    }

    private fun startFragmentChangeThread() {
        val handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                loginViewPager.currentItem = 1
            }
        }

        val runnable = Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val msg = Message()
            handler.sendMessage(msg)
        }
        val thread = Thread(runnable)
        thread.start()
    }
}
