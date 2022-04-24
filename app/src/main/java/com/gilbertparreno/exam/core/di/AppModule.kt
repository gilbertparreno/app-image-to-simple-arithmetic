package com.gilbertparreno.exam.core.di

import com.gilbertparreno.exam.core.providers.CoroutineContextProvider
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesCoroutineContext() = CoroutineContextProvider()

    @Singleton
    @Provides
    fun providesTextRecognition() : TextRecognizer {
        return TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }
}