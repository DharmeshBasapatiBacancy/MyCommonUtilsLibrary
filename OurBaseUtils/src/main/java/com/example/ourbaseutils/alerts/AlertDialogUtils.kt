package com.example.ourbaseutils.alerts

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StyleRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showAlertDialog(
    title: String,
    message: String,
    ok: Pair<String,()->Unit>,
    cancel: Pair<String,()->Unit>? = null){

    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(ok.first) { _,_ -> ok.second() }

    cancel?.let{
        builder.setNegativeButton(it.first) { _, _ -> it.second() }
    }

    builder.create().show()
}

fun Context.showMaterialAlertDialog(
    @StyleRes style: Int = 0,
    dialogBuilder: MaterialAlertDialogBuilder.() -> Unit
) {
    MaterialAlertDialogBuilder(this, style)
        .apply {
            setCancelable(false)
            dialogBuilder()
            create()
            show()
        }
}

fun MaterialAlertDialogBuilder.negativeButton(
    text: String = "No",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.positiveButton(
    text: String = "Yes",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.neutralButton(
    text: String = "OK",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNeutralButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}
