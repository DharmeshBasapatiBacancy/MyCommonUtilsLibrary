package com.example.ourbaseutils.permission

import androidx.activity.result.ActivityResult

interface PermissionHandler {
    fun onPermissionResult(activityResult: ActivityResult?)
    fun showPermissionRationaleDialog(permission: String)
}
