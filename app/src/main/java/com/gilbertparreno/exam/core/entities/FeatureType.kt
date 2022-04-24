package com.gilbertparreno.exam.core.entities

sealed class FeatureType(open val feature: String) {
    object Camera : FeatureType("camera")
    object FileSystem : FeatureType("file-system")

    companion object {
        fun getType(feature: String) : FeatureType? {
            return when (feature) {
                "camera" -> Camera
                "file-system" -> FileSystem
                else -> null
            }
        }
    }
}