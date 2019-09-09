package com.lucaslprimo.thewatchernews.view

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucaslprimo.thewatchernews.model.NewsRepository
import com.lucaslprimo.thewatchernews.model.api.entities.ArticleNews
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel: ViewModel {

    private var newsRepository:NewsRepository = NewsRepository
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    constructor()

    @VisibleForTesting
    constructor(repo: NewsRepository){
        newsRepository = repo
    }

    fun getLastNews() : LiveData<List<ArticleNews>>{
        val topHeadlines: MutableLiveData<List<ArticleNews>> = MutableLiveData()
        compositeDisposable.add(this.newsRepository.getLastNews("").subscribe { list ->
            topHeadlines.value = list
        })

        return topHeadlines
    }

    fun getGeneralNews(): LiveData<List<ArticleNews>> {
        val queryNews: MutableLiveData<List<ArticleNews>> = MutableLiveData()
        compositeDisposable.add(newsRepository.getNewsByQuery("").subscribe { list ->
            queryNews.value = list
        })

        return queryNews
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}