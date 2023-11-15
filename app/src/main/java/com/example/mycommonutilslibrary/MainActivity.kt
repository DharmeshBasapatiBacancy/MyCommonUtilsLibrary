package com.example.mycommonutilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.mycommonutilslibrary.databinding.ActivityMainBinding
import com.example.ourbaseutils.api.Resource
import com.example.ourbaseutils.common.NetworkUtil
import com.example.ourbaseutils.logging.showLongToast
import com.example.ourbaseutils.logging.showShortToast
import com.example.ourbaseutils.views.visible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    }
}