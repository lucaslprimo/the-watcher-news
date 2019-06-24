package com.lucaslprimo.thewatchernews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel = HomeViewModel()

        homeViewModel.getTopHeadlines().observe(this, Observer{
        })
    }
}
