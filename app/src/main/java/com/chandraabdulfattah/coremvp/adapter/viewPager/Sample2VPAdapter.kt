package com.chandraabdulfattah.coremvp.adapter.viewPager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by bezzo on 08/11/17.
 */

class Sample2VPAdapter(fm: FragmentManager, val context: Context) : FragmentStatePagerAdapter(fm) {

    val PAGE_COUNT = 3
    var tabTitles: Array<String> = arrayOf("Fragment1", "Fragment2", "Fragment3")

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> {
                // fragment - 1
            }
            1 -> {
                // fragment - 2
            }
            2 -> {
                // fragment - 3
            }
        }

        return fragment
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}