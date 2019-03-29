package com.channa.cartrackcodingchallenge.data.source.remote.action

import com.channa.cartrackcodingchallenge.data.source.remote.response.UserResponse

interface OnUsersResponse : OnErrorResponse {
    fun onSuccessUsersResponse(userResponseList: List<UserResponse>)
}