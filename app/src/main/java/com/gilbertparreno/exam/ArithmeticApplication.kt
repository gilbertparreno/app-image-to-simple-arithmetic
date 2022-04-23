package com.gilbertparreno.exam

import android.app.Application
import com.gilbertparreno.exam.core.di.AppComponent
import com.gilbertparreno.exam.core.di.AppModule
import com.gilbertparreno.exam.core.di.DaggerAppComponent
import timber.log.Timber

class ArithmeticApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()

        Timber.plant(Timber.DebugTree())
    }
}