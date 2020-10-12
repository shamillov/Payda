package com.shamilov.payda.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shamilov.payda.R
import com.shamilov.payda.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val presenter by moxyPresenter { ProfilePresenter(findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        btnAssistance.setOnClickListener {
            presenter.navigateToAssistance()
        }

        btnAboutApp.setOnClickListener {
            presenter.navigateToAboutApp()
        }

        btnSettings.setOnClickListener {
            presenter.navigateToSettings()
        }
    }
}
