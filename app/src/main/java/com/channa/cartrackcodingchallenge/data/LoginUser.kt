package com.channa.cartrackcodingchallenge.data

class LoginUser(private val username: String, private val password: String) {

    val isUsernameValid: Boolean
        get() = !username.isBlank()

    val isPasswordValid: Boolean
        get() = password.length > 5 && !password.isBlank()

}