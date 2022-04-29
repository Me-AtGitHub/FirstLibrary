package com.au.viewmodelexample.activities

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.au.viewmodelexample.CountryChangeListener
import com.au.viewmodelexample.CountryCode
import com.au.viewmodelexample.R
import com.au.viewmodelexample.databinding.ActivityTestBinding
import com.au.viewmodelexample.fragments.CountryCodes
import com.google.android.material.tabs.TabLayout

class TestActivity : BaseActivity<ActivityTestBinding>() {

    private  val TAG = "TestActivity"
    override fun bindView(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding.etPhone.onFocusChangeListener = onFocusedChangeListener
        binding.etEmail.onFocusChangeListener = onFocusedChangeListener
        binding.etEmail.addTextChangedListener(onTextChange)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (binding.tabLayout.selectedTabPosition == 0) {
                    binding.phoneGroup.visibility = View.VISIBLE
                    binding.etEmail.visibility = View.GONE
                } else {
                    binding.phoneGroup.visibility = View.GONE
                    binding.etEmail.visibility = View.VISIBLE
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        binding.rootLayout.setOnClickListener {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            restoreInitialState()
        }
    }

    private val onFocusedChangeListener = View.OnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            binding.ivProfile.translationY = -80F
            binding.tabLayout.translationY = -80F
            binding.etPhone.translationY = -80F
            binding.viewDivider.translationY = -80F
            binding.viewPhone.translationY = -80F
            binding.tvCountryCode.translationY = -80F
            binding.etEmail.translationY = -80F
        }
    }

    override fun onBackPressed() {
        if(binding.etEmail.hasFocus()||binding.etPhone.hasFocus()){
            restoreInitialState()
        }else super.onBackPressed()
    }
    private fun restoreInitialState(){
        binding.ivProfile.translationY = 80F
        binding.tabLayout.translationY = 80F
        binding.etPhone.translationY = 80F
        binding.viewDivider.translationY = 80F
        binding.viewPhone.translationY = 80F
        binding.tvCountryCode.translationY = 80F
        binding.etEmail.translationY = 80F
        binding.etPhone.clearFocus()
        binding.etEmail.clearFocus()
    }

    private val onTextChange = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Log.d(TAG, "beforeTextChanged: ")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.d(TAG, "onTextChanged: ")
        }

        override fun afterTextChanged(s: Editable?) {
            Log.d(TAG, "afterTextChanged: ")
        }

    }

}