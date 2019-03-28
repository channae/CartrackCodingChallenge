package com.channa.cartrackcodingchallenge.di.component

import android.content.Context
import com.channa.cartrackcodingchallenge.MainActivity
import com.channa.cartrackcodingchallenge.di.module.ApplicationContextModule
import com.channa.cartrackcodingchallenge.di.module.RoomModule
import com.channa.cartrackcodingchallenge.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, RoomModule::class])
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)

    fun context(): Context

}
