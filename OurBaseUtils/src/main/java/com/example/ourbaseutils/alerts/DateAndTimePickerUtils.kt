package com.example.ourbaseutils.alerts

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

fun Context.showTimePickerDialog(onTimeSelected: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val timePickerDialog = TimePickerDialog(this,
        { _, selectedHourOfDay, selectedMinute ->
            onTimeSelected(selectedHourOfDay, selectedMinute)
        }, hourOfDay, minute, true)

    timePickerDialog.show()
}

fun Context.showDatePickerDialog(onDateSelected: (Calendar) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val datePickerDialog = DatePickerDialog(this,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(Calendar.YEAR, selectedYear)
            selectedCalendar.set(Calendar.MONTH, selectedMonth)
            selectedCalendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth)
            onDateSelected(selectedCalendar)
        }, year, month, dayOfMonth)

    datePickerDialog.show()
}