package com.au.androidessentials.webservices

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


/*
* Created By Vikas Singh on 20/04/2022.
*
*/


class RetrofitApp {

    /*
    Static function get takes two parameters
    1- Base Url in string form
    2- Token as String
    By passing these two params you can get retrofit object
    which will be responsible for generating links using your base url.
    */


    companion object{

        fun get(baseUrl:String,token: String):Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(token))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        }

        private fun getOkHttpClient(token:String): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)
            builder.addNetworkInterceptor(httpLoggingInterceptor)

            builder.addInterceptor(Interceptor { chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                    .header("token", token)
                    .header("Accept", "application/json")
                val build = requestBuilder.build()
                chain.proceed(build)
            })
            return builder.build()
        }
    }
}

/*

                                        TODO FOLLOW THIS PATTERN TO LEVERAGE THE "RETROFITAPP" CLASS


This is the class where all the RESTFUL api method will reside
interface Api{
    @POST("urlSuccessor")
    fun update():Call<ResponseClass>

    @GET("urlSuccessor")
    fun getData():Call<ResponseClass>
}

TODO This the class to get RetrofitClient for a particular Api interface

class RestClient{
    companion object{

        private const val BASE_URL:String = "https://example.com/"
        private var ACCESS_TOKEN:String = "token"

        fun <X,T:Class<X>> get(api:T):X{
            return RetrofitApp.get(BASE_URL, ACCESS_TOKEN).create(api)
        }

        fun <X,T:Class<X>> get(baseUrl:String,accessToken:String,api:T):X{
            return RetrofitApp.get(baseUrl,accessToken).create(api)
        }

    }
}

TODO finally implement call these api methods in your activity or fragment

class FRAGMENT-or-ACTIVITY{

    fun performAction(){
        RestClient.get(Api::class.java).getData()
    }

}

*/
