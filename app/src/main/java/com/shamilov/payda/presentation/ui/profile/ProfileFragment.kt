package com.shamilov.payda.presentation.ui.profile

import android.os.Bundle
import android.view.View
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentProfileBinding
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileView {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val presenter by moxyPresenter { ProfilePresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)

        presenter.getContribution()

        initListeners()
    }

    override fun setContribution(count: Int) {
        binding.tvUserContribution.text = count.toString()
    }

    private fun initListeners() {
        binding.btnAssistance.setOnClickListener { presenter.navigateToAssistance() }
        binding.btnAboutApp.setOnClickListener { presenter.navigateToAboutApp() }
        binding.btnSettings.setOnClickListener { presenter.navigateToSettings() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
