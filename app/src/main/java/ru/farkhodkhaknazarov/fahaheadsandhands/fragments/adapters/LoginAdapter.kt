package ru.farkhodkhaknazarov.fahaheadsandhands.fragments.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class LoginAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    val mFragments = ArrayList<Fragment>()

    internal fun addFragment(fragment: Fragment) {
        mFragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mFragments.size
}