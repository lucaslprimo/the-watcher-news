package com.lucaslprimo.thewatchernews.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucaslprimo.thewatchernews.R
import com.lucaslprimo.thewatchernews.model.api.entities.ArticleNews
import com.lucaslprimo.thewatchernews.utils.TimeUtils
import kotlinx.android.synthetic.main.item_news.view.*
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(val items:List<ArticleNews>, val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_news_small_pic, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage = itemView.iv_image
        private val txtCategory = itemView.txt_category
        private val txtHeadline = itemView.txt_headline
        private val txtDescription = itemView.txt_description
        private val txtDate = itemView.txt_time
        private val txtProvider = itemView.txt_provider

        @SuppressLint("SimpleDateFormat")
        fun bind(articleNews: ArticleNews){
            txtCategory?.text = articleNews.category
            txtDescription?.text = articleNews.description
            txtHeadline?.text = articleNews.name
            articleNews.provider[0].let {
                txtProvider?.text = it.name
            }

            Glide.with(itemView.context)
                .load(articleNews.image.thumbnail.contentUrl)
                .into(ivImage)

            val dateFormat = SimpleDateFormat(TimeUtils.DATE_API)
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")

            dateFormat.parse(articleNews.datePublished).let {
                txtDate.text = TimeUtils.getTimeAgo(it,itemView.context)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,WebviewActivity::class.java)
                intent.putExtra("url",articleNews.url)
                itemView.context.startActivity(intent)
            }
        }
    }

}

