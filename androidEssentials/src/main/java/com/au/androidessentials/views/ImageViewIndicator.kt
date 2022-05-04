package com.au.androidessentials.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import com.au.androidessentials.R
import com.au.androidessentials.views.utils.Defaults.DEFAULT_FLOAT
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INDICATOR_HEIGHT
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INDICATOR_WIDTH
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INT
import com.au.androidessentials.views.utils.Defaults.DEFAULT_MARGIN_INDICATOR
import com.au.androidessentials.views.utils.Defaults.DEFAULT_PADDING_INDICATOR
import com.au.androidessentials.views.utils.Dimensions

class ImageViewIndicator : LinearLayout, ViewPager3.PageChangeListener {

    private val TAG = "ImageViewIndicator"

    private var sizeMeasure = this

    var indicatorCount: Int
    var selectedIndicatorColor: Int
    var unselectedIndicatorColor: Int

    private var currentPage: Int = 0

    var indicatorWidthFactor: Float = DEFAULT_FLOAT
    var indicatorHeightFactor: Float = DEFAULT_FLOAT

    var indicatorWidth: Float = DEFAULT_INDICATOR_WIDTH
    var indicatorHeight: Float = DEFAULT_INDICATOR_HEIGHT
    var imageRadius = DEFAULT_FLOAT
    var viewWidth: Int = DEFAULT_INT
    var viewHeight: Int = DEFAULT_INT

