package com.channa.cartrackcodingchallenge.data.source

import com.channa.cartrackcodingchallenge.data.LoginUser
import com.channa.cartrackcodingchallenge.data.source.local.LoginUserLocalDataSource
import javax.inject.Inject

class LoginUserManager @Inject constructor(var loginUserLocalDataSource: LoginUserLocalDataSource) {

    suspend fun isUserAuthenticated(loginUser: LoginUser): Boolean {
        var user = loginUserLocalDataSource.getLoginUserByUsername(loginUser.username)

        return if (user != null) {
            user.password == loginUser.password && user.country == loginUser.country
        } else
            false
    }

    fun insertNewLoginUser(loginUser: LoginUser) {
        loginUserLocalDataSource.insertNewLoginUser(loginUser)
    }

}