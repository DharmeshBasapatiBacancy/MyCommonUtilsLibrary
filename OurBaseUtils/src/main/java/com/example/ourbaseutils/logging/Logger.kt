package com.example.ourbaseutils.logging

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: String): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_SHORT).apply { show() }
}

fun Context.showLongToast(message: String): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_LONG).apply { show() }
}