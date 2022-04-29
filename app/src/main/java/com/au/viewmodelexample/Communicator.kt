package com.au.viewmodelexample

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.Filter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.au.viewmodelexample.databinding.CountryCodePickerBinding
import com.au.viewmodelexample.databinding.ItemCountryBinding
import java.util.*

abstract class Communicator {
    open fun onBackPress() {}
    open fun onCreated() {}
    open fun onThat() {}
    open fun onThis() {}
    open fun onData(data: Any) {}
}

interface InstanceCreated {
    fun instanceCreated(owner: Fragment)
}

interface DataReceiver {
    fun onDataReceive(data: Any?)
}

interface OnTextChanged : TextWatcher {
    fun onText(string: String)
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onText(s.toString())
    }
}

interface CountryChangeListener {
    fun onCountryChanged(code: CountryCode)
}

data class CountryCode(val countryName: String, val countryCode: String)




