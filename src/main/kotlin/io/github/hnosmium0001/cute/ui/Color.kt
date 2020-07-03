package io.github.hnosmium0001.cute.ui

typealias Color = RGBColor

inline class RGBColor(
    // 32-bit integer in ARGB format
    val hex: Int
) {
    val red: Int get() = (hex shr 16) and 0xff
    val green: Int get() = (hex shr 8) and 0xff
    val blue: Int get() = hex and 0xff
    val alpha: Int get() = (hex shr 24) and 0xff

    val normalizedRed: Float get() = red.toFloat() / 255.0F
    val normalizedGreen: Float get() = green.toFloat() / 255.0F
    val normalizedBlue: Float get() = blue.toFloat() / 255.0F
    val normalizedAlpha: Float get() = alpha.toFloat() / 255.0F

    constructor(red: Int, green: Int, blue: Int, alpha: Int)
        : this((alpha shl 24) or (red shl 16) or (green shl 8) or (blue))

    constructor(red: Float, green: Float, blue: Float, alpha: Float) : this(
        red = (red * 255.0F).toInt(),
        green = (green * 255.0F).toInt(),
        blue = (blue * 255.0F).toInt(),
        alpha = (alpha * 255.0F).toInt()
    )

    // Adapted from https://stackoverflow.com/questions/3018313/algorithm-to-convert-rgb-to-hsv-and-hsv-to-rgb-in-range-0-255-for-both
    fun asHSV(): HSVColor {
        val min = minOf(normalizedRed, normalizedGreen, normalizedBlue)
        val max = maxOf(normalizedRed, normalizedGreen, normalizedBlue)
        val delta = max - min

        if (delta < 0.00001F) {
            return HSVColor(0.0F, 0.0F, max, normalizedAlpha)
        }
        if (max > 0.0F) {
            var h: Float = when {
                // Between cyan & yellow
                red >= max -> (green - blue) / delta
                // Between magenta & cyan
                green >= max -> 2.0F + (green - red) / delta
                // Between magenta & cyan
                else -> 4.0F + (red - green) / delta
            } * 60.0F // Convert to degrees

            if (h < 0.0F) {
                h += 360.0F
            }

            return HSVColor(h, delta / max, max, normalizedAlpha)
        } else {
            return HSVColor(Float.NaN, 0.0F, max, normalizedAlpha)
        }
    }
}

class HSVColor(
    // Angle in degrees (between 0 and 360)
    val hue: Float,
    // A fraction between 0 and 1
    val saturation: Float,
    // A fraction between 0 and 1
    val value: Float,
    // A fraction between 0 and 1
    val alpha: Float
) {
    // Adapted from https://stackoverflow.com/questions/3018313/algorithm-to-convert-rgb-to-hsv-and-hsv-to-rgb-in-range-0-255-for-both
    fun asRGB(): RGBColor {
        val hh = (if (hue >= 360.0F) 0.0F else hue) / 60.0F
        val i = hh.toLong()
        val ff = hh - i
        val p = value * (1.0F - saturation)
        val q = value * (1.0F - (saturation * ff))
        val t = value * (1.0F - (saturation * (1.0F - ff)))

        return when (i) {
            0L -> RGBColor(value, t, p, alpha)
            1L -> RGBColor(q, value, p, alpha)
            2L -> RGBColor(p, value, t, alpha)
            3L -> RGBColor(p, q, value, alpha)
            4L -> RGBColor(t, p, value, alpha)
            else -> RGBColor(value, p, q, alpha)
        }
    }
}