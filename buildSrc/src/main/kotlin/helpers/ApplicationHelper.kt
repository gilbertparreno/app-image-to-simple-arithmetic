package helpers

object ApplicationHelper {
    fun renameApkFileName(baseName: String): String {
        val apkFileNameFormat = "app-%s.apk"
        val apkFileName = apkFileNameFormat.format(baseName.substring(0, baseName.indexOf("-")))
            .replace("_", "-")
        return when {
            baseName.contains("camera") -> {
                apkFileName.replace("camera", "built-in-camera")
            }
            else -> apkFileName
        }
    }
}