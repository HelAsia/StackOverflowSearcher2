package com.example.asia.stackoverflowsearcher.data.repositories

import android.util.Log
import com.example.asia.stackoverflowsearcher.data.model.QueryResult
import com.example.asia.stackoverflowsearcher.data.network.RetrofitSingleton
import com.example.asia.stackoverflowsearcher.data.network.SearchAPI
import com.example.asia.stackoverflowsearcher.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class QueryRepository : QueryRepositoryInterface {
    private var retrofit: Retrofit? = null
    private var searchAPI: SearchAPI? = null
    private var baseURL: String? = null

    constructor(){
        this.baseURL = Constant.BASE_URL
        this.retrofit = RetrofitSingleton.getInstance(this.baseURL).getRetrofit()
        this.searchAPI = retrofit?.create(SearchAPI::class.java)
    }

    constructor(url: String?){
        this.baseURL = url
        this.retrofit = RetrofitSingleton.getInstance(this.baseURL).getRetrofit()
        this.searchAPI = retrofit?.create(SearchAPI::class.java)
    }

    override fun getQueryResult(title: String?, listener: QueryRepositoryInterface.OnQueryResultDisplayListener?) {
        val resp: Call<QueryResult>? = searchAPI?.getQueryResult(title)
        resp?.enqueue(object : Callback<QueryResult>{
            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                val queryResult: QueryResult? = response.body()
                if (queryResult?.errorId == null){
                    Log.i("onResponse(): ", "$queryResult")
                    listener?.onSuccess(queryResult?.items)
                }else{
                    Log.i("onResponse(). error: ", "$queryResult")
                    listener?.onError(queryResult?.errorMessage)
                }
            }

            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                Log.i("onFailure(): Server ", t.message)
                listener?.onError(t.message)
            }
        })
    }
}