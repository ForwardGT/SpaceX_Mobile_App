package com.example.spacexmobileapp.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.mapTimestampToDate(): String {
    val date = Date((this - 25200) * 1000)
    return SimpleDateFormat("d MMMM yyyy, hh:mm:ss", Locale.getDefault()).format(date)
}