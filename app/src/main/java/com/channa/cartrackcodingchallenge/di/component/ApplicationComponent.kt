package com.channa.cartrackcodingchallenge.di.component

import android.content.Context
import com.channa.cartrackcodingchallenge.di.module.ApplicationContextModule
import com.channa.cartrackcodingchallenge.di.module.RemoteConnectionModule
import com.channa.cartrackcodingchallenge.di.module.RoomModule
import com.channa.cartrackcodingchallenge.login.LoginActivity
import com.channa.cartrackcodingchallenge.user_detail.UserListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, RoomModule::class, RemoteConnectionModule::class])
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(userListActivity: UserListActivity)

    fun context(): Context

}
