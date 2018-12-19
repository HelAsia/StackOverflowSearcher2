package com.example.asia.stackoverflowsearcher.licenses

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebViewClient
import com.example.asia.stackoverflowsearcher.R
import kotlinx.android.synthetic.main.activity_web_view.*

class LicenseWebViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setToolbar()
        setWebView()
    }

    override fun onBackPressed(){
        val intent = Intent(this, LicensesActivity::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("NewApi")
    private fun setToolbar() {
        toolbar_web_view_activity_layout.setTitle(R.string.title_with_font)
        setSupportActionBar(toolbar_web_view_activity_layout)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    private fun setWebView() {
        val url: String = intent.getStringExtra("url")
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, LicensesActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}