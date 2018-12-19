package com.example.asia.stackoverflowsearcher.licenses

import android.content.Context
import com.example.asia.stackoverflowsearcher.data.model.License

interface LicenseContract {
    interface View {
        fun getContext(): Context
        fun setToolbar()
        fun setRecyclerView(licenseList: List<License>)
    }

    interface Presenter {
        fun setFistScreen()
    }
}