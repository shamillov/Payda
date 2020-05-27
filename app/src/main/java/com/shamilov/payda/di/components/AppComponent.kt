package com.shamilov.payda.di.components

import com.shamilov.payda.di.modules.*
import com.shamilov.payda.ui.fragments.DonationActiveFragment
import com.shamilov.payda.ui.fragments.DonationCompletedFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Shamilov on 25.05.2020
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        InteractorModule::class,
        MapperModule::class,
        NetworkModule::class,
        PresenterModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(activeFragment: DonationActiveFragment)
    fun inject(completedFragment: DonationCompletedFragment)
}
