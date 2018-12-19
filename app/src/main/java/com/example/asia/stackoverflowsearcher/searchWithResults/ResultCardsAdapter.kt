package com.example.asia.stackoverflowsearcher.searchWithResults

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import com.example.asia.stackoverflowsearcher.R
import com.example.asia.stackoverflowsearcher.data.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.one_result_card.view.*

class ResultCardsAdapter(var itemList: List<Item?>?): RecyclerView.Adapter<ResultCardsAdapter.ViewHolder>() {
    private var callbackWebView: OnShareWebViewDetailsListener? = null

    fun setCallbackWebViewOnShareClickedListener(callbackWebView: OnShareWebViewDetailsListener){
        this.callbackWebView = callbackWebView
    }

    interface OnShareWebViewDetailsListener{
        fun shareCardClicked(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ResultCardsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.one_result_card, parent, false)
        return ResultCardsAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(itemList?.get(position)!!)

        viewHolder.itemView.one_result_card_layout.setOnClickListener {
            val url = itemList?.get(position)!!.link
            callbackWebView?.shareCardClicked(url)
        }
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun animateCircularReveal(view: View?){
        val centerX = 0
        val centerY = 0
        val startRadius = 0
        val endRadius = Math.max(view!!.width, view.height)

        val animation = ViewAnimationUtils
            .createCircularReveal(view, centerX, centerY, startRadius.toFloat(), endRadius.toFloat())

        view.visibility = View.VISIBLE
        animation.start()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onViewAttachedToWindow(viewHolder: ViewHolder) {
        super.onViewAttachedToWindow(viewHolder)
        animateCircularReveal(viewHolder.itemView)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: Item){
            val titleQuery = item.title
            val userName= item.owner?.displayName
            val avatarUrl = item.owner?.profileImage
            val answerCount = item.answerCount

            itemView.title.text = titleQuery
            itemView.user_name.text = userName
            Picasso.get().load(avatarUrl).into(itemView.avatar)
            itemView.answer_count.text = answerCount.toString()
        }
    }
}