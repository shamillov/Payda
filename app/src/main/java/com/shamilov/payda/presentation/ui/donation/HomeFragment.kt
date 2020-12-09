package com.shamilov.payda.presentation.ui.donation

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentHomeBinding
import com.shamilov.payda.presentation.ui.donation.adapter.OrdersPagerAdapter

/**
 * Created by Shamilov on 20.05.2020
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.viewPager.adapter = OrdersPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0)
                tab.text = getString(R.string.tabLayout_active_donation)
            else
                tab.text = getString(R.string.tabLayout_completed_donation)
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
