package com.example.asia.stackoverflowsearcher.search_with_results

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepository
import com.example.asia.stackoverflowsearcher.licenses.LicensesActivityView
import com.example.asia.stackoverflowsearcher.welcome.WelcomeActivityView
import kotlinx.android.synthetic.main.activity_search_and_result_view.*

class SearchAndResultActivityView : AppCompatActivity(), SearchAndResultContract.View {
    private val presenter: SearchAndResultContract.Presenter? =
        SearchAndResultPresenter(this, QueryRepository())
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_and_result_view)
        context = applicationContext

        setToolbar()
        presenter?.setFirstScreen()
        presenter?.setSwipeRefreshLayout()
    }

    override fun getContext(): Context? {
        return context
    }

    override fun setToolbar() {
        toolbar_activity_search_view.setTitle(  R.string.title_with_font)
        setSupportActionBar(toolbar_activity_search_view)
    }

    override fun getRecyclerView(): RecyclerView {
        return items_recycler_view
    }

    override fun getErrorMessageTextView(): TextView {
        return error_message
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout {
        return swipe_refresh_layout
    }

    override fun goToDetails(url: String?) {
        val intent = Intent(context, WelcomeActivityView::class.java)
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
        searchViewAndroidActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(title: String?): Boolean {
                presenter?.saveLastQueryInPreferences(title)
                presenter?.getItemsFromServer(title)
                searchViewAndroidActionBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
