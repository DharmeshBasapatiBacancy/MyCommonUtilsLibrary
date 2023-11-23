package com.example.ourbaseutils.permissions

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.ourbaseutils.Constant
import com.example.ourbaseutils.sharedPreference.Prefs

/**
 * Created by admin on 22/11/23.
 */


lateinit var permissionListener : Permission



class Permis(
    val permissions: Array<String>,
    val permissionCode: Int,
    val activity: Activity
)  : ActivityCompat() {


    fun setPermission(perm : Permission){
        permissionListener = perm

        Action.requestPermission(activity,permissions,permissionCode)

    }
}

object PermissionHandlerCallback {

    fun onRequestPermissionsResult(
        mainActivity: Activity,
        permissionCode: Int,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        isShowCustomPopup : Boolean = false
    ) {


        when (requestCode) {
            permissionCode -> {

                Log.d("HandlePermission","Permission size = ${grantResults.size}")

                for (i in grantResults) {

                    if (grantResults.isNotEmpty() && grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        Log.d("HandlePermission","Permission Granted")
                        permissionListener.onGranted()
                    }else {


                        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                            if (Prefs[Constant.ALLOWED]){

                                //Permanent Denied
                                Action.startAppSettingsConfigActivity(mainActivity)
                                permissionListener.onDeniedPermanently()

                            }else {

                                permissionListener.onDenied()
                                Action.showMessageOKCancel(mainActivity,"You need to allow access permissions",
                                ) { _, _ ->
                                    Action.requestPermission(mainActivity,permissions,permissionCode)
                                    Prefs[Constant.ALLOWED] = true
                                }
                            }
                        }
                        break
                    }
                }
            }
        }
    }
}


object Action {

     fun showMessageOKCancel(activity: Activity,message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            // .setNegativeButton("Cancel", null)
            .setCancelable(false)
            .create()
            .show()
    }

     fun startAppSettingsConfigActivity(activity: Activity) {
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        i.data = Uri.parse("package:${activity.packageName}")
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        activity.startActivity(i)
    }

    fun requestPermission(
        mainActivity: Activity,
        permissions: Array<out String>,
        permissionCode: Int
    ) {

        ActivityCompat.requestPermissions(
            mainActivity, permissions,
            permissionCode
        )
    }


}

interface Permission {

    fun onGranted()
    fun onDenied()
    fun onDeniedPermanently()

}