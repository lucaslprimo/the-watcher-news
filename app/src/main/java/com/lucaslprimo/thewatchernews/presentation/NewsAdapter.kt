package com.lucaslprimo.thewatchernews.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucaslprimo.thewatchernews.R
import com.lucaslprimo.thewatchernews.presentation.objects.Article
import kotlinx.android.synthetic.main.item_news.view.*


class NewsAdapter(val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    var items:List<Article>? = null
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_news_small_pic, parent, false))
    }

    override fun getItemCount(): Int {
        if(items!=null)
            return items!!.size
        else return 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage = itemView.iv_image
        private val txtCategory = itemView.txt_category
        private val txtHeadline = itemView.txt_headline
        private val txtDescription = itemView.txt_description
        private val txtDate = itemView.txt_time
        private val txtProvider = itemView.txt_provider

        @SuppressLint("SimpleDateFormat")
        fun bind(article: Article){
            txtCategory?.text = article.category
            txtDescription?.text = article.excerpt
            txtHeadline?.text = article.title
            txtProvider?.text = article.sourceName

            article.imageUrl.let {
                Glide.with(itemView.context)
                    .load(article.imageUrl)
                    .into(ivImage)
            }

            if(article.imageUrl == null){
                ivImage.visibility = View.GONE;
            }

            txtDate.text = article.publishedDate

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,WebviewActivity::class.java)
                intent.putExtra("url",article.contentUrl)
                itemView.context.startActivity(intent)
            }
        }
    }

}

