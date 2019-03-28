package com.channa.cartrackcodingchallenge.di.component

import android.content.Context
import com.channa.cartrackcodingchallenge.MainActivity
import com.channa.cartrackcodingchallenge.di.module.ApplicationContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun context(): Context

}
