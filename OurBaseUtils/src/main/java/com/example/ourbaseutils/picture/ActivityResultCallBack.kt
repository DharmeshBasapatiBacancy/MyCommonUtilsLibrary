package com.example.ourbaseutils.picture

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by admin on 24/11/23.
 */
class ActivityResultCallBack : AppCompatActivity() {


    fun Context.getResult() : Intent? {
        var data: Intent? = null
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                data = result.data

            }
        }

        return data

    }

    fun Context.sendIntentRequest(){

        var data: Intent? = null
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                data = result.data

            }
        }

        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)

    }
}
