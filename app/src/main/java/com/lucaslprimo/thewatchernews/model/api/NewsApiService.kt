package com.lucaslprimo.thewatchernews.model.api

import com.lucaslprimo.thewatchernews.model.api.entities.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("news")
    fun getHeadlines(): Call<ArticleResponse>

    @GET("/v2/everything")
    fun getNewsByQuery(@Query("q") query: String): Call<ArticleResponse>

}