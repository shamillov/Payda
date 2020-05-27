package com.shamilov.payda.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shamilov.payda.ui.fragments.DonationActiveFragment
import com.shamilov.payda.ui.fragments.DonationCompletedFragment

class OrdersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            DonationActiveFragment()
        } else {
            DonationCompletedFragment()
        }
    }
}