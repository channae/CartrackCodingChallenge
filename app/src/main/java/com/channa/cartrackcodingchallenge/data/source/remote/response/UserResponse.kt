package com.channa.cartrackcodingchallenge.data.source.remote.response

class UserResponse(
    private val id: Int,
    private val name: String,
    private val username: String,
    private val email: String,
    private val address: AddressResponse,
    private val phone: String,
    private val website: String,
    private val company: CompanyResponse
) {

    val getPhoneNumber: String
        get() = ""

    val getPhoneNumberExtension: String
        get() = ""

}