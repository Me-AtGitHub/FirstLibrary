package com.au.androidessentials.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.au.androidessentials.R
import com.au.androidessentials.databinding.LayoutCirculerImageBinding
import com.au.androidessentials.views.utils.Dimensions

class AppImageView : CardView {

    private val TAG = "ImageView"
    private var binding: LayoutCirculerImageBinding
    private var imageRadius: Float = 0.0F
    private var imageElevation: Float = 0F
    private var mBackgroundColor: Int = 0
    private var mResources = 0
    private var viewHeight = 80
    private var viewWidth = 80
    private var mLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    private var padding: Dimensions = Dimensions(0, 0, 0, 0)
    private var margin: Dimensions = Dimensions(0, 0, 0, 0)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    @SuppressLint("UseCompatLoadingForDrawables")
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        context.obtainStyledAttributes(attributeSet, R.styleable.AppImageView, defStyleAttr, 0)
            .apply {
                mResources = getResourceId(R.styleable.AppImageView_resource, 0)
                binding = LayoutCirculerImageBinding.inflate(
                    LayoutInflater.from(context),
                    this@AppImageView
                )
                imageRadius = getDimension(R.styleable.AppImageView_imageRadius, 0.0F)
                mBackgroundColor = getColor(R.styleable.AppImageView_backgroundColor, 0)
                imageElevation = getDimension(R.styleable.AppImageView_imageElevation, 0F)
                init()
                recycle()
            }
    }


    private fun init() {

        this.setPadding(padding.start, padding.top, padding.end, padding.bottom)

        this.layoutParams = LayoutParams(viewWidth, viewHeight).apply {
            setMargins(margin.start, margin.top, margin.end, margin.bottom)
        }

        radius = imageRadius
        when {
            mResources != 0 -> binding.imageView.setBackgroundResource(mResources)
            mBackgroundColor != 0 -> binding.imageView.setBackgroundResource(mBackgroundColor)
        }
        this.elevation = imageElevation
        this.cardElevation = imageElevation
        postInvalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (widthMode) {
            // wrap content //
            MeasureSpec.AT_MOST -> viewWidth = 80
            MeasureSpec.UNSPECIFIED -> viewWidth = widthSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewWidth = widthSize
        }

        when (heightMode) {
            // wrap content //
            MeasureSpec.AT_MOST -> viewHeight = 80
            MeasureSpec.UNSPECIFIED -> viewHeight = heightSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewHeight = heightSize
        }
        setMeasuredDimension(viewWidth, viewHeight)
        postInvalidate()
    }

    fun setDimensions(width:Float,height:Float){
        Log.d(TAG, "setDimensions: width-> $width , height -> $height ")
        layoutParams.width = width.toInt()
        layoutParams.height = height.toInt()
        requestLayout()
    }
    
    inner class Builder {

        fun setLayoutParams(layoutParams: LayoutParams): Builder {
            viewHeight = layoutParams.height
            viewWidth = layoutParams.width
            mLayoutParams = layoutParams
            return this
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun setColor(color: Int): Builder {
            mResources = 0
            mBackgroundColor = color
            return this
        }

        fun setImageRadius(shape: Float): Builder {
            imageRadius = shape
            return this
        }

        fun setMargin(start: Int, end: Int, top: Int, bottom: Int): Builder {
            this@AppImageView.margin = Dimensions(start, end, top, bottom)
            return this
        }

        fun setPadding(start: Int, end: Int, top: Int, bottom: Int): Builder {
            this@AppImageView.padding = Dimensions(start, end, top, bottom)
            return this
        }

        fun build(): AppImageView {
            init()
            return this@AppImageView
        }

    }

}