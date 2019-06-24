package com.lucaslprimo.thewatchernews.model.api

import com.lucaslprimo.thewatchernews.model.api.Entities.Article
import com.lucaslprimo.thewatchernews.model.api.Entities.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    fun getHeadlines(@Query("country") country:String, @Query("apiKey") apiKey:String): Call<List<Article>>

    @GET("/v2/everything")
    fun getNewsByQuery(@Query("q") query:String, @Query("apiKey") apiKey:String): Call<List<Article>>

    @GET("/v2/sources")
    fun getSources(@Query("apiKey") apiKey:String): Call<List<Source>>
}