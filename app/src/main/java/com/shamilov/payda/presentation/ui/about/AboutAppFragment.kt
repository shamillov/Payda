package com.shamilov.payda.presentation.ui.about

import android.os.Bundle
import android.view.View
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentAboutAppBinding
import com.shamilov.payda.extensions.addBackButton
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 28.05.2020
 */
class AboutAppFragment: BaseFragment(R.layout.fragment_about_app), AboutAppView {

    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { AboutAppPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAboutAppBinding.bind(view)

        binding.toolbar.addBackButton { presenter.navigateBack() }

        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initListeners() {
        binding.btnFeedback.setOnClickListener { presenter.navigateToFeedback() }
    }
}