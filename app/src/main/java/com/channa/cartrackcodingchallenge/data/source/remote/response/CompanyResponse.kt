package com.channa.cartrackcodingchallenge.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CompanyResponse(private val name: String, private val catchPhrase: String, private val bs: String) : Parcelable