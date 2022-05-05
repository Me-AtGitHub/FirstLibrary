package com.au.androidessentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.au.androidessentials.Functions.Companion.replaceFragment
import com.au.androidessentials.Functions.Companion.shortToast

abstract class BaseFragment<viewBinding:ViewBinding>:Fragment() {
    private var _binding:viewBinding? = null
    val binding get() = _binding!!
    abstract fun bind(container:ViewGroup?):viewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}