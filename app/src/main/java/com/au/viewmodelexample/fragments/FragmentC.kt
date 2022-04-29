package com.au.viewmodelexample.fragments

import android.view.ViewGroup
import com.au.viewmodelexample.databinding.FragmentCBinding

class FragmentC:BaseFragment<FragmentCBinding>() {

    override fun inflateBinding(container: ViewGroup?): FragmentCBinding {
        return FragmentCBinding.inflate(layoutInflater,container,false)
    }
}