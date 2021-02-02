package com.lucaslprimo.thewatchernews.model

import android.content.Context
import androidx.preference.PreferenceManager
import com.lucaslprimo.thewatchernews.data.NewsRepository
import com.lucaslprimo.thewatchernews.data.objects.ArticleNews
import com.lucaslprimo.thewatchernews.model.utils.TimeUtils
import com.lucaslprimo.thewatchernews.presentation.objects.Article
import com.lucaslprimo.thewatchernews.presentation.objects.Category
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Model {

    fun getPreferenceTabs(context:Context):List<Category>{

        val newsTabList:MutableList<Category> = ArrayList()

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        if(prefs.getBoolean("breaking", true)){
            newsTabList.add(Category.BREAKINGNEWS)
        }

        if(prefs.getBoolean("entertain", true)) {
            newsTabList.add(Category.ENTERTAINMENT)
        }

        if(prefs.getBoolean("tech", true)){
            newsTabList.add(Category.TECHNOLOGY)
        }

        if(prefs.getBoolean("sports", true)){
            newsTabList.add(Category.SPORTS)
        }

        if(prefs.getBoolean("business", true)) {
            newsTabList.add(Category.BUSINESS)
        }

        if(prefs.getBoolean("world", true)) {
            newsTabList.add(Category.WORLD)
        }

        if(prefs.getBoolean("politics", true)) {
            newsTabList.add(Category.POLITICS)
        }


        return newsTabList
    }

    fun loadArticles(category: Category, countryCode:String, context: Context) : Single<List<Article>>{
        return Single.create { emitter ->
            when(category){
                Category.ENTERTAINMENT -> {
                    NewsRepository.getNewsEntertainment(countryCode).subscribe {
                            newsResponse: List<ArticleNews>? ->
                        fillArticleList(newsResponse, context, emitter)
                    }
                }
                Category.TECHNOLOGY -> {
                    NewsRepository.getNewsTech(countryCode).subscribe {
                            newsResponse: List<ArticleNews>? ->
                        fillArticleList(newsResponse, context, emitter)
                    }
                }
                Category.BUSINESS ->  {
                    NewsRepository.getNewsBusiness(countryCode).subscribe {
                            newsResponse: List<ArticleNews>? ->
                        fillArticleList(newsResponse, context, emitter)
                    }
                }
                Category.WORLD -> {
                    NewsRepository.getNewsWorld(countryCode)
                        .subscribe { newsResponse: List<ArticleNews>? ->
                            fillArticleList(newsResponse, context, emitter)
                        }
                }
                Category.SPORTS -> {
                    NewsRepository.getNewsSports(countryCode)
                        .subscribe { newsResponse: List<ArticleNews>? ->
                            fillArticleList(newsResponse, context, emitter)
                        }
                }
                Category.POLITICS -> {
                    NewsRepository.getNewsPolitics(countryCode)
                        .subscribe { newsResponse: List<ArticleNews>? ->
                            fillArticleList(newsResponse, context, emitter)
                        }
                }
                Category.BREAKINGNEWS -> {
                    NewsRepository.getBreakingNews()
                        .subscribe { newsResponse: List<ArticleNews>? ->
                            fillArticleList(newsResponse, context, emitter)
                        }
                }
            }
        }
    }

    private fun fillArticleList (
        newsResponse: List<ArticleNews>?,
        context: Context,
        emitter: SingleEmitter<List<Article>>
    ){
        val listResult = ArrayList<Article>()

        newsResponse?.let {
            for (articleResponse in newsResponse) {
                listResult.add(getArticleFromArticleResponse(articleResponse, context))
            }
            emitter.onSuccess(listResult)
        }
    }

    private fun getArticleFromArticleResponse(articleResponse: ArticleNews, context: Context) : Article {
        var providerName: String
        articleResponse.provider.let { it ->
            it[0].let {
                providerName = it.name
            }
        }

        var datePublished:String
        val dateFormat = SimpleDateFormat(TimeUtils.DATE_API)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        dateFormat.parse(articleResponse.datePublished).let {
            datePublished = TimeUtils.getTimeAgo(it, context)
        }

        return Article(
            articleResponse.category,
            articleResponse.name,
            articleResponse.description,
            articleResponse.image?.thumbnail?.contentUrl,
            providerName,
            datePublished,
            articleResponse.url
        )
    }

}