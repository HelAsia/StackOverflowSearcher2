package com.example.asia.stackoverflowsearcher.searchWithResults

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.Item
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepository
import com.example.asia.stackoverflowsearcher.details.WebViewActivity
import com.example.asia.stackoverflowsearcher.licenses.LicensesActivityView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search_and_result_view.*
import java.util.*
import java.util.concurrent.TimeUnit

class SearchAndResultActivityView : AppCompatActivity(), SearchAndResultContract.View,
    ResultCardsAdapter.OnShareWebViewDetailsListener{
    private val presenter: SearchAndResultContract.Presenter? =
        SearchAndResultPresenter(this, QueryRepository())
    private var context: Context? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_and_result_view)
        context = applicationContext

        presenter?.setFirstScreen()
    }

    override fun getContext(): Context? {
        return context
    }

    override fun setToolbar() {
        toolbar_activity_search_view.setTitle(  R.string.title_with_font)
        setSupportActionBar(toolbar_activity_search_view)
    }

    override fun setRecyclerView(itemList: List<Item?>?) {
        if (itemList == null){
            setVisibleErrorMessageTextView("You do not have cards to show!")
        }else{
            setLinearLayoutForRecyclerView(itemList)
            setSwipeRefreshLayoutEnabledStatus()
        }
    }

    override fun setLinearLayoutForRecyclerView(itemList: List<Item?>?) {
        val adapterCards = ResultCardsAdapter(itemList)
        items_recycler_view.adapter = adapterCards
        linearLayoutManager = LinearLayoutManager(this)
        items_recycler_view.layoutManager = linearLayoutManager
        adapterCards.setCallbackWebViewOnShareClickedListener(this)
    }

    override fun setSwipeRefreshLayoutEnabledStatus() {
        if (linearLayoutManager != null){
            items_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    swipe_refresh_layout.isEnabled =
                            linearLayoutManager!!.findFirstVisibleItemPosition() == 0
                }
            })
        }
    }

    override fun setSwipeRefreshLayout() {
        swipe_refresh_layout.setOnRefreshListener{
            presenter?.getItemsFromServer(presenter.getLastQueryFromPreferences())
        }

        swipe_refresh_layout?.setColorSchemeResources(R.color.primary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)
    }

    override fun setVisibleErrorMessageTextView(errorMessageText: String?) {
        error_message.visibility = View.VISIBLE
        error_message.text = errorMessageText
    }

    override fun setGoneErrorMessageTextView() {
        error_message.visibility = View.GONE
    }

    override fun setFinishRefreshingSwipeRefresh() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun goToDetails(url: String?) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
        finish()
    }

    override fun goToFragment(url: String?) {
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_details, getFragmentWithArgs(url))
        fragmentTransaction.commit()
    }

    override fun getFragmentWithArgs(url: String?): ResultDetailsFragmentView {
        val data = Bundle()
        data.putString("url", url)
        val resultDetailsFragmentView = ResultDetailsFragmentView()
        resultDetailsFragmentView.arguments = data
        return resultDetailsFragmentView
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_menu, menu)

        val refreshViewItem = menu?.findItem(R.id.action_refresh)
        refreshViewItem?.setOnMenuItemClickListener {
            presenter?.getItemsFromServer(presenter.getLastQueryFromPreferences())
            return@setOnMenuItemClickListener true
        }

        val infoViewItem = menu?.findItem(R.id.action_licenses)
        infoViewItem?.setOnMenuItemClickListener {
            val intent = Intent(context, LicensesActivityView::class.java)
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchViewAndroidActionBar = searchViewItem?.actionView as android.support.v7.widget.SearchView
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchViewAndroidActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.support.v7.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(title: String): Boolean {
                    presenter?.saveLastQueryInPreferences(title)
                    presenter?.getItemsFromServer(title)
                    searchViewAndroidActionBar.clearFocus()
                    return true
                }

                override fun onQueryTextChange(p0: String): Boolean{
                    if (p0.length > 2){
                        subscriber.onNext(p0)
                    }
                    return false
                }
            })
        }).debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { title ->
                presenter?.saveLastQueryInPreferences(title)
                presenter?.getItemsFromServer(title)
            }

        return super.onCreateOptionsMenu(menu)
    }

    override fun shareCardClicked(url: String?) {
    if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            goToDetails(url)
        }else{
            goToFragment(url)
        }
    }
}
