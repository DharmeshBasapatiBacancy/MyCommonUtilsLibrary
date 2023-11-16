package com.example.ourbaseutils.logging

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.showShortToast(message: String): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_SHORT).apply { show() }
}

fun Context.showLongToast(message: String): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_LONG).apply { show() }
}

fun Any.showVLog(log: String) = Log.v(this::class.java.simpleName, log)

fun Any.showELog(log: String) = Log.e(this::class.java.simpleName, log)

fun Any.showDLog(log: String) = Log.d(this::class.java.simpleName, log)

fun Any.showILog(log: String) = Log.i(this::class.java.simpleName, log)

fun Any.showWLog(log: String) = Log.w(this::class.java.simpleName, log)