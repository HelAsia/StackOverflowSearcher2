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

class ResultCardsAdapter(private val itemList: List<Item?>?,
                         private val clickListener: (Item) -> Unit): RecyclerView.Adapter<ResultCardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ResultCardsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.one_result_card, parent, false)
        return ResultCardsAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(itemList!![position], clickListener)
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
        fun bind(item: Item?, clickListener: (Item) -> Unit){
            itemView.title.text = item?.title
            itemView.user_name.text = item?.owner?.displayName
            Picasso.get().load(item?.owner?.profileImage).into(itemView.avatar)
            itemView.answer_count.text = item?.answerCount.toString()
            if(item != null){
                itemView.setOnClickListener{ clickListener(item)}
            }
        }
    }
}