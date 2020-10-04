package com.shamilov.payda.presentation.ui.feedback

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shamilov.payda.R
import com.shamilov.payda.extensions.addBackButton
import com.shamilov.payda.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_feedback.*

/**
 * Created by Shamilov on 30.08.2020
 */
class FeedbackFragment : BaseFragment(R.layout.fragment_feedback) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.addBackButton { findNavController().navigateUp() }
    }
}