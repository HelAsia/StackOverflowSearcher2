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
                QueryRepositoryInterface.OnQueryResultDisplayListener,
    ResultCardsAdapter.OnShareWebViewDetailsListener {

    private var linearLayoutManager: LinearLayoutManager? = null

    override fun setFirstScreen() {
        if (getLastQueryFromPreferences() != ""){
            getItemsFromServer(getLastQueryFromPreferences())
        }
    }

    override fun getItemsFromServer(title: String?) {
        queryRepository?.getQueryResult(title, this)
    }

    override fun getLastQueryFromPreferences(): String? {
        return PreferenceManager.getDefaultSharedPreferences(searchView?.getContext())
            .getString(Constant.PREF_LAST_QUERY, "")
    }

    override fun setRecyclerView(itemList: List<Item?>?) {
        if (itemList == null){
            showErrorMessage(searchView?.getContext()?.resources
                    ?.getString(R.string.empty_list_error))
        }else{
            setLinearLayoutForRecyclerView(itemList)
            setSwipeRefreshLayoutEnabledStatus()
        }
    }

    override fun showErrorMessage(errorMessageText: String?) {
        if (searchView != null){
            searchView.getErrorMessageTextView().visibility = View.VISIBLE
            searchView.getErrorMessageTextView().text = errorMessageText
        }
    }

    override fun setLinearLayoutForRecyclerView(itemList: List<Item?>?) {
        if (searchView != null){
            val adapterCards = ResultCardsAdapter(itemList)
            searchView.getRecyclerView().adapter = adapterCards
            linearLayoutManager = LinearLayoutManager(searchView.getContext())
            searchView.getRecyclerView().layoutManager = linearLayoutManager
            adapterCards.setCallbackWebViewOnShareClickedListener(this)
        }
    }

    override fun setSwipeRefreshLayoutEnabledStatus() {
        if (searchView != null && linearLayoutManager != null){
            searchView.getRecyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    searchView.getSwipeRefreshLayout().isEnabled =
                            linearLayoutManager!!.findFirstVisibleItemPosition() == 0
                }
            })

        }
    }

    override fun setSwipeRefreshLayout() {
        searchView?.getSwipeRefreshLayout()?.setOnRefreshListener{
            getItemsFromServer(getLastQueryFromPreferences())
        }

        searchView?.getSwipeRefreshLayout()?.setColorSchemeResources(R.color.primary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)
    }

    override fun shareCardClicked(url: String?) {
        if (searchView != null){
            if (searchView.getContext()?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
                searchView.goToDetails(url)
            }else{
                searchView.goToFragment(url)
            }
        }
    }

    override fun onSuccess(itemList: List<Item>?) {
        if (searchView!= null){
            setRecyclerView(itemList)
            searchView.getErrorMessageTextView().visibility = View.GONE
            searchView.getSwipeRefreshLayout().isRefreshing = false

        }
    }

    override fun onError(errorMessageText: String?) {
        showErrorMessage(errorMessageText)
    }

    override fun saveLastQueryInPreferences(title: String?) {
        val lastQuery = PreferenceManager.getDefaultSharedPreferences(searchView?.getContext()).edit()
        lastQuery.putString(Constant.PREF_LAST_QUERY, title).apply()
        lastQuery.commit()
    }
}