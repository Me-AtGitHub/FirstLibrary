package com.au.viewmodelexample.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.au.viewmodelexample.databinding.FragmentTestViewPagerBinding

class ViewPagerTestFragment:BaseFragment<FragmentTestViewPagerBinding>() {

    private var position:Int = 0
    override fun inflateBinding(container: ViewGroup?): FragmentTestViewPagerBinding {
        return FragmentTestViewPagerBinding.inflate(layoutInflater,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION,0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "$position"

       /* val color = when(position){
            0-> android.R.color.holo_red_light
            1-> android.R.color.holo_green_light
            else -> android.R.color.holo_purple
        }

        binding.rlRoot.setBackgroundResource(color)*/
    }

    companion object{
        private const val POSITION = "2465"
        fun get(position:Int) = ViewPagerTestFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION,position)
            }
        }
    }

}