package com.channa.cartrackcodingchallenge.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.channa.cartrackcodingchallenge.data.source.UserListWrapper
import com.channa.cartrackcodingchallenge.data.source.UserRepository


class UserListViewModel : ViewModel() {

    lateinit var userRepository: UserRepository

    fun init(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun getUsers(): LiveData<UserListWrapper> {
        return userRepository.getUsers()
    }

}