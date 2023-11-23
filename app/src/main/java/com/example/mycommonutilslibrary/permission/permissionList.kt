package com.example.mycommonutilslibrary.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by admin on 22/11/23.
 */


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
var storage_permissions_33 = arrayOf(
    Manifest.permission.READ_MEDIA_IMAGES,
    //Manifest.permission.READ_MEDIA_AUDIO,
    //Manifest.permission.READ_MEDIA_VIDEO
)

var storage_permissions = arrayOf(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
)

fun permissions(): Array<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        storage_permissions_33
    } else {
        storage_permissions
    }
}