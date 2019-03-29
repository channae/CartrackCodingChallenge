package com.channa.cartrackcodingchallenge.di.module

import android.content.Context
import com.channa.cartrackcodingchallenge.data.source.UserRepository
import com.channa.cartrackcodingchallenge.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteConnectionModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepository(userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(context: Context): UserRemoteDataSource {
        return UserRemoteDataSource(context)
    }

}