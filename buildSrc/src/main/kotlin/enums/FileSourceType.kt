package enums

enum class FileSourceType(val fileSourceValue: String) {
    BUILT_IN_CAMERA("built-in-camera"),
    FILESYSTEM("filesystem");

    fun getType(fileSourceValue: String) : FileSourceType{
        return values().first { it.fileSourceValue == fileSourceValue }
    }
}