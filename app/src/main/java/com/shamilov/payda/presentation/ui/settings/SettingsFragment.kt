package com.shamilov.payda.presentation.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.data.local.datastore.SettingsDatastore
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Created by Shamilov on 07/10/2020
 */
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val dataStore: SettingsDatastore by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            dataStore.getHost.collect { prefs ->
                etLocalHost.setText(prefs)
            }
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