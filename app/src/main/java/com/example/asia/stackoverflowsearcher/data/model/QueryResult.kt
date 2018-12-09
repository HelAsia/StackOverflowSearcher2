package com.example.asia.stackoverflowsearcher.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QueryResult(  @SerializedName("items")
                    @Expose
                    var items: List<Item>? = null,
                    @SerializedName("has_more")
                    @Expose
                    var hasMore: Boolean? = null,
                    @SerializedName("quota_max")
                    @Expose
                    var quotaMax: Int? = null,
                    @SerializedName("quota_remaining")
                    @Expose
                    var quotaRemaining: Int? = null,
                    @SerializedName("error_id")
                    @Expose
                    var errorId: Int? = null,
                    @SerializedName("error_message")
                    @Expose
                    var errorMessage: String? = null,
                    @SerializedName("error_name")
                    @Expose
                    var errorName: String? = null)