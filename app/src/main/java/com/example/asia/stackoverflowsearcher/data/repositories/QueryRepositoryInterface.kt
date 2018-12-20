package com.example.asia.stackoverflowsearcher.data.repositories

import com.example.asia.stackoverflowsearcher.data.model.Item

interface QueryRepositoryInterface {
    interface OnQueryResultDisplayListener{
        fun onSuccess(itemList: List<Item>?)
        fun onError(errorMessageText: String?)
    }

    fun getQueryResult(title: String?, listener: OnQueryResultDisplayListener)
}