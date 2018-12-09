package com.example.asia.stackoverflowsearcher.search_with_results

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.repositories.QueryRepository

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

    override fun getContext(): Context {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setToolbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecyclerView(): RecyclerView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorMessageTextView(): TextView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToDetails(url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToFragment(url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragmentWithArgs(url: String?): ResultDetailsFragmentView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFirstFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
