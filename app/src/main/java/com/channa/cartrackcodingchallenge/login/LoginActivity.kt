package com.channa.cartrackcodingchallenge.login

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.channa.cartrackcodingchallenge.R
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.OnCountryPickerListener


class LoginActivity : AppCompatActivity() {

    lateinit var flagContainerLinearLayout: LinearLayout
    lateinit var countryTextView: TextView
    lateinit var countryFlagImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.channa.cartrackcodingchallenge.R.layout.activity_login)

        flagContainerLinearLayout = findViewById(R.id.ll_flag_container)
        countryTextView = findViewById(R.id.tv_country)
        countryFlagImageView = findViewById(R.id.iv_flag)

        flagContainerLinearLayout.setOnClickListener(View.OnClickListener {

            val builder = CountryPicker.Builder().with(this)
                    .listener(OnCountryPickerListener { country ->
                        run {
                            countryTextView.text = country.name
                            countryFlagImageView.setImageResource(country.flag)
                        }
                    }).build().showDialog(this)
        })
    }

}
