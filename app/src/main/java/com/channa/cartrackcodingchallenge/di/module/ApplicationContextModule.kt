package com.channa.cartrackcodingchallenge.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(val context: Context) {

    @Provides
    fun provideApplicationContext(): Context {
        return context
    }

}
