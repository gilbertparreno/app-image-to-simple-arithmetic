package enums

enum class ThemeColor(val color: String) {
    RED("red"), GREEN("green");

    fun getColor(color: String): ThemeColor {
        return values().first { it.color == color }
    }
}