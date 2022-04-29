package com.au.viewmodelexample.fragments

import android.view.ViewGroup
import com.au.viewmodelexample.databinding.FragmentDBinding

class FragmentD:BaseFragment<FragmentDBinding>() {

    override fun inflateBinding(container: ViewGroup?): FragmentDBinding {
        return FragmentDBinding.inflate(layoutInflater,container,false)
    }

}