package com.shamilov.payda.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.domain.repository.DatastoreRepository
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val presenter by moxyPresenter { ProfilePresenter(findNavController()) }
    private val datastore: DatastoreRepository by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        lifecycleScope.launch {
            datastore.getContribution.collect { tvUserContribution.text = it.toString() }
        }
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
