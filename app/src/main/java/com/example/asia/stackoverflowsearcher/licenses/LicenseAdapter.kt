package com.example.asia.stackoverflowsearcher.licenses

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.License
import kotlinx.android.synthetic.main.one_license_card.view.*

class LicenseAdapter(private val licensesList: List<License>,
                     private val clickListener: (License) -> Unit):
        RecyclerView.Adapter<LicenseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = layoutInflater.inflate(R.layout.one_license_card, parent, false)
        return LicenseAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int = licensesList.size


    override fun onBindViewHolder(holder: LicenseAdapter.ViewHolder, position: Int) {
        holder.bind(licensesList[position], clickListener)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun animateCircularReveal(view: View) {
        val centerX = 0
        val centerY = 0
        val startRadius = 0
        val endRadius = Math.max(view.width, view.height)
        val animation = ViewAnimationUtils
            .createCircularReveal(view, centerX, centerY, startRadius.toFloat(), endRadius.toFloat())
        view.visibility = View.VISIBLE
        animation.start()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onViewAttachedToWindow(viewHolder: LicenseAdapter.ViewHolder) {
        super.onViewAttachedToWindow(viewHolder)
        animateCircularReveal(viewHolder.itemView)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(license: License, clickListener: (License) -> Unit){
            itemView.licenseName.text = license.licenseName
            itemView.licenseAuthor.text = license.licenseAuthor
            itemView.licenseDescription.text = license.licenseDescription
            itemView.setOnClickListener{ clickListener(license)}
        }
    }
}