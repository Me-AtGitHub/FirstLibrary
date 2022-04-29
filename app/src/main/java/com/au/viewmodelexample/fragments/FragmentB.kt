package com.au.viewmodelexample.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.au.viewmodelexample.Communicator
import com.au.viewmodelexample.DataManager
import com.au.viewmodelexample.DataReceiver
import com.au.viewmodelexample.activities.MainActivity
import com.au.viewmodelexample.databinding.FragmentBBinding

class FragmentB:BaseFragment<FragmentBBinding>() {
    private val TAG = "FragmentB"
    override fun inflateBinding(container: ViewGroup?): FragmentBBinding {
        return FragmentBBinding.inflate(layoutInflater,container,false)
    }
}