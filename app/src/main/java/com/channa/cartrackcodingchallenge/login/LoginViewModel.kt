package com.channa.cartrackcodingchallenge.login

import androidx.lifecycle.ViewModel
import com.channa.cartrackcodingchallenge.data.LoginUser
import com.channa.cartrackcodingchallenge.data.source.LoginUserManager

class LoginViewModel : ViewModel() {

    lateinit var loginUserManager: LoginUserManager

    fun init(loginUserManager: LoginUserManager) {
        this.loginUserManager = loginUserManager
    }

    suspend fun authenticateUser(loginUser: LoginUser): Boolean {
        return loginUserManager.isUserAuthenticated(loginUser)
    }

    fun insertNewLoginUser(loginUser: LoginUser) {
        loginUserManager.insertNewLoginUser(loginUser)
    }

}
