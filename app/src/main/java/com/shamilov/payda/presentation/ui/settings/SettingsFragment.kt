package com.shamilov.payda.presentation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.shamilov.payda.BuildConfig
import com.shamilov.payda.R
import com.shamilov.payda.data.local.datastore.SettingsDatastore
import com.shamilov.payda.data.local.preferences.ApplicationPreferences
import com.shamilov.payda.presentation.base.BaseFragment
import com.shamilov.payda.utils.HostSelectionInterceptor
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.ext.scope

/**
 * Created by Shamilov on 07/10/2020
 */
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val dataStore: SettingsDatastore by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            etLocalHost.setText(dataStore.getHost.first())
        }

        btnApply.setOnClickListener {
            if (etLocalHost.text.toString().isNotEmpty()) {
                lifecycleScope.launch {
                    dataStore.putBaseUrl(etLocalHost.text.toString())
                }
            }
        }

        btnReset.setOnClickListener {
            etLocalHost.setText("")
            lifecycleScope.launch {
                dataStore.resetBaseUrl()
            }
        }
    }

}