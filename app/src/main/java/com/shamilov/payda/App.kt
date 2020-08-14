package com.shamilov.payda

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.shamilov.payda.di.*
import com.shamilov.payda.di.components.AppComponent
import com.shamilov.payda.di.modules.ApplicationModule
import com.shamilov.payda.di.modules.InteractorModule
import com.shamilov.payda.di.modules.MapperModule
import com.shamilov.payda.di.modules.PresenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Shamilov on 25.05.2020
 */
class App : Application() {

//    private lateinit var appComponent: AppComponent
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        instance = this
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(dataModule, networkModule, mapperModule, interactorModule, schedulesModule))
        }
    }

//    fun getAppComponent(): AppComponent {
//        return appComponent
//    }

    companion object {
        lateinit var instance: App
    }
}