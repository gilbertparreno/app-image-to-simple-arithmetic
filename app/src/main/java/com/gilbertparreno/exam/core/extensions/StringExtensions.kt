package com.gilbertparreno.exam.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.generateFileNameWithDate(type: String): String {
    val currentDate = SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH).format(Date())
    return "$this-%s-$type".format(currentDate)
}

fun String.getFirstArithmeticExpression(): String? {
    val regex = "\\d*\\.?\\d+[/+\\-*]\\d*\\.?\\d+".toRegex()
    val test = regex.findAll(this.replace(" ", ""))
    return test.firstOrNull()?.value
}

fun String.removeDigits(): String {
    return this.replace("\\d*\\.?\\d+".toRegex(), "")
}