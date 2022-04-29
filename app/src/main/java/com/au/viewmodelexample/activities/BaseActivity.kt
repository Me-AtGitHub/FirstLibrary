package com.au.viewmodelexample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.au.viewmodelexample.Communicator

abstract class BaseActivity<X : ViewBinding> : AppCompatActivity() {

    private var _binding: X? = null
    val binding get() = _binding!!
    abstract fun bindView(): X

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindView()
        setContentView(binding.root)
    }



}