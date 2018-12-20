package com.example.asia.stackoverflowsearcher.licenses

import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.License

class LicensePresenter(private val licensesView: LicenseContract.View): LicenseContract.Presenter {

    override fun setFistScreen() {
        licensesView.setToolbar()
        licensesView.setRecyclerView(getLicenseList())
    }

    private fun getLicenseList(): List<License> {
        val licenseOne = License(
            1,
            licensesView.getContext().resources.getString(R.string.license_one_name),
            licensesView.getContext().resources.getString(R.string.license_one_author),
            licensesView.getContext().resources.getString(R.string.license_one_description),
            licensesView.getContext().resources.getString(R.string.license_one_url))

        val licenseTwo = License(
            2,
            licensesView.getContext().resources.getString(R.string.license_two_name),
            licensesView.getContext().resources.getString(R.string.license_two_author),
            licensesView.getContext().resources.getString(R.string.license_two_description),
            licensesView.getContext().resources.getString(R.string.license_two_url)
        )
        val licenseThree = License(
            3,
            licensesView.getContext().resources.getString(R.string.license_three_name),
            licensesView.getContext().resources.getString(R.string.license_three_author),
            licensesView.getContext().resources.getString(R.string.license_three_description),
            licensesView.getContext().resources.getString(R.string.license_three_url)
        )
        val licenseFour = License(
            4,
            licensesView.getContext().resources.getString(R.string.license_four_name),
            licensesView.getContext().resources.getString(R.string.license_four_author),
            licensesView.getContext().resources.getString(R.string.license_four_description),
            licensesView.getContext().resources.getString(R.string.license_four_url)
        )
        val licenseFive = License(
            5,
            licensesView.getContext().resources.getString(R.string.license_five_name),
            licensesView.getContext().resources.getString(R.string.license_five_author),
            licensesView.getContext().resources.getString(R.string.license_five_description),
            licensesView.getContext().resources.getString(R.string.license_five_url)
        )
        val licenseSix = License(
            6,
            licensesView.getContext().resources.getString(R.string.license_six_name),
            licensesView.getContext().resources.getString(R.string.license_six_author),
            licensesView.getContext().resources.getString(R.string.license_six_description),
            licensesView.getContext().resources.getString(R.string.license_six_url)
        )
        val licenseSeven = License(
            7,
            licensesView.getContext().resources.getString(R.string.license_seven_name),
            licensesView.getContext().resources.getString(R.string.license_seven_author),
            licensesView.getContext().resources.getString(R.string.license_seven_description),
            licensesView.getContext().resources.getString(R.string.license_seven_url)
        )
        val licenseEight = License(
            8,
            licensesView.getContext().resources.getString(R.string.license_eight_name),
            licensesView.getContext().resources.getString(R.string.license_eight_author),
            licensesView.getContext().resources.getString(R.string.license_eight_description),
            licensesView.getContext().resources.getString(R.string.license_eight_url)
        )

        return listOf(licenseOne, licenseTwo, licenseThree, licenseFour, licenseFive,
            licenseSix, licenseSeven, licenseEight)
    }
}