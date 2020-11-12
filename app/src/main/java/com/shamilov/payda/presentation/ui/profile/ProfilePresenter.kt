package com.shamilov.payda.presentation.ui.profile

import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.domain.interactor.ProfileInteractor
import com.shamilov.payda.presentation.navigation.NavigationActions.toAboutApp
import com.shamilov.payda.presentation.navigation.NavigationActions.toAssistance
import com.shamilov.payda.presentation.navigation.NavigationActions.toSettings
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import org.koin.core.inject

/**
 * Created by Shamilov on 20.07.2020
 */
@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {

    private val interactor: ProfileInteractor by inject()

    fun navigateToAssistance() { navigate(toAssistance) }
    fun navigateToAboutApp() { navigate(toAboutApp) }
    fun navigateToSettings() { navigate(toSettings) }

    fun getContribution() {
        presenterScope.launch {
            interactor.getContributionCount().collect { viewState.setContribution(it) }
        }
    }

}