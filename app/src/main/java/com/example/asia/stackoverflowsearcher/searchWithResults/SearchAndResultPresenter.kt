package com.example.asia.stackoverflowsearcher.searchWithResults

import android.content.res.Configuration
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.Item
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepository
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepositoryInterface
import com.example.asia.stackoverflowsearcher.utils.Constant

class SearchAndResultPresenter(private val searchView: SearchAndResultContract.View?,
                               private val queryRepository: QueryRepository?):
                SearchAndResultContract.Presenter,
                QueryRepositoryInterface.OnQueryResultDisplayListener {

    override fun setFirstScreen() {
        if (getLastQueryFromPreferences() != ""){
            getItemsFromServer(getLastQueryFromPreferences())
        }
        searchView?.setToolbar()
        searchView?.setSwipeRefreshLayout()
    }

    override fun getItemsFromServer(title: String?) {
        queryRepository?.getQueryResult(title, this)
    }

    override fun getLastQueryFromPreferences(): String? {
        return PreferenceManager.getDefaultSharedPreferences(searchView?.getContext())
            .getString(Constant.PREF_LAST_QUERY, "")
    }

    override fun onSuccess(itemList: List<Item>?) {
            searchView?.setRecyclerView(itemList)
            searchView?.setGoneErrorMessageTextView()
            searchView?.setFinishRefreshingSwipeRefresh()
    }

    override fun onError(errorMessageText: String?) {
        searchView?.setVisibleErrorMessageTextView(errorMessageText)
    }

    override fun saveLastQueryInPreferences(title: String?) {
        val lastQuery = PreferenceManager.getDefaultSharedPreferences(searchView?.getContext()).edit()
        lastQuery.putString(Constant.PREF_LAST_QUERY, title).apply()
        lastQuery.commit()
    }
}