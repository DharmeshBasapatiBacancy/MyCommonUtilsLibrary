package com.example.ourbaseutils.views

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.ourbaseutils.logging.showDLog

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Activity.hideKeyboard(activity: Activity) {
    try {

        val view = activity.currentFocus
        if (view != null) {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } catch (e: Exception) {
        showDLog(e.toString())
    }
}

fun Activity.showKeyboard(editText: EditText, activity: Activity) {

    try {

        editText.requestFocus()
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

    } catch (e: Exception) {
        e.printStackTrace()
    }
}