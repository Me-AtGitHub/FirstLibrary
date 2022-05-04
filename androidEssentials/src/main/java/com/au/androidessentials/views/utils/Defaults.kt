package com.au.androidessentials.views.utils

object Defaults {

    const val DEFAULT_INT = 0
    const val DEFAULT_FLOAT = 0.0F

    const val DEFAULT_INDICATOR_FACTOR = 6F

    private const val DEFAULT_MARGIN_HORIZONTAL = 6
    private const val DEFAULT_PADDING_HORIZONTAL = 4

    const val DEFAULT_INDICATOR_HEIGHT = 20F
    const val DEFAULT_INDICATOR_WIDTH = 32F

    val DEFAULT_PADDING_INDICATOR =
        Dimensions(DEFAULT_PADDING_HORIZONTAL, DEFAULT_PADDING_HORIZONTAL, DEFAULT_INT, DEFAULT_INT)
    val DEFAULT_MARGIN_INDICATOR =
        Dimensions(DEFAULT_MARGIN_HORIZONTAL, DEFAULT_MARGIN_HORIZONTAL, DEFAULT_INT, DEFAULT_INT)

    const val POSITION_DEFAULT = -1
    const val START = 0
    const val END = 1
    const val TOP = 2
    const val BOTTOM = 3
    const val CENTER_HORIZONTAL = 4
    const val CENTER_VERTICAL = 5
    const val BELOW = 6
    const val ABOVE = 7
    const val ABOVE_CENTER_HORIZONTAL  = ABOVE or CENTER_HORIZONTAL

}

data class Dimensions(val start: Int, val end: Int, val top: Int, val bottom: Int)
