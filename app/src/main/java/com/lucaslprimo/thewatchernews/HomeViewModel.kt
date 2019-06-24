package com.lucaslprimo.thewatchernews

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucaslprimo.thewatchernews.model.NewsRepository
import com.lucaslprimo.thewatchernews.model.api.Entities.Article
import com.lucaslprimo.thewatchernews.model.api.Entities.Source
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel: ViewModel {

    private var newsRepository:NewsRepository
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    constructor(){
        newsRepository = NewsRepository
    }

    @VisibleForTesting
    constructor(repo: NewsRepository){
        newsRepository = repo
    }

    fun getTopHeadlines() : LiveData<List<Article>>{
        val topHeadlines: MutableLiveData<List<Article>> = MutableLiveData()
        compositeDisposable.add(this.newsRepository.getTopHeadlines("us").subscribe { list ->
            topHeadlines.value = list
        })

        return topHeadlines
    }

    fun getGeneralNews(): LiveData<List<Article>> {
        val queryNews: MutableLiveData<List<Article>> = MutableLiveData()
        compositeDisposable.add(newsRepository.getNewsByQuery("").subscribe { list ->
            queryNews.value = list
        })

        return queryNews
    }

    fun getSources() : LiveData<List<Source>> {
        val sources: MutableLiveData<List<Source>> = MutableLiveData()
        compositeDisposable.add(this.newsRepository.getSources().subscribe { list ->
            sources.value = list
        })

        return sources
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}