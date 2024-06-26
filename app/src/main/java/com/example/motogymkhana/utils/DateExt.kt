package com.example.motogymkhana.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_EEE_d_MMMM_HH_mm = "EEE, d MMMM HH:mm"
const val FORMAT_EEE_HH_mm = "EEE, HH:mm"
const val FORMAT_HH_mm = "HH:mm"
const val FORMAT_dd_MM_yy_HH_mm = "dd-MM-yy HH:mm"
const val FORMAT_dd_MM_yyyy = "dd.MM.yyyy"
const val FORMAT_mm_ss_ss = "mm:ss.SS"


fun String.toTime(pattern: String) =
    SimpleDateFormat(pattern, Locale.getDefault()).parse(this)?.time ?: 0L

fun Long.format(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this * 1000)

@SuppressLint("SimpleDateFormat")
fun Double.millisecondToMinutes(): String =
    SimpleDateFormat(FORMAT_mm_ss_ss).format(this)