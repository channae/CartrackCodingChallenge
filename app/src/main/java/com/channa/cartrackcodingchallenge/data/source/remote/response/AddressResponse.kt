package com.channa.cartrackcodingchallenge.data.source.remote.response

class AddressResponse(
    private val street: String,
    private val suite: String,
    private val city: String,
    private val zipcode: String,
    private val geo: GeoResponse
)