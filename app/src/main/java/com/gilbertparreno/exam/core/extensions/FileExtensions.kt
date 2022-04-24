package com.gilbertparreno.exam.core.extensions

import java.io.File

fun File.getLastModifiedFile() : File? {
    if (!isDirectory) return null
    return listFiles().maxByOrNull { it.lastModified() }
}