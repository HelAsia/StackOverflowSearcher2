package com.example.asia.stackoverflowsearcher.searchWithResults

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.asia.stackoverflowsearcher.R
import kotlinx.android.synthetic.main.fragment_details_fragment_view.*

class ResultDetailsFragment: Fragment() {
    private var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details_fragment_view, container, false)
        setView(view)
        setWebView(getUrl())
        return view
    }

    override fun getView(): View? {
        return fragmentView
    }

    private fun setView(view: View) {
        this.fragmentView = view
    }

    private fun getUrl(): String? {
        return arguments?.getString("url") ?: "https://stackoverflow.com/"
    }

    private fun setWebView(url: String?){
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)
    }
}