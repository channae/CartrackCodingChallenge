package com.channa.cartrackcodingchallenge.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.channa.cartrackcodingchallenge.BaseActivity
import com.channa.cartrackcodingchallenge.MyApplication
import com.channa.cartrackcodingchallenge.data.LoginUser
import com.channa.cartrackcodingchallenge.data.source.LoginUserManager
import com.channa.cartrackcodingchallenge.user_detail.UserListActivity
import com.channa.cartrackcodingchallenge.utils.CustomAnimations
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.listeners.OnCountryPickerListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginActivity : BaseActivity() {

    @Inject
    lateinit var loginUserManager: LoginUserManager

    companion object {
        private const val TAG = "LoginActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.channa.cartrackcodingchallenge.R.layout.activity_login)

        (application as MyApplication).applicationComponent?.inject(this)

        val loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.init(loginUserManager)


        loginViewModel.insertNewLoginUser(LoginUser("user123", "user123", "Singapore"))

        ll_flag_container.setOnClickListener(View.OnClickListener {

            val builder = CountryPicker.Builder().with(this)
                .listener(OnCountryPickerListener { country ->
                    run {

                        et_username.clearFocus()
                        et_password.clearFocus()


                        hideKeyboard(this)
                        tv_country.text = country.name
                        iv_flag.setImageResource(country.flag)

                    }
                }).build().showDialog(this)
        })

        btn_login.setOnClickListener(View.OnClickListener {

            GlobalScope.launch(Dispatchers.Main) {

                var isInputValidationError = false

                var username = et_username.text.toString()
                var password = et_password.text.toString()
                var country = tv_country.text.toString()

                var loginUser = LoginUser(username, password, country)

                if (!loginUser.isUsernameValid) {
                    tv_username_error.text = "Username cannot be empty"
                    isInputValidationError = true
                } else
                    tv_username_error.text = ""


                if (!loginUser.isPasswordValid) {
                    tv_password_error.text = "Password cannot be empty"
                    isInputValidationError = true
                } else
                    tv_password_error.text = ""

                if (!loginUser.isCountryValid) {
                    tv_country_error.text = "Country cannot be empty"
                    isInputValidationError = true
                } else
                    tv_country_error.text = ""


                if (!isInputValidationError) {
                    clearInputValidationErrors()
                    if (loginViewModel.authenticateUser(loginUser)) {

                        val intent = Intent(this@LoginActivity, UserListActivity::class.java)
                        startActivity(intent)

                    } else {
                        showSnackBar(cl_parent, "Login Failed, Incorrect user credentials!")
                        CustomAnimations.shake(applicationContext, ll_login_container)
                    }
                }
            }
        })

        cl_parent.setOnClickListener(View.OnClickListener { hideKeyboard(this) })
    }

    private fun clearInputValidationErrors() {
        tv_username_error.text = ""
        tv_password_error.text = ""
        tv_country_error.text = ""
    }

}
