package com.lucaslprimo.thewatchernews.model

import com.lucaslprimo.thewatchernews.model.api.Entities.Article
import com.lucaslprimo.thewatchernews.model.api.Entities.Source
import com.lucaslprimo.thewatchernews.model.api.NewsApiService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NewsRepository {

    private const val BASE_URL = "https://newsapi.org"
    private const val API_KEY = "3cf43ec9b9124bf981e121fdcc1b0f44"

    var newsApiService: NewsApiService? = null

    init{
        val client = getHttpLogger()

        val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        newsApiService = retrofit.create(NewsApiService::class.java)
    }

    private fun getHttpLogger() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun getTopHeadlines(country:String): Observable<List<Article>?> {
        return Observable.create { emitter ->
            newsApiService?.getHeadlines(country,API_KEY)?.enqueue( object: Callback<List<Article>?> {
                override fun onResponse(call: Call<List<Article>?>, response: Response<List<Article>?>) {
                    response.body()?.let { emitter.onNext(it) }
                }

                override fun onFailure(call: Call<List<Article>?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsByQuery(query:String):Observable<List<Article>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByQuery(query,API_KEY)?.enqueue( object: Callback<List<Article>?> {
                override fun onResponse(call: Call<List<Article>?>, response: Response<List<Article>?>) {
                    response.body()?.let { emitter.onNext(it) }
                }

                override fun onFailure(call: Call<List<Article>?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getSources(): Observable<List<Source>?> {
        return Observable.create { emitter ->
            newsApiService?.getSources(API_KEY)?.enqueue( object: Callback<List<Source>?> {
                override fun onResponse(call: Call<List<Source>?>, response: Response<List<Source>?>) {
                    response.body()?.let { emitter.onNext(it) }
                }

                override fun onFailure(call: Call<List<Source>?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }
}