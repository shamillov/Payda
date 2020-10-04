package com.shamilov.payda.presentation.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shamilov.payda.R
import com.shamilov.payda.extensions.addBackButton
import kotlinx.android.synthetic.main.fragment_about_app.*

/**
 * Created by Shamilov on 28.05.2020
 */
class AboutAppFragment: Fragment(R.layout.fragment_about_app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.addBackButton { findNavController().popBackStack() }

        btnFeedback.setOnClickListener { findNavController().navigate(R.id.actionAboutAppToFeedback) }
    }
}