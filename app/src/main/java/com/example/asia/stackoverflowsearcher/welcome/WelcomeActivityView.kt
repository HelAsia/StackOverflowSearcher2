package com.example.asia.stackoverflowsearcher.welcome

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.search_with_results.SearchAndResultActivityView

class WelcomeActivityView : AppCompatActivity() {
    private val splashDisplayLength: Long = 1500
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_view)

        context = applicationContext

        Handler().postDelayed({
            val intent = Intent(context, SearchAndResultActivityView::class.java)
            startActivity(intent)
            finish()
        }, splashDisplayLength)
    }
}
