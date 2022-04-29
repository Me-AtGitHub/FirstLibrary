package com.au.viewmodelexample.fragments

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.Filter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.au.viewmodelexample.CountryChangeListener
import com.au.viewmodelexample.CountryCode
import com.au.viewmodelexample.OnTextChanged
import com.au.viewmodelexample.databinding.CountryCodePickerBinding
import com.au.viewmodelexample.databinding.ItemCountryBinding
import java.util.*

class CountryCodes private constructor() {

    private lateinit var listener: CountryChangeListener
    private var dialog: Dialog? = null
    private lateinit var editText: EditText
    private var adapter: CountryAdapter? = null
    private var copyList: MutableList<CountryCode> = mutableListOf()

    fun show() {
        copyList.clear()
        copyList.addAll(getCodes())
        adapter = CountryAdapter()
        dialog = Dialog(editText.context).apply {
            CountryCodePickerBinding.inflate(LayoutInflater.from(editText.context)).let {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setContentView(it.root)
                it.rvCountries.adapter = adapter
                it.rvCountries.layoutManager = LinearLayoutManager(editText.context)
                it.ivClose.setOnClickListener {
                    dismiss()
                }
                it.etCountryName.addTextChangedListener(object : OnTextChanged {
                    override fun onText(string: String) {
                        adapter!!.getFilter().filter(it.etCountryName.text.trim().toString())
                    }
                })
            }
        }
        dialog!!.show()
    }

    fun setEditText(editText: EditText) {
        this.editText = editText
    }

    fun setListener(listener: CountryChangeListener) {
        this.listener = listener
    }

    inner class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {


        inner class ViewHolder(val binding: ItemCountryBinding) :
            RecyclerView.ViewHolder(binding.root) {
            init {
                binding.tvItemCountryName.setOnClickListener {
                    dialog!!.dismiss()
                    listener.onCountryChanged(copyList[adapterPosition])
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemCountryBinding.inflate(
                    LayoutInflater.from(editText.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            copyList[position].let {
                holder.binding.tvItemCountryName.text = it.countryName
            }
        }

        override fun getItemCount(): Int {
            return copyList.count()
        }

        fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val key = constraint!!.trim().toString()
                    val filteredList = mutableListOf<CountryCode>()

                    if (key != "") {
                        for (i in getCodes()) {
                            if (i.countryName.contains(key, true)) filteredList.add(i)
                        }
                    } else filteredList.addAll(getCodes() as MutableList<CountryCode>)
                    return FilterResults().apply {
                        count = filteredList.count()
                        values = filteredList
                    }
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    copyList.clear()
                    if (constraint != "")
                        copyList.addAll(results!!.values as MutableList<CountryCode>)
                    else copyList.addAll(getCodes())
                    notifyDataSetChanged()
                }

            }
        }
    }

    companion object {

        private val list: MutableList<CountryCode> = mutableListOf()

        fun getInstance() = CountryCodes()

        fun getCodes(): List<CountryCode> {
            if (list.isEmpty()) {
                val locale = Locale.getISOCountries()
                for (i in locale) {
                    Locale("", i).let {
                        list.add(CountryCode(it.displayName, it.country))
                    }
                }
            }
            return list
        }

    }

}
