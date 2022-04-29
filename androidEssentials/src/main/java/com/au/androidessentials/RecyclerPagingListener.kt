package com.au.androidessentials

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerPagingListener(private val lm: LinearLayoutManager): RecyclerView.OnScrollListener() {

    abstract fun isLoading():Boolean
    abstract fun notHaveDataToLoad():Boolean
    abstract fun onLoadMore()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(!notHaveDataToLoad()&&!isLoading()){
            if(lm.findLastCompletelyVisibleItemPosition() >= lm.itemCount-1){
                onLoadMore()
            }
        }
    }

}