package com.lucaslprimo.thewatchernews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucaslprimo.thewatchernews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel

    var rvNews:RecyclerView? = null
    var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNews = this.rv_news

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.getLastNews().observe(this, Observer{

            newsAdapter = NewsAdapter(it,this)
            rvNews?.adapter = newsAdapter
            rvNews?.layoutManager = LinearLayoutManager(this)
        })
    }
}
