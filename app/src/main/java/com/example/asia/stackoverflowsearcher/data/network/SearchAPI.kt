package com.example.asia.stackoverflowsearcher.data.network

import com.example.asia.stackoverflowsearcher.data.model.QueryResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("2.2/search?order=desc&sort=activity&site=stackoverflow")
    fun getQueryResult(@Query("intitle") title: String?): Observable<QueryResult>
}