package com.channa.cartrackcodingchallenge

import android.app.Application
import com.channa.cartrackcodingchallenge.di.component.ApplicationComponent
import com.channa.cartrackcodingchallenge.di.component.DaggerApplicationComponent
import com.channa.cartrackcodingchallenge.di.module.ApplicationContextModule
import com.channa.cartrackcodingchallenge.di.module.RemoteConnectionModule
import com.channa.cartrackcodingchallenge.di.module.RoomModule

class MyApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .roomModule(RoomModule(this))
            .remoteConnectionModule(RemoteConnectionModule())
            .build()

    }
}

