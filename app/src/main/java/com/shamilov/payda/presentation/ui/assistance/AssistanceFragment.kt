package com.shamilov.payda.presentation.ui.assistance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shamilov.payda.R
import com.shamilov.payda.extension.addBackButton
import kotlinx.android.synthetic.main.fragment_assistance.*

/**
 * Created by Shamilov on 28.05.2020
 */
class AssistanceFragment: Fragment(R.layout.fragment_assistance) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.addBackButton { findNavController().popBackStack() }
    }
}