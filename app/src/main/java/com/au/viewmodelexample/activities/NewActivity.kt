package com.au.viewmodelexample.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.au.viewmodelexample.R
import com.au.viewmodelexample.databinding.ActivityNewBinding
import com.au.viewmodelexample.fragments.ViewPagerTestFragment

class NewActivity:BaseActivity<ActivityNewBinding>() {

    private val TAG = "NewActivity"
    override fun bindView(): ActivityNewBinding {
        return ActivityNewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding.viewPager.setAdapter(ViewPagerAdapter(this))
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
        override fun getItemCount() = 3
        override fun createFragment(position: Int) = ViewPagerTestFragment.get(position)
    }
}