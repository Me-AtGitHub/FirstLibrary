package com.au.viewmodelexample

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycling


class DataManager  {


    private constructor()
    // fragments that are joining this communication pool
    private val fragmentRegistry:HashMap<Fragment,DataReceiver> = hashMapOf()

    // called on each instance call of this class
    private val instanceCreated = object:InstanceCreated{
        override fun instanceCreated(owner: Fragment) {
            if(dataHolder!!.haveDataFor(owner::class.java.canonicalName)){
                fragmentRegistry[owner]!!.onDataReceive(dataHolder!!.getData(owner::class.java.canonicalName))
            }
        }
    }

    // to access data
    private var dataHolder = DataHolder.getInstance()

    fun <T:Fragment> sendData(toFragment:Class<T>,value:Any){
        dataHolder!!.saveData(toFragment.canonicalName,value)
    }


    companion object{

        private var instance:DataManager? = null

        fun getInstance():DataManager{
            if(instance==null)
                instance = DataManager()
            return instance!!
        }

        fun getInstanceFor(owner:Fragment,dataReceiver: DataReceiver):DataManager{
            if(instance == null)
                instance = DataManager()
            return DataManager().apply {
                fragmentRegistry[owner] = dataReceiver
                instanceCreated.instanceCreated(owner)
            }
        }
    }
}