package com.shamilov.payda.presentation.ui.profile

import androidx.navigation.NavController
import com.shamilov.payda.R
import com.shamilov.payda.presentation.base.BasePresenter
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

}