package com.example.asia.stackoverflowsearcher.data.network

import com.example.asia.stackoverflowsearcher.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitSingleton private constructor(baseURL: String?) {
    private var retrofit: Retrofit? = null

    init {
        createRetrofitInstance(baseURL)
    }

    companion object {
        private var instance: RetrofitSingleton? = null

        fun getInstance(baseURL: String?): RetrofitSingleton {
            if(instance == null){
                instance = RetrofitSingleton(baseURL)
            }
            return instance as RetrofitSingleton
        }
    }

    private fun createRetrofitInstance(baseURL: String?){
        if(retrofit == null){
            val baseURLToRetrofit: String?
            val client: OkHttpClient
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                .readTimeout(Constant.GLOBAL_TIMEOUT_PARAMETER, TimeUnit.SECONDS)
                .connectTimeout(Constant.GLOBAL_TIMEOUT_PARAMETER, TimeUnit.SECONDS)

            client = builder.build()

            val gson: Gson = GsonBuilder().setLenient().create()

            baseURLToRetrofit = baseURL ?: Constant.BASE_URL

            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(baseURLToRetrofit)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }

    fun getRetrofit(): Retrofit?{
        return this.retrofit
    }

}