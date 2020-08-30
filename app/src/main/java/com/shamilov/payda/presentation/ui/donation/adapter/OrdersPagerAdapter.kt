package com.shamilov.payda.presentation.ui.donation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shamilov.payda.presentation.ui.donation.active.DonationActiveFragment
import com.shamilov.payda.presentation.ui.donation.completed.DonationCompletedFragment

/**
 * Created by Shamilov on 25.07.2020
 */
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