package com.example.asia.stackoverflowsearcher.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.searchWithResults.SearchAndResultActivityView
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        context = applicationContext

        setToolbar()
        setWebView(getUrl())
    }

    private fun getUrl(): String{
        val i: Intent = intent
        return i.getStringExtra("url") ?: "https://stackoverflow.com/"
    }

    @SuppressLint("NewApi")
    private fun setWebView(url: String){
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)
    }

    @SuppressLint("NewApi")
    private fun setToolbar(){
        toolbar_web_view_activity_layout.setTitle(R.string.title_with_font)
        setSupportActionBar(toolbar_web_view_activity_layout)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, SearchAndResultActivityView::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
