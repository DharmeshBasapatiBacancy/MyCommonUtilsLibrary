package com.example.ourbaseutils.picture

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.ourbaseutils.alerts.negativeButton
import com.example.ourbaseutils.alerts.positiveButton
import com.example.ourbaseutils.alerts.showMaterialAlertDialog


var resultLauncher: ActivityResultLauncher<Intent>? = null

/**
 * Created by admin on 24/11/23.
 */


fun Context.showSelectionPopup(activity: AppCompatActivity){

    showMaterialAlertDialog {
        setTitle("Selection")
        setMessage("Please choose option to get image")
        positiveButton("Camera") {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(activity,intent,1,null)


        }
        negativeButton("Gallery") {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(activity,Intent.createChooser(intent, "Select Picture"),2,null)

        }
    }
}
