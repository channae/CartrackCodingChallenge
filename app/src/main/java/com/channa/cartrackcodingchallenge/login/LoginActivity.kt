package com.channa.cartrackcodingchallenge.login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.OnCountryPickerListener


class LoginActivity : AppCompatActivity() {

    lateinit var countryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.channa.cartrackcodingchallenge.R.layout.activity_login)

        countryTextView = findViewById(com.channa.cartrackcodingchallenge.R.id.tv_country)
        countryTextView.setOnClickListener(View.OnClickListener {

            val builder = CountryPicker.Builder().with(this)
                    .listener(OnCountryPickerListener { country ->
                        run {
                            Toast.makeText(this, country.name, Toast.LENGTH_LONG).show()
                            countryTextView.text = country.name

                        }
                    }).build().showDialog(this)
        })
    }
}
