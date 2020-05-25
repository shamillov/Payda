package com.shamilov.payda

import android.app.Application
import com.shamilov.payda.di.components.AppComponent
import com.shamilov.payda.di.components.DaggerAppComponent
import com.shamilov.payda.di.modules.*

/**
 * Created by Shamilov on 25.05.2020
 */
class App: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = initializeDagger()
    }

    private fun initializeDagger(): AppComponent {
        return DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .interactorModule(InteractorModule())
            .mapperModule(MapperModule())
            .presenterModule(PresenterModule())
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    companion object {
        lateinit var instance: App
    }
}