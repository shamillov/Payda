package com.shamilov.payda.presentation.ui.profile

import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.R
import moxy.InjectViewState

/**
 * Created by Shamilov on 20.07.2020
 */
@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {
    fun navigateToAssistance() { navigate(R.id.actionProfileToAssistance) }
    fun navigateToAboutApp() { navigate(R.id.actionProfileToAboutApp) }
    fun navigateToSettings() { navigate(R.id.actionProfileToSettings) }
}