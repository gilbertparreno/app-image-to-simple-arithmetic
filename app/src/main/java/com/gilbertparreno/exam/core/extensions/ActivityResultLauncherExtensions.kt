package com.gilbertparreno.exam.core.extensions

import android.content.Context
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import java.io.File

fun ActivityResultLauncher<Uri>.launchWithUri(context: Context) {
    val filesDir = context.filesDir ?: return
    val file = File(filesDir, "image-from-camera".generateFileNameWithDate(".jpg"))
    this.launch(context.getFileProviderUri(file))
}