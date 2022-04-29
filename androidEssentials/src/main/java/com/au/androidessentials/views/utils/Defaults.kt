package com.au.androidessentials.views.utils

object Defaults {

     const val DEFAULT_INT = 0
     const val DEFAULT_FLOAT = 0.0F

     const val DEFAULT_INDICATOR_FACTOR = 6F

     private const val DEFAULT_MARGIN_HORIZONTAL = 6
     private const val DEFAULT_PADDING_HORIZONTAL = 4

     const val DEFAULT_INDICATOR_HEIGHT = 20F
     const val DEFAULT_INDICATOR_WIDTH = 32F

     val DEFAULT_PADDING_INDICATOR = Dimensions(DEFAULT_PADDING_HORIZONTAL, DEFAULT_PADDING_HORIZONTAL, DEFAULT_INT, DEFAULT_INT)
     val DEFAULT_MARGIN_INDICATOR = Dimensions(DEFAULT_MARGIN_HORIZONTAL, DEFAULT_MARGIN_HORIZONTAL, DEFAULT_INT, DEFAULT_INT)
     const val POSITION_CENTER = 0
     const val POSITION_START = 1
     const val POSITION_END = 2
     const val POSITION_TOP = 3



}
data class Dimensions(val start: Int, val end: Int, val top: Int, val bottom: Int)