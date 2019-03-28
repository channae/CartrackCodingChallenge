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
        get() {
            var phoneList = phone.split(" ")
            var phoneNumber = phoneList.getOrNull(0)

            if (phoneNumber == null || phoneNumber.isBlank()) phoneNumber = "N/A"

            return phoneNumber

        }

    val getPhoneNumberExtension: String
        get() {
            var phoneList = phone.split(" ")
            var extensionNumber = phoneList.getOrNull(1)

            if (extensionNumber == null || extensionNumber.isBlank()) extensionNumber = "N/A"

            return extensionNumber

        }

}