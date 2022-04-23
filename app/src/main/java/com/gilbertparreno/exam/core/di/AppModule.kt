package com.gilbertparreno.exam.core.di

import com.gilbertparreno.exam.core.providers.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesCoroutineContext() = CoroutineContextProvider()
}