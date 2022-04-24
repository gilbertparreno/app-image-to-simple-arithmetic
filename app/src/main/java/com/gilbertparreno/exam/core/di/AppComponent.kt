package com.gilbertparreno.exam.core.di

import com.gilbertparreno.exam.ui.main.MainFragment
import com.gilbertparreno.exam.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}