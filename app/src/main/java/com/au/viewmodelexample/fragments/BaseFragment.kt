package com.au.viewmodelexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.au.viewmodelexample.Communicator
import com.au.viewmodelexample.activities.BaseActivity


abstract class BaseFragment<T:ViewBinding> : Fragment() {

    private  var mBinding: T? = null
    val binding get() = mBinding!!
    abstract fun inflateBinding(container: ViewGroup?):T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = inflateBinding(container)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        requireActivity()
        mBinding = null
    }

}