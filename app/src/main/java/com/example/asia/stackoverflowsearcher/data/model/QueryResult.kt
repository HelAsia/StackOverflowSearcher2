package com.example.asia.stackoverflowsearcher.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QueryResult {
    @SerializedName("items")
    @Expose
    private var items: List<Item>? = null
    @SerializedName("has_more")
    @Expose
    private var hasMore: Boolean? = null
    @SerializedName("quota_max")
    @Expose
    private var quotaMax: Int? = null
    @SerializedName("quota_remaining")
    @Expose
    private var quotaRemaining: Int? = null
    @SerializedName("error_id")
    @Expose
    private var errorId: Int? = null
    @SerializedName("error_message")
    @Expose
    private var errorMessage: String? = null
    @SerializedName("error_name")
    @Expose
    private var errorName: String? = null
}