package com.au.viewmodelexample

import android.text.Editable
import android.text.TextWatcher

interface OnTextChange: TextWatcher {
    fun onTextChange(s:CharSequence?)
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChange(s)
    }
}