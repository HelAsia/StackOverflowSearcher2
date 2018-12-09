package com.example.asia.stackoverflowsearcher.search_with_results

import android.support.v7.widget.LinearLayoutManager
import com.example.asia.stackoverflowsearcher.data.model.Item
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepository

class SearchAndResultPresenter(searchView: SearchAndResultContract.View?, queryRepository: QueryRepository?):
    SearchAndResultContract.Presenter {
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun setFirstScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemsFromServer(title: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastQueryFromPreferences(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecyclerView(itemList: List<Item?>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(errorMessageText: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLinearLayoutForRecyclerView(itemList: List<Item?>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSwipeRefreshLayoutEnabledStatus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSwipeRefreshLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveLastQueryInPreferences(title: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}