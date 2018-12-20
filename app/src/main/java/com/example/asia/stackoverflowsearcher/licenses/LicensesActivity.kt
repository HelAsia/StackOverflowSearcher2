package com.example.asia.stackoverflowsearcher.licenses

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.License
import com.example.asia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity
import kotlinx.android.synthetic.main.activity_licenses_view.*

class LicensesActivity : AppCompatActivity(), LicenseContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_licenses_view)

        val presenter: LicenseContract.Presenter = LicensePresenter(this)
        presenter.setFistScreen()
    }

    override fun getContext(): Context = this

    override fun onBackPressed() {
        val intent = Intent(this, SearchAndResultActivity::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("NewApi")
    override fun setToolbar() {
        toolbar_licenses.setTitle(R.string.title_with_font)
        setSupportActionBar(toolbar_licenses)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun setRecyclerView(licenseList: List<License>) {
        val adapter = LicenseAdapter(licenseList) { license: License -> goToLicenseSource(license) }
        licencesDisplayRecyclerView.adapter = adapter
        licencesDisplayRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun goToLicenseSource(license: License) {
        val intent = Intent(this, LicenseWebViewActivity::class.java)
        intent.putExtra("url", license.licenseUri)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, SearchAndResultActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
