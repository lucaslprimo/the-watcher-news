package com.lucaslprimo.thewatchernews.model

import com.lucaslprimo.thewatchernews.model.api.entities.ArticleNews
import com.lucaslprimo.thewatchernews.model.api.entities.ArticleResponse
import com.lucaslprimo.thewatchernews.model.api.NewsApiService
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsRepository {

    private const val BASE_URL = "https://thewatchernewsapi.cognitiveservices.azure.com/bing/v7.0/"
    private const val API_KEY = "api-key"
    private const val API_KEY_NAME = "Ocp-Apim-Subscription-Key"
    private var newsApiService: NewsApiService? = null

    init{
        val client = getHttpClient()

        val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        newsApiService = retrofit.create(NewsApiService::class.java)
    }

    private fun getHttpClient() : OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().header(API_KEY_NAME, API_KEY)
                chain.proceed(newRequest.build())
            }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //interceptor.level = HttpLoggingInterceptor.Level.NONE
        clientBuilder.addInterceptor(interceptor)

        return clientBuilder.build()
    }

    fun getLastNews(country:String): Observable<List<ArticleNews>?> {
        return Observable.create { emitter ->
            newsApiService?.getHeadlines()?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsByQuery(query:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByQuery(query)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }
}