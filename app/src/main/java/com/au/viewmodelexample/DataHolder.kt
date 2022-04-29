package com.au.viewmodelexample

class DataHolder {

    private val hashMap = hashMapOf<String?,Any>()

    fun saveData(key:String?,value:Any) {
        hashMap[key] = value
    }

    fun haveDataFor(key:String?):Boolean{
        return hashMap[key] !=null
    }

    fun getData(key: String?):Any?{
        return hashMap[key]
    }
    companion object{
        private var instance:DataHolder? = null

        fun getInstance():DataHolder?{
            if(instance == null)
                instance = DataHolder()
            return instance

        }
    }
}