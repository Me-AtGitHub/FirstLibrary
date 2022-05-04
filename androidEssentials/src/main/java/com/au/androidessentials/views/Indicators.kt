package com.au.androidessentials.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.au.androidessentials.R

class Indicators : View,ViewPager3.PageChangeListener {

    private val TAG = "Indicators"
    private var canvas: Canvas? = null
    private lateinit var paint: Paint
    private var indicatorSize: Float = 16F
    private var indicatorColor: Int = android.R.color.holo_red_dark
    private var viewHeight = 80
    private var viewWidth = 160

    constructor(context: Context):this(context,null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attributeSet: AttributeSet?, style: Int) : super(
        context,
        attributeSet,
        style
    ) {
        context.obtainStyledAttributes(attributeSet, R.styleable.Indicators, style, 0).apply {
            indicatorSize = getDimension(R.styleable.Indicators_sizeIndicator, 16F)
            indicatorColor = getColor(R.styleable.Indicators_colorIndicator, android.R.color.holo_red_dark)
            recycle()
            init()
        }
    }

    private fun init(){
        alpha = 0.5F
    }
    fun setIndicatorSize(size:Float){
        indicatorSize = size
        postInvalidate()
    }

    fun setViewHeight(height:Float){
        viewHeight = height.toInt()
        postInvalidate()
    }

    fun setViewWidth(width:Float){
        viewWidth = width.toInt()
        postInvalidate()
    }

    fun setIndicatorColor(color:Int){
        indicatorColor = color
        postInvalidate()
    }

    fun canDrawCircle():Boolean{
        return canvas!=null
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = context.getColor(indicatorColor)
        }
        drawCircle()
    }

    private fun drawCircleWithAnimation(isLeft:Boolean,offset:Float){
        val xWidth = width / 3
        val yHeight = height

        for (i in 0..2) {
            var size = indicatorSize-offset
            if(i!=1) size = (size / 1.5).toFloat()+offset
            canvas?.drawCircle(
                (xWidth / 2 + xWidth * i).toFloat(),
                (yHeight / 2).toFloat(),
                size,
                paint
            )
        }

        postInvalidate()
    }
    private fun drawCircle() {
        val xWidth = width / 3
        val yHeight = height

        for (i in 0..2) {
            var size = indicatorSize
            if (i != 1) size = (size / 1.5).toFloat()
            canvas?.drawCircle(
                (xWidth / 2 + xWidth * i).toFloat(),
                (yHeight / 2).toFloat(),
                size,
                paint
            )
        }

        postInvalidate()
    }

    private fun drawCircle(position: Int, endPosition: Int) {

        val xWidth = width / 3
        val yHeight = height
        for (i in 0..2) {
            when {
                position == 0 && i == 0 -> {}
                position == endPosition && i == 2 -> {}
                else ->
                    if (i == 1)
                        canvas?.drawCircle(
                            (xWidth / 2 + xWidth * i).toFloat(),
                            (yHeight / 2).toFloat(),
                            indicatorSize,
                            paint
                        )
                    else canvas?.drawCircle(
                        (xWidth / 2 + xWidth * i).toFloat(), (yHeight / 2).toFloat(),
                        (indicatorSize / 1.3).toFloat(), paint
                    )
            }
        }
        postInvalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (widthMode) {
            MeasureSpec.AT_MOST -> viewWidth = 160
            MeasureSpec.UNSPECIFIED -> viewWidth = widthSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewWidth = widthSize
        }

        when (heightMode) {
            MeasureSpec.AT_MOST -> viewHeight = 80
            MeasureSpec.UNSPECIFIED -> viewHeight = heightSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewHeight = heightSize
        }

        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onPageSelected(position: Int, totalItem: Int) {
        drawCircle(position,totalItem)
    }

    override fun onPageScroll(currentPosition: Int, nextPosition: Int, positionOffset: Float) {
    }


}