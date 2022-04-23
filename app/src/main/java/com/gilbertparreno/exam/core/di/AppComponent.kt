package com.gilbertparreno.exam.core.di

import com.gilbertparreno.exam.MainFragment
import com.gilbertparreno.exam.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}