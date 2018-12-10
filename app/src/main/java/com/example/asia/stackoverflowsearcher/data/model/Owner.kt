package com.example.asia.stackoverflowsearcher.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner {
    @SerializedName("reputation")
    @Expose
    var reputation: Int? = null
    @SerializedName("userId")
    @Expose
    var userId: Int? = null
    @SerializedName("userType")
    @Expose
    var userType: String? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("accept_rate")
    @Expose
    var acceptRate: Int? = null
}