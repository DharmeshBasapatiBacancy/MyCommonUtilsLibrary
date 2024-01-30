package com.example.mycommonutilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycommonutilslibrary.BuildConfig.FLAVOR
import com.example.mycommonutilslibrary.BuildConfig.VERSION_NAME
import com.example.mycommonutilslibrary.databinding.ActivityLoginBinding
import com.example.ourbaseutils.logging.showLongToast
import com.example.ourbaseutils.logging.showShortToast
import com.example.ourbaseutils.views.hideKeyboard
import com.example.ourbaseutils.views.isEmailValid
import com.example.ourbaseutils.views.isNotNull
import com.example.ourbaseutils.views.isValidMobileNumber
import com.example.ourbaseutils.views.isValidPassword

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSubmit.setOnClickListener {
                when {
                    !isNotNull(edtEmail.text.toString()) -> {
                        showShortToast("Enter email address")
                    }

                    !isEmailValid(edtEmail.text.toString()) -> {
                        showShortToast("Please enter valid email address")
                    }

                    !isNotNull(edtPassword.text.toString()) -> {
                        showShortToast("Enter password")
                    }

                    !isValidPassword(edtPassword.text.toString()) -> {
                        showLongToast("1 Upper case, 1 Lower case, 1 Special Characters : minimum 6 characters")
                    }

                    !isNotNull(edtMobileNumber.text.toString()) -> {
                        showShortToast("Enter mobile number")
                    }

                    !edtMobileNumber.text.toString().isValidMobileNumber() -> {
                        showShortToast("Enter mobile number valid")
                    }

                    else -> {
                        hideKeyboard(this@LoginActivity)
                        showLongToast("Login Successfully ${edtEmail.text?.trim()} ${edtPassword.text?.trim()}")
                    }
                }
            }
        }

        //version name
        binding.tvVersionName.text = if (FLAVOR.equals("dev", true)) "Dev : Version : $VERSION_NAME" else "Prod : Version : $VERSION_NAME"
    }

    override fun onResume() {
        super.onResume()
        //showKeyboard(binding.edtEmail, this@LoginActivity)
    }
}