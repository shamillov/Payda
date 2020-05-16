package com.shamilov.payda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.payda.R
import com.shamilov.payda.ui.adapters.OrdersPagerAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager: ViewPager2 = root.findViewById(R.id.viewPager)
        viewPager.adapter = OrdersPagerAdapter(this)

        val tabLayout: TabLayout = root.findViewById(R.id.tabLayout)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy {
                tab, position ->
            if (position == 0)
                tab.text = "Active"
            else
                tab.text = "Complete"
        })
        tabLayoutMediator.attach()

        return root
    }
}
