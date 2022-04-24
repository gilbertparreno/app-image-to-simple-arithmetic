package com.gilbertparreno.exam.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.gilbertparreno.exam.BuildConfig
import java.io.File

fun Context.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dp(value: Float): Float = (value * resources.displayMetrics.density)
fun Context.sp(value: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

fun Context.getFileProviderUri(file: File): Uri {
    return FileProvider.getUriForFile(
        this,
        "${BuildConfig.APPLICATION_ID}.provider",
        file
    )
}