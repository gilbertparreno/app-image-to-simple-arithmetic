package com.gilbertparreno.exam.core.entities

sealed class FeatureType(open val feature: String) {
    object Camera : FeatureType("camera")
    object FileSystem : FeatureType("filesystem")

    companion object {
        fun getType(feature: String) : FeatureType? {
            return when (feature) {
                "camera" -> Camera
                "filesystem" -> FileSystem
                else -> null
            }
        }
    }
}