package com.channa.cartrackcodingchallenge.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.channa.cartrackcodingchallenge.data.LoginUser

@Dao
interface LoginUserDao {

    @Query("SELECT * from LoginUser where username == :username")
    fun getLoginUserByUsername(username: String): LoginUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginUser(loginUser: LoginUser)

}