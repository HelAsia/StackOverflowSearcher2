package com.example.asia.stackoverflowsearcher.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner {
    @SerializedName("reputation")
    @Expose
    private var reputation: Int? = null
    @SerializedName("userId")
    @Expose
    private var userId: Int? = null
    @SerializedName("userType")
    @Expose
    private var userType: String? = null
    @SerializedName("profile_image")
    @Expose
    private var profileImage: String? = null
    @SerializedName("display_name")
    @Expose
    private var displayName: String? = null
    @SerializedName("link")
    @Expose
    private var link: String? = null
    @SerializedName("accept_rate")
    @Expose
    private var acceptRate: Int? = null
}