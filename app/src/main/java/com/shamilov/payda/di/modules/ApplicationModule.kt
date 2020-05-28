package com.shamilov.payda.di.modules

import android.content.Context
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.executor.SchedulerProviderImpl
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Shamilov on 25.05.2020
 */
@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider {
        return SchedulerProviderImpl()
    }
}