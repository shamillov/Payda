package com.shamilov.payda.presentation.ui.profile

import androidx.navigation.NavController
import com.shamilov.payda.R
import com.shamilov.payda.presentation.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class ProfilePresenter(private val navController: NavController) : BasePresenter<ProfileView>() {

    fun navigateToAssistance() {
        navController.navigate(R.id.navigation_assistance)
    }

    fun navigateToAboutApp() {
        navController.navigate(R.id.action_profileFragment_to_aboutCarFragment)

    }

}