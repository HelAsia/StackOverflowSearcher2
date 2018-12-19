package com.example.asia.stackoverflowsearcher.data.repositories

import android.util.Log
import com.example.asia.stackoverflowsearcher.data.model.QueryResult
import com.example.asia.stackoverflowsearcher.data.network.RetrofitSingleton
import com.example.asia.stackoverflowsearcher.data.network.SearchAPI
import com.example.asia.stackoverflowsearcher.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class QueryRepository : QueryRepositoryInterface {
    private var retrofit: Retrofit? = null
    private var searchAPI: SearchAPI? = null

    init {
        this.retrofit = RetrofitSingleton.createRetrofitInstance()
        this.searchAPI = retrofit?.create(SearchAPI::class.java)
    }

    override fun getQueryResult(title: String?, listener: QueryRepositoryInterface.OnQueryResultDisplayListener) {
        searchAPI?.getQueryResult(title)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    queryResult -> if (queryResult.errorId == null)  listener.onSuccess(queryResult.items)
                            else listener.onError(queryResult.errorMessage)
                    Log.i("onResponse(): ", "$queryResult")
                },
                {
                    error: Throwable -> listener.onError(error.message)
                    Log.i("onResponse(). error: ", "$error")
                }

            )

   }
}