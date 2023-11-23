package com.example.ourbaseutils.permissions

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat



/**
 * Created by admin on 21/11/23.
 */
class HandlePermission(
    val permissions: Array<String>,
    var mainActivity: Activity,
    val permissionCode: Int
) : AppCompatActivity() {



    private var isNotFirstTime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = this
        pickImage()
    }

    fun pickImage() {

        if (checkPermissions()){
            //Permission Granted
            Log.d("HandlePermission","Permission Granted")
        }else {
            // Request for permission
            requestPermission()

        }
    }


    private fun checkPermissions(): Boolean {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            !(ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        }else {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
        }

    }

    private fun requestPermission() {

        ActivityCompat.requestPermissions(
            mainActivity, permissions,
            permissionCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            // .setNegativeButton("Cancel", null)
            .setCancelable(false)
            .create()
            .show()
    }

    private fun startAppSettingsConfigActivity() {
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        i.data = Uri.parse("package:$packageName")
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        startActivity(i)
    }


    object PermissionHandlerCallback {
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {

       /*     when (requestCode) {
                permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("HandlePermission","Permission Granted")

                    // main logic
                } else {
                    Log.d("HandlePermission","Permission Denied")


                    if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        if (isNotFirstTime){
                            startAppSettingsConfigActivity()

                        }else {

                            isNotFirstTime = true
                            showMessageOKCancel("You need to allow access permissions"
                            ) { _, _ ->
                                requestPermission()
                            }
                        }
                    }
                }
            }*/
        }
    }


}