package com.channa.cartrackcodingchallenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LoginUser(@field:PrimaryKey val username: String, val password: String, val country: String) {

    val isUsernameValid: Boolean
        get() = !username.isBlank()

    val isPasswordValid: Boolean
        get() = password.length > 5 && !password.isBlank()

    val isCountryValid: Boolean
        get() = !country.isBlank()

}