package com.shamilov.payda.presentation.ui.about

import android.os.Bundle
import android.view.View
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.extensions.addBackButton
import kotlinx.android.synthetic.main.fragment_about_app.*
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 28.05.2020
 */
class AboutAppFragment: BaseFragment(R.layout.fragment_about_app), AboutAppView {

    private val presenter by moxyPresenter { AboutAppPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.addBackButton { presenter.navigateBack() }

        btnFeedback.setOnClickListener { presenter.navigateToFeedback() }
    }
}