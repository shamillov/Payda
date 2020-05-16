package com.shamilov.payda.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shamilov.payda.ui.ActiveFragment
import com.shamilov.payda.ui.CompletedFragment

class OrdersPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ActiveFragment()
        } else {
            CompletedFragment()
        }
    }
}