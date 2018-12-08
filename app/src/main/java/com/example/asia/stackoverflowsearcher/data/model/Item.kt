package com.example.asia.stackoverflowsearcher.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("tags")
    @Expose
    private var tags: List<String>? = null
    @SerializedName("owner")
    @Expose
    private var owner: Owner? = null
    @SerializedName("is_answered")
    @Expose
    private var isAnswered: Boolean? = null
    @SerializedName("view_count")
    @Expose
    private var viewCount: Int? = null
    @SerializedName("answer_count")
    @Expose
    private var answerCount: Int? = null
    @SerializedName("score")
    @Expose
    private var score: Int? = null
    @SerializedName("last_activity_date")
    @Expose
    private var lastActivityDate: Int? = null
    @SerializedName("creation_date")
    @Expose
    private var creationDate: Int? = null
    @SerializedName("question_id")
    @Expose
    private var questionId: Int? = null
    @SerializedName("link")
    @Expose
    private var link: String? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("accepted_answer_id")
    @Expose
    private var acceptedAnswerId: Int? = null
    @SerializedName("last_edit_date")
    @Expose
    private var lastEditDate: Int? = null
    @SerializedName("closed_date")
    @Expose
    private var closedDate: Int? = null
    @SerializedName("closed_reason")
    @Expose
    private var closedReason: String? = null
    @SerializedName("bounty_amount")
    @Expose
    private var bountyAmount: Int? = null
    @SerializedName("bounty_closes_date")
    @Expose
    private var bountyClosesDate: Int? = null
}