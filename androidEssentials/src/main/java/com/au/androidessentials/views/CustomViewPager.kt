package com.au.androidessentials.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.au.androidessentials.Functions.Companion.dpToPx
import com.au.androidessentials.R
import com.au.androidessentials.databinding.LayoutLevatatingPageViewPagerBinding

class CustomViewPager : RelativeLayout {

    private var currentItemHorizontalMargin = 0F
    private var nextItemVisible = 0F

    var viewPager:ViewPager2
    private var binding:LayoutLevatatingPageViewPagerBinding

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle,
        0
    ){
        context.obtainStyledAttributes(attributeSet,R.styleable.CustomViewPager,0,0).apply {

            currentItemHorizontalMargin = getDimension(R.styleable.CustomViewPager_itemHorizontalMargin,0F)
            nextItemVisible = getDimension(R.styleable.CustomViewPager_nextItemVisible,0F)
            binding = LayoutLevatatingPageViewPagerBinding.inflate(LayoutInflater.from(context),this@CustomViewPager)
            viewPager = binding.viewPager
            recycle()
            init()
        }
    }

    private fun init(){
        binding.viewPager.apply{
            offscreenPageLimit = 1
//            setPadding(30.dpToPx(context),0,30.dpToPx(context),0)
//            setPageTransformer(MarginPageTransformer(20.dpToPx(context)))

            setPageTransformer{page,position->
//                page.translationX = -(currentItemHorizontalMargin+nextItemVisible)*position
//                page.scaleY = 1 -(0.25F* kotlin.math.abs(position))
//                page.alpha = 0.25F + (1 - kotlin.math.abs(position))
                page.translationX = -(page.width)*position
                page.translationY = -(20)*position
            }
            clipChildren = false
            clipToPadding = false
            setPadding(80,80,80,80)

        }
    }

}