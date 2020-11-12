package com.shamilov.payda.presentation.ui.about

import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.presentation.navigation.NavigationActions.toFeedback
import moxy.InjectViewState

/**
 * Created by Shamilov on 06.11.2020
 */
@InjectViewState
class AboutAppPresenter : BasePresenter<AboutAppView>() {
    fun navigateToFeedback() { navigate(toFeedback) }
}