    private var padding = DEFAULT_PADDING_INDICATOR
    private var margin = DEFAULT_MARGIN_INDICATOR

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        DEFAULT_INT
    )

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attributeSet: AttributeSet?, defStyleRes: Int) : super(
        context,
        attributeSet,
        defStyleRes
    ) {
        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ImageViewIndicator,
            DEFAULT_INT,
            DEFAULT_INT
        ).let {

            /* Selected Indicator Color */
            selectedIndicatorColor =
                it.getResourceId(R.styleable.ImageViewIndicator_selectedIndicatorColor, DEFAULT_INT)

            /* Unselected Indicator Color */
            unselectedIndicatorColor = it.getResourceId(
                R.styleable.ImageViewIndicator_unselectedIndicatorColor,
                DEFAULT_INT
            )

            /* No of Indicator to display */
            indicatorCount =
                it.getInteger(R.styleable.ImageViewIndicator_indicatorCount, DEFAULT_INT)

            /* Radius of  each Indicator */
            imageRadius =
                it.getDimension(R.styleable.ImageViewIndicator_indicatorImageRadius, DEFAULT_FLOAT)

            /* Width of each indicator */
            indicatorWidth = it.getDimension(
                R.styleable.ImageViewIndicator_indicatorWidth,
                DEFAULT_INDICATOR_WIDTH
            )

            /* Height of each Indicator */
            indicatorHeight = it.getDimension(
                R.styleable.ImageViewIndicator_indicatorHeight,
                DEFAULT_INDICATOR_HEIGHT
            )

            /* Indicator width factor */
            indicatorWidthFactor =
                it.getFloat(R.styleable.ImageViewIndicator_indicatorWidthFactor, DEFAULT_FLOAT)

            /* Indicator Height factor */
            indicatorHeightFactor =
                it.getFloat(R.styleable.ImageViewIndicator_indicatorHeightFactor, DEFAULT_FLOAT)

            it.recycle()
            init()
        }
    }


    private fun init() {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(viewWidth, viewHeight).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
        for (i in 0 until indicatorCount) {
            if (i == 0) {
                addIndicatorView(
                    indicatorWidth.toInt(),
                    indicatorHeight.toInt(),
                    selectedIndicatorColor
                )
            } else
                addIndicatorView(
                    (indicatorWidth - indicatorWidthFactor).toInt(),
                    (indicatorHeight - indicatorHeightFactor).toInt(),
                    unselectedIndicatorColor
                )
        }
        postInvalidate()
    }

    private fun addIndicatorView(
        width: Int,
        height: Int,
        color: Int,
        mMargin: Dimensions? = margin,
        mPadding: Dimensions? = padding
    ) {
        val imageViewBuilder = AppImageView(context).Builder().apply {
            this.setLayoutParams(FrameLayout.LayoutParams(width, height).apply {
                gravity = Gravity.CENTER_VERTICAL
            })
            setColor(color)
            setImageRadius(imageRadius)
        }
        this.addView(imageViewBuilder.build(), LayoutParams(width, height).apply {
            gravity = Gravity.CENTER_VERTICAL
            setMargins(mPadding!!.start, mPadding.top, mPadding.end, mPadding.bottom)
            setPadding(mMargin!!.start, mMargin.top, mMargin.end, mMargin.bottom)
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (widthMode) {
            // wrap content //
            MeasureSpec.AT_MOST -> viewWidth = DEFAULT_INT
            MeasureSpec.UNSPECIFIED -> viewWidth = widthSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewWidth = widthSize
        }

        when (heightMode) {
            // wrap content //
            MeasureSpec.AT_MOST -> viewHeight = DEFAULT_INT
            MeasureSpec.UNSPECIFIED -> viewHeight = heightSize
            // exact width height //
            MeasureSpec.EXACTLY -> viewHeight = heightSize
        }

        /* for child width */
        val horizontalPaddingCount = (padding.start + padding.end) * indicatorCount
        val horizontalMarginCount = (margin.start + margin.end) * indicatorCount
        val viewWidthCount =
            indicatorWidth + ((indicatorCount - 1) * (indicatorWidth - indicatorWidthFactor))
        val totalViewWidth = -horizontalPaddingCount + horizontalMarginCount + viewWidthCount

        /* for child height */
        val verticalPaddingCount = padding.top + padding.bottom
        val verticalMarginCount = margin.top + margin.bottom
        val totalViewHeight = verticalMarginCount + verticalPaddingCount + indicatorHeight

        if (viewWidth < countNeededWidth()) viewWidth = countNeededWidth()
        if (viewHeight < totalViewHeight.toInt()) viewHeight += (totalViewHeight - viewHeight).toInt()

        setMeasuredDimension(viewWidth, viewHeight)
    }

    private fun countNeededWidth(): Int {
        var totalWidth = 0
        for (i in 0 until childCount) {
            getChildAt(i).apply {
                totalWidth += measuredWidth + marginStart + marginEnd + paddingStart + paddingEnd
            }
        }
        return totalWidth + 8
    }

    private fun countNeededHeight() {
        var totalHeight = 0

    }

    private fun drawIndicators(position: Int, totalItem: Int) {
        Log.d(TAG, "drawIndicators: ")
        for (i in 0 until totalItem) {
            if (i != position) {
                Log.d(TAG, "drawIndicators: ${i == position}")
                addIndicatorView(
                    (indicatorWidth - indicatorWidthFactor).toInt(),
                    (indicatorHeight - indicatorHeightFactor).toInt(),
                    unselectedIndicatorColor
                )
            } else {
                Log.d(TAG, "drawIndicators: ${i == position}")
                addIndicatorView(
                    indicatorWidth.toInt(),
                    indicatorHeight.toInt(),
                    selectedIndicatorColor
                )
            }
        }
    }


    override fun onPageSelected(position: Int, totalItem: Int) {
        Log.d(TAG, "onPageSelected: $position")
        currentPage = position
        removeAllViews()
        drawIndicators(position, totalItem)
        postInvalidate()
    }



    override fun onPageScroll(
        currentPosition: Int,
        nextPosition: Int,
        positionOffset: Float
    ) {
//        Log.d(TAG, "onPageScroll:$currentPosition -- to -- $nextPosition  $positionOffset")
        try {
            val viewOne = getChildAt(currentPosition) as AppImageView
            val viewTwo = getChildAt(nextPosition) as AppImageView
            if (nextPosition > currentPosition)
                animateViews(viewOne, viewTwo, positionOffset)
            else
                animateViews(viewOne, viewTwo, 1 - positionOffset)
        } catch (e: Exception) {
        }
    }


    private fun animateViews(
        selectedView: AppImageView,
        unselectedView: AppImageView,
        offset: Float
    ) {

        val selectedWidth = indicatorWidth
        val unselectedWidth = indicatorWidth - indicatorWidthFactor

        val selectedHeight = indicatorHeight
        val unselectedHeight = indicatorHeight - indicatorHeightFactor

        val heightDifference = (selectedHeight - unselectedHeight) * offset
        val widthDifference = (selectedWidth - unselectedWidth) * offset


        selectedView.setDimensions(
            selectedWidth - widthDifference,
            selectedHeight - heightDifference
        )
        unselectedView.setDimensions(
            unselectedWidth + widthDifference,
            unselectedHeight + heightDifference
        )

    }

}

