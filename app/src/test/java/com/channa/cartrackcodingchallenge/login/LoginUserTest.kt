package com.channa.cartrackcodingchallenge.login

import com.channa.cartrackcodingchallenge.data.LoginUser
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUserTest {

    lateinit var loginUser: LoginUser

    @Test
    fun isUsernameValid_CorrectUsername_ReturnsTrue() {
        loginUser = LoginUser("user123", "user123")
        assertTrue(loginUser.isUsernameValid)
    }

    @Test
    fun isUsernameValid_BlankUsername_ReturnsFalse() {
        loginUser = LoginUser("      ", "user123")
        assertFalse(loginUser.isUsernameValid)
    }

    @Test
    fun isPasswordValid_CorrectPassword_ReturnsTrue() {
        loginUser = LoginUser("user123", "user123")
        assertTrue(loginUser.isPasswordValid)
    }

    @Test
    fun isPasswordValid_LengthLessThan5Password_ReturnsFalse() {
        loginUser = LoginUser("user123", "user")
        assertFalse(loginUser.isPasswordValid)
    }

    @Test
    fun isPasswordValid_BlankPassword_ReturnsFalse() {
        loginUser = LoginUser("user123", "      ")
        assertFalse(loginUser.isPasswordValid)
    }


}