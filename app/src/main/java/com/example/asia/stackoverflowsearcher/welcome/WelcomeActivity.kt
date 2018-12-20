package com.example.asia.stackoverflowsearcher.welcome

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity

class WelcomeActivity : AppCompatActivity() {
    private val splashDisplayLength: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_view)

        Handler().postDelayed({
            val intent = Intent(this, SearchAndResultActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDisplayLength)
    }
}
