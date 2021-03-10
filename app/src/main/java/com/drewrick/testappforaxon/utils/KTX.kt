package com.drewrick.testappforaxon.utils

import android.content.Context
import com.drewrick.testappforaxon.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun String.beautyDate(): String {
    val date: Date?
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    date = simpleDateFormat.parse(this)
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}