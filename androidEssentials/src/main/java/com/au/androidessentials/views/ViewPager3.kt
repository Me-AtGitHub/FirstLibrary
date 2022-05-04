package com.au.androidessentials.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.au.androidessentials.R
import com.au.androidessentials.databinding.LayoutViewPagerWithIndicatorBinding
import com.au.androidessentials.views.utils.Defaults
import com.au.androidessentials.views.utils.Defaults.BOTTOM
import com.au.androidessentials.views.utils.Defaults.DEFAULT_FLOAT
import com.au.androidessentials.views.utils.Defaults.DEFAULT_INT
import com.au.androidessentials.views.utils.Defaults.END
import com.au.androidessentials.views.utils.Defaults.POSITION_DEFAULT
import com.au.androidessentials.views.utils.Defaults.START
import com.au.androidessentials.views.utils.Defaults.TOP

class ViewPager3 : RelativeLayout {

    private var previousOffset: Float = DEFAULT_FLOAT

    private lateinit var adapter: FragmentStateAdapter

    private var indicatorRadius: Float = DEFAULT_FLOAT

    private var colorSelectedIndicator: Int = DEFAULT_INT
    private var colorUnselectedIndicator: Int = DEFAULT_INT

    private var indicatorWidth: Float = DEFAULT_FLOAT
    private var indicatorHeight: Float = DEFAULT_FLOAT

    private var indicatorWidthFactor: Float = DEFAULT_FLOAT
    private var indicatorHeightFactor: Float = DEFAULT_FLOAT

    private var indicatorPosition: Int = POSITION_DEFAULT
    private var positionRules: MutableList<Int> = mutableListOf()
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
                indicatorHeightFactor =
                    getFloat(R.styleable.ViewPager3_heightFactorIndicator, DEFAULT_FLOAT)

                /* indicator width factor */
                indicatorWidthFactor =
                    getFloat(R.styleable.ViewPager3_widthFactorIndicator, DEFAULT_FLOAT)

                /* indicator position */
                indicatorPosition =
                    getInteger(R.styleable.ViewPager3_indicatorPosition, POSITION_DEFAULT)

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
        positionRules.clear()
        indicatorView = ImageViewIndicator(context)
        indicatorView.elevation = 2F
        binding.apply {

            addView(indicatorView, 0, layoutParam.apply {
                when (indicatorPosition) {

                    START -> positionRules.add(ALIGN_PARENT_START)
                    END -> positionRules.add(ALIGN_PARENT_END)
                    TOP -> positionRules.add(ALIGN_PARENT_TOP)
                    BOTTOM -> positionRules.add(ALIGN_PARENT_BOTTOM)
                    Defaults.CENTER_HORIZONTAL -> positionRules.add(CENTER_HORIZONTAL)
                    Defaults.CENTER_VERTICAL -> positionRules.add(CENTER_VERTICAL)
                    Defaults.ABOVE -> positionRules.add(ABOVE)
                    Defaults.BELOW -> positionRules.add(BELOW)

                    /*TOP or Defaults.CENTER_HORIZONTAL -> {
                        positionRules.add(ALIGN_PARENT_TOP)
                        positionRules.add(CENTER_HORIZONTAL)
                    }
                    BOTTOM or Defaults.CENTER_HORIZONTAL -> {
                        positionRules.add(ALIGN_PARENT_BOTTOM)
                        positionRules.add(CENTER_HORIZONTAL)
                    }

                    START or Defaults.CENTER_VERTICAL -> {
                        positionRules.add(ALIGN_PARENT_START)
                        positionRules.add(CENTER_VERTICAL)
                    }
                    END or Defaults.CENTER_VERTICAL->{
                        positionRules.add(ALIGN_PARENT_END)
                        positionRules.add(CENTER_VERTICAL)
                    }

                    Defaults.ABOVE or START -> {
                        positionRules.add(ALIGN_PARENT_START)
                        positionRules.add(ABOVE)
                    }
                    Defaults.ABOVE or END -> {
                        positionRules.add(ALIGN_PARENT_END)
                        positionRules.add(ABOVE)
                    }
                    Defaults.ABOVE or Defaults.CENTER_HORIZONTAL -> {
                        positionRules.add(CENTER_HORIZONTAL)
                        positionRules.add(ABOVE)
                    }

                    Defaults.BELOW or START -> {
                        positionRules.add(ALIGN_PARENT_START)
                        positionRules.add(BELOW)
                    }
                    Defaults.BELOW or END -> {
                        positionRules.add(ALIGN_PARENT_END)
                        positionRules.add(BELOW)
                    }
                    Defaults.BELOW or Defaults.CENTER_HORIZONTAL -> {
                        positionRules.add(CENTER_HORIZONTAL)
                        positionRules.add(BELOW)
                    }*/

                }
                positionRules.forEach {
                    addRule(it)
                }
                /* addRule(ALIGN_PARENT_BOTTOM)
                 addRule(CENTER_HORIZONTAL)*/
//                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            })

            /* Setting indicator view's  properties */
            indicatorView.indicatorHeight = indicatorHeight
            indicatorView.indicatorWidth = indicatorWidth
            indicatorView.selectedIndicatorColor = colorSelectedIndicator
            indicatorView.unselectedIndicatorColor = colorUnselectedIndicator
            indicatorView.indicatorWidthFactor = indicatorWidthFactor
            indicatorView.indicatorHeightFactor = indicatorHeightFactor
            indicatorView.imageRadius = indicatorRadius

            viewPager.registerOnPageChangeCallback(onPageChangeCallback)
        }
        postInvalidate()
    }

    fun setAdapter(adapter: FragmentStateAdapter) {
        this.adapter = adapter
        indicatorView.indicatorCount = adapter.itemCount
        binding.viewPager.adapter = adapter
    }

    fun getAdapter() = this.adapter

    private val onPageChangeCallback = object : OnPageChange() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            indicatorView.onPageSelected(position, adapter.itemCount)
        }

        override fun onScroll(
            currentPosition: Int,
            nextPosition: Int,
            offset: Float
        ) {
            indicatorView.onPageScroll(currentPosition, nextPosition, offset)
        }
    }

    interface PageChangeListener {
        fun onPageSelected(position: Int, totalItem: Int)
        fun onPageScroll(
            currentPosition: Int,
            nextPosition: Int,
            positionOffset: Float
        )
    }

    abstract class OnPageChange : ViewPager2.OnPageChangeCallback() {

        private var nextPosition = 0
        private var currentPosition = 0

        private var pageState = 0

        private var lastOffset: Float = 0F

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentPosition = position
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (pageState != 2)
                if (positionOffset > 0F) {
                    nextPosition = if (currentPosition == position) position + 1 else position
                    onScroll(currentPosition, nextPosition, positionOffset)
                    Log.d(TAG, "onPageScrolled: current -> $currentPosition next-> $nextPosition offset -> $positionOffset")
                    lastOffset = positionOffset
                }
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            pageState = state
            if (state == 2) {
                Log.d(
                    TAG,
                    "onPageScrollStateChanged: current -> $currentPosition next-> $nextPosition"
                )

                ValueAnimator.ofFloat(lastOffset * 1000, 1000F).apply {
                    val value = animatedValue as Float
                    duration = 300L
                    addUpdateListener {
//                        if (currentPosition < nextPosition)
                            onScroll(currentPosition, nextPosition, value / 1000)
                    }
                }.start()
            }


        }

        abstract fun onScroll(currentPosition: Int, nextPosition: Int, offset: Float)
    }

    companion object {
        private const val TAG = "ViewPager3"
    }

}