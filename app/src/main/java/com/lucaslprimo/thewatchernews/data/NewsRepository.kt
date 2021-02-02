package com.lucaslprimo.thewatchernews.data

import com.lucaslprimo.thewatchernews.data.objects.ArticleNews
import com.lucaslprimo.thewatchernews.data.objects.ArticleResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsRepository {

    private const val CATEGORY_TECHNOLOGY = "ScienceAndTechnology"
    private const val CATEGORY_ENTERTAINMENT = "Entertainment"
    private const val CATEGORY_SPORTS = "Sports"
    private const val CATEGORY_HEALTH = "Health"
    private const val CATEGORY_BUSINESS = "Business"
    private const val CATEGORY_POLITICS = "Politics"
    private const val CATEGORY_WORLD = "World"

    private const val BASE_URL = "https://thewatchernewsapi.cognitiveservices.azure.com/bing/v7.0/"
    private const val API_KEY = "API_KEY"
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
                val newRequest = request.newBuilder().header(
                    API_KEY_NAME,
                    API_KEY
                )
                chain.proceed(newRequest.build())
            }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //interceptor.level = HttpLoggingInterceptor.Level.NONE
        clientBuilder.addInterceptor(interceptor)

        return clientBuilder.build()
    }

    fun getTrending():Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getTrendingTopics()?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getBreakingNews():Observable<List<ArticleNews>?>{
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

    fun getNewsTech(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_TECHNOLOGY, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsBusiness(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_BUSINESS, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsPolitics(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_POLITICS, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsHealth(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_HEALTH, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsEntertainment(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_ENTERTAINMENT, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsWorld(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_WORLD, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
                override fun onResponse(call: Call<ArticleResponse?>, response: Response<ArticleResponse?>) {
                    response.body()?.let { emitter.onNext(it.value) }
                }

                override fun onFailure(call: Call<ArticleResponse?>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

    fun getNewsSports(countryCode:String):Observable<List<ArticleNews>?>{
        return Observable.create { emitter ->
            newsApiService?.getNewsByCategoryAndCountry(CATEGORY_SPORTS, countryCode)?.enqueue( object: Callback<ArticleResponse?> {
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