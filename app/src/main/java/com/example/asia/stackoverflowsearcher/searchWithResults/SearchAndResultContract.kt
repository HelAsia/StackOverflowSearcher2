package com.example.asia.stackoverflowsearcher.searchWithResults

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.asia.stackoverflowsearcher.data.model.Item

interface SearchAndResultContract {
    interface View{
        fun getContext(): Context?
        fun setToolbar()
        fun setVisibleErrorMessageTextView(errorMessageText: String?)
        fun setGoneErrorMessageTextView()
        fun setFinishRefreshingSwipeRefresh()
        fun setRecyclerView(itemList: List<Item?>?)
        fun setLinearLayoutForRecyclerView(itemList: List<Item?>?)
        fun setSwipeRefreshLayoutEnabledStatus()
        fun setSwipeRefreshLayout()
        fun goToDetails(url: String?)
        fun goToFragment(url: String?)
        fun getFragmentWithArgs(url: String?): ResultDetailsFragmentView
    }

    interface Presenter{
        fun setFirstScreen()
        fun getItemsFromServer(title: String?)
        fun getLastQueryFromPreferences(): String?
        fun saveLastQueryInPreferences(title: String?)
    }
}