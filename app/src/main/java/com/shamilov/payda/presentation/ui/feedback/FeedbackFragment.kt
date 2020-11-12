package com.shamilov.payda.presentation.ui.feedback

import android.os.Bundle
import android.view.View
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.extensions.addBackButton
import kotlinx.android.synthetic.main.fragment_feedback.*
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 30.08.2020
 */
class FeedbackFragment : BaseFragment(R.layout.fragment_feedback), FeedbackView {

    private val presenter by moxyPresenter { FeedbackPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.addBackButton { presenter.navigateBack() }
    }
}