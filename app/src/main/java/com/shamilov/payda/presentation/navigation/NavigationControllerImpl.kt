package com.shamilov.payda.presentation.navigation

import androidx.navigation.NavController
import com.shamilov.payda.R

class NavigationControllerImpl(private val navController: NavController) : NavigationController {
    override fun pop() {
        navController.popBackStack()
    }

    override fun navigateFromProfileToAboutApp() {
        navController.navigate(R.id.action_profileFragment_to_aboutCarFragment)
    }

    override fun navigateFromProfileToAssistance() {

    }

    override fun navigateFromProfileToMyDonation() {

    }
}