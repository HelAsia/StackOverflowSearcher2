package com.example.asia.stackoverflowsearcher.details

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setToolbar()
        setWebView(getUrl())
    }

    override fun onBackPressed(){
        val intent = Intent(this, SearchAndResultActivity::class.java)
        startActivity(intent)
        finish()
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
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, SearchAndResultActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
