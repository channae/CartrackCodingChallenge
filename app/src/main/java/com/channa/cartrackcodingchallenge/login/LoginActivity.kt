package com.channa.cartrackcodingchallenge.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.channa.cartrackcodingchallenge.MyApplication
import com.channa.cartrackcodingchallenge.R
import com.channa.cartrackcodingchallenge.data.LoginUser
import com.channa.cartrackcodingchallenge.data.source.LoginUserManager
import com.channa.cartrackcodingchallenge.user_detail.UserListActivity
import com.channa.cartrackcodingchallenge.utils.CustomAnimations
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.OnCountryPickerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @BindView(R.id.ll_login_container)
    lateinit var loginContainerLinearLayout: LinearLayout

    @BindView(R.id.et_username)
    lateinit var usernameEditText: EditText

    @BindView(R.id.et_password)
    lateinit var passwordEditText: EditText

    @BindView(R.id.tv_country)
    lateinit var countryTextView: TextView

    @BindView(R.id.btn_login)
    lateinit var loginButton: Button

    @BindView(R.id.iv_flag)
    lateinit var countryFlagImageView: ImageView

    @BindView(R.id.ll_flag_container)
    lateinit var flagContainerLinearLayout: LinearLayout

    // Error displays

    @BindView(R.id.tv_username_error)
    lateinit var usernameErrorTextView: TextView

    @BindView(R.id.tv_password_error)
    lateinit var passwordErrorTextView: TextView

    @BindView(R.id.tv_country_error)
    lateinit var countryErrorTextView: TextView

    @Inject
    lateinit var loginUserManager: LoginUserManager

    companion object {
        private const val TAG = "LoginActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.channa.cartrackcodingchallenge.R.layout.activity_login)
        ButterKnife.bind(this)

        (application as MyApplication).applicationComponent?.inject(this)

        val loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.init(loginUserManager)


        loginViewModel.insertNewLoginUser(LoginUser("user123", "user123", "Singapore"))

        flagContainerLinearLayout.setOnClickListener(View.OnClickListener {

            val builder = CountryPicker.Builder().with(this)
                .listener(OnCountryPickerListener { country ->
                    run {
                        countryTextView.text = country.name
                        countryFlagImageView.setImageResource(country.flag)
                    }
                }).build().showDialog(this)
        })

        loginButton.setOnClickListener(View.OnClickListener {

            GlobalScope.launch(Dispatchers.Main) {

                var isInputValidationError: Boolean = false

                var username = usernameEditText.text.toString()
                var password = passwordEditText.text.toString()
                var country = countryTextView.text.toString()

                var loginUser = LoginUser(username, password, country)

                if (!loginUser.isUsernameValid) {
                    usernameErrorTextView.text = "Username cannot be empty"
                    isInputValidationError = true
                } else
                    usernameErrorTextView.text = ""


                if (!loginUser.isPasswordValid) {
                    passwordErrorTextView.text = "Password cannot be empty"
                    isInputValidationError = true
                } else
                    passwordErrorTextView.text = ""

                if (!loginUser.isCountryValid) {
                    countryErrorTextView.text = "Country cannot be empty"
                    isInputValidationError = true
                } else
                    countryErrorTextView.text = ""


                if (!isInputValidationError) {
                    clearInputValidationErrors()
                    if (loginViewModel.authenticateUser(loginUser)) {
                        Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_LONG).show()

                        val intent = Intent(this@LoginActivity, UserListActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Login Failed, Incorrect user credentials!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        CustomAnimations.shake(applicationContext, loginContainerLinearLayout)
                    }
                }
            }
        })
    }

    fun clearInputValidationErrors() {
        usernameErrorTextView.text = ""
        passwordErrorTextView.text = ""
        countryErrorTextView.text = ""
    }

}
