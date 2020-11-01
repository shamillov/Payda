package com.shamilov.payda.presentation.ui.profile

import androidx.navigation.NavController
import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.R
import moxy.InjectViewState

/**
 * Created by Shamilov on 20.07.2020
 */
@InjectViewState
class ProfilePresenter(private val navController: NavController) : BasePresenter<ProfileView>() {

    fun navigateToAssistance() {
        navController.navigate(R.id.actionProfileToAssistance)
    }

    fun navigateToAboutApp() {
        navController.navigate(R.id.actionProfileToAboutApp)
    }

    fun navigateToSettings() {
        navController.navigate(R.id.actionProfileToSettings)
    }

}