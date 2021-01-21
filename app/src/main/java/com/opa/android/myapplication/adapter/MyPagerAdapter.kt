package com.opa.android.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.opa.android.myapplication.fragment.FragmentDetails
import com.opa.android.myapplication.fragment.FragmentForm

class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private lateinit var fragment: Fragment

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            fragment = FragmentForm.newInstance()
        } else if (position == 1) {
            fragment = FragmentDetails.newInstance()
        }

        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}