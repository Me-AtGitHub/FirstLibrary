package com.au.viewmodelexample.activities

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.au.viewmodelexample.R
import com.au.viewmodelexample.databinding.ActivityMainBinding
import com.au.viewmodelexample.fragments.FragmentA
import com.au.viewmodelexample.fragments.FragmentB
import com.au.viewmodelexample.fragments.FragmentC
import com.au.viewmodelexample.fragments.FragmentD

class MainActivity : BaseActivity<ActivityMainBinding>() {

    var radius = 25F
    override fun bindView() = ActivityMainBinding.inflate(layoutInflater)

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bounce.setOnClickListener{
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.diagonal_motion))
        }

        binding.fadeIn.setOnClickListener{
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in))
        }

        binding.fadeOut.setOnClickListener{
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out))
        }

        binding.rotate.setOnClickListener {
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate))
        }

        binding.zoomIn.setOnClickListener {
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_in))
        }

        binding.zoomOut.setOnClickListener {
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_out))
        }

        binding.slideUp.setOnClickListener {
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_up))
        }

        binding.slideDown.setOnClickListener {
            binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_down))
        }

    }


}