package com.shamilov.payda.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentProfileBinding
import com.shamilov.payda.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileView {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val presenter by moxyPresenter { ProfilePresenter() }
    private val datastore: DatastoreRepository by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)

        initListeners()

        lifecycleScope.launch {
            datastore.getContribution.collect { binding.tvUserContribution.text = it.toString() }
        }
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
