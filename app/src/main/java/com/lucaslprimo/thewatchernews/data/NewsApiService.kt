package com.lucaslprimo.thewatchernews.data

import com.lucaslprimo.thewatchernews.data.objects.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("news")
    fun getHeadlines(): Call<ArticleResponse>

    @GET("/news/trendingtopics")
    fun getTrendingTopics(): Call<ArticleResponse>

    @GET("news")
    fun getNewsByCategoryAndCountry(@Query("category")category: String, @Query("mkt")countryCode: String): Call<ArticleResponse>

}