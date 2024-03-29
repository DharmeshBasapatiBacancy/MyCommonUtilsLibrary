package com.example.mycommonutilslibrary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.mycommonutilslibrary.databinding.ActivityMainBinding
import com.example.mycommonutilslibrary.permission.UserPermissionActivity
import com.example.mycommonutilslibrary.permission.UserPermissionFragment
import com.example.ourbaseutils.alerts.negativeButton
import com.example.ourbaseutils.alerts.positiveButton
import com.example.ourbaseutils.alerts.showAlertDialog
import com.example.ourbaseutils.alerts.showDatePickerDialog
import com.example.ourbaseutils.alerts.showMaterialAlertDialog
import com.example.ourbaseutils.alerts.showTimePickerDialog
import com.example.ourbaseutils.api.Resource
import com.example.ourbaseutils.common.NetworkUtil
import com.example.ourbaseutils.logging.showELog
import com.example.ourbaseutils.logging.showLongToast
import com.example.ourbaseutils.logging.showShortToast
import com.example.ourbaseutils.sharedPreference.Prefs
import com.example.ourbaseutils.views.visible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Prefs.init(this)

        //Using View Extensions
        binding.tvSample.visible()

        //Using Resource for api calling
        val apiResponse = MutableLiveData<Resource<String>>()
        apiResponse.value = Resource.Success("Some API Response")

        //Using Logger extension function to show toast
        showShortToast("This is a short message !!!")

        //Using NetworkUtil for checking internet connection
        if(NetworkUtil.hasInternetConnection(this)){
            showLongToast("You are connected to internet !!!")
        } else {
            showLongToast("You are NOT connected to internet !!!")
        }

        //Using shared preferences set and get helper methods
        Prefs["NAME"] = "John Doe"
        showELog("Shared Pref Value = ${Prefs["NAME", ""]}")

        binding.btnShowSimpleAlertDialog.setOnClickListener {
            //Using alert dialog extension function
            showAlertDialog(
                "Title",
                "Message",
                "Yes" to { onDialogActionClicked("Yes") },
                "No" to { onDialogActionClicked("No") })

        }

        binding.btnShowMaterialAlertDialog.setOnClickListener {
            //Using alert dialog extension function
            showMaterialAlertDialog {
                setTitle("Confirm")
                setMessage("Are you sure you want to delete this item?")
                positiveButton("Ok") { onDialogActionClicked("Ok") }
                negativeButton("Cancel") { onDialogActionClicked("Cancel") }
            }
        }

        binding.btnShowDatePicker.setOnClickListener {
            showDatePickerDialog {
                showLongToast("Date = ${it.time}")
            }
        }

        binding.btnShowTimePicker.setOnClickListener {
            showTimePickerDialog { hour, minute ->
                val selectedTime = String.format("%02d:%02d", hour, minute)
                showLongToast("Time = $selectedTime")
            }
        }

        binding.btnLoginPage.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnUserPermissionPage.setOnClickListener {
            startActivity(Intent(this, UserPermissionActivity::class.java))

        }

    }

    private fun onDialogActionClicked(message: String) {
        showShortToast("$message Clicked")
    }
}