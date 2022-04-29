package com.au.androidessentials.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.au.androidessentials.R
import com.au.androidessentials.databinding.LayoutViewPagerWithIndicatorBinding
import com.au.androidessentials.views.utils.Defaults.DEFAULT_FLOAT
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INDICATOR_FACTOR
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INT
import com.au.androidessentials.views.utils.Defaults.POSITION_CENTER

class ViewPager3 : LinearLayout {

    private var previousOffset: Float = DEFAULT_FLOAT

    private lateinit var adapter: FragmentStateAdapter

    private var indicatorRadius: Float = DEFAULT_FLOAT

    private var colorSelectedIndicator: Int = DEFAULT_INT
    private var colorUnselectedIndicator: Int = DEFAULT_INT

    private var indicatorWidth: Float = DEFAULT_FLOAT
    private var indicatorHeight: Float = DEFAULT_FLOAT

    private var indicatorWidthFactor:Float = DEFAULT_FLOAT
    private var indicatorHeightFactor:Float = DEFAULT_FLOAT

    private var indicatorPosition: Int = POSITION_CENTER

    private lateinit var indicatorView: ImageViewIndicator
    private lateinit var binding: LayoutViewPagerWithIndicatorBinding
    private var layoutParam = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        DEFAULT_INT
    )

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        context.obtainStyledAttributes(attributeSet, R.styleable.ViewPager3, defStyle, DEFAULT_INT)
            .apply {

                //TODO Indicator Properties

                /* radius of indicator */
                indicatorRadius =
                    getDimension(R.styleable.ViewPager3_indicatorRadius, DEFAULT_FLOAT)

                /*resource of selected indicator */
                colorSelectedIndicator =
                    getResourceId(R.styleable.ViewPager3_colorSelectedIndicator, DEFAULT_INT)

                /*colour od unselected indicator */
                colorUnselectedIndicator =
                    getResourceId(R.styleable.ViewPager3_colorUnselectedIndicator, DEFAULT_INT)

                /*  height of indicator */
                indicatorHeight =
                    getDimension(R.styleable.ViewPager3_indicatorViewHeight, DEFAULT_FLOAT)

                /* width of indicator */
                indicatorWidth =
                    getDimension(R.styleable.ViewPager3_indicatorViewWidth, DEFAULT_FLOAT)

                /* indicator Size Factor */
                indicatorHeightFactor = getFloat(R.styleable.ViewPager3_heightFactorIndicator, DEFAULT_FLOAT)

                /* indicator width factor */
                indicatorWidthFactor = getFloat(R.styleable.ViewPager3_widthFactorIndicator, DEFAULT_FLOAT)

                /* binding view */
                binding = LayoutViewPagerWithIndicatorBinding.inflate(
                    LayoutInflater.from(context),
                    this@ViewPager3
                )

                recycle()
                init()
            }
    }

    private fun init() {
        indicatorView = ImageViewIndicator(context)
        binding.apply {
            orientation = VERTICAL
            addView(indicatorView,1,layoutParam.apply {
                setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
            })

            /* Setting indicator view's  properties */
            indicatorView.indicatorHeight = indicatorHeight
            indicatorView.indicatorWidth = indicatorWidth
            indicatorView.selectedIndicatorColor = colorSelectedIndicator
            indicatorView.unselectedIndicatorColor = colorUnselectedIndicator
            indicatorView.indicatorWidthFactor = indicatorWidthFactor
            indicatorView.indicatorHeightFactor = indicatorHeightFactor
            indicatorView.imageRadius = indicatorRadius

            binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
        }
        postInvalidate()
    }

    fun setAdapter(adapter: FragmentStateAdapter) {
        this.adapter = adapter
        indicatorView.indicatorCount = adapter.itemCount
        binding.viewPager.adapter = adapter
    }

    fun getAdapter() = this.adapter

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            indicatorView.onPageSelected(position, adapter.itemCount)
        }

        //TODO use this function to show animation
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            indicatorView.onPageScroll(position, positionOffset)
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    }

    interface PageChangeListener {
        fun onPageSelected(position: Int, totalItem: Int)
        fun onPageScroll(position:Int,positionOffset: Float)
    }

}