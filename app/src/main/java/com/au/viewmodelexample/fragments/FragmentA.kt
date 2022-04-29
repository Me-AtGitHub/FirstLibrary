package com.au.viewmodelexample.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.au.viewmodelexample.DataManager
import com.au.viewmodelexample.DataReceiver
import com.au.viewmodelexample.databinding.FragmentABinding

class FragmentA:BaseFragment<FragmentABinding>() {

    private val TAG = "FragmentA"
    override fun inflateBinding(container: ViewGroup?): FragmentABinding {
        return FragmentABinding.inflate(layoutInflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataManager.getInstanceFor(this,object :DataReceiver{
            override fun onDataReceive(data: Any?) {
                Log.d(TAG, "onDataReceive: $data")
            }

        })
    }

}