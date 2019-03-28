package com.channa.cartrackcodingchallenge.data

class LoginUser(private val username: String, private val password: String) {

    val isUsernameValid: Boolean
        get() = false

    val isPasswordValid: Boolean
        get() = false

}