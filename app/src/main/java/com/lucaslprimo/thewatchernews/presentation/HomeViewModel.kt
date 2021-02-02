package com.lucaslprimo.thewatchernews.presentation

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucaslprimo.thewatchernews.model.Model
import com.lucaslprimo.thewatchernews.presentation.objects.Article
import com.lucaslprimo.thewatchernews.presentation.objects.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel: ViewModel {

    constructor()

    private var model: Model = Model()

    var newsTech = MutableLiveData<List<Article>>()
    var newsBusiness = MutableLiveData<List<Article>>()
    var newsPolitics = MutableLiveData<List<Article>>()
    var newsBreaking = MutableLiveData<List<Article>>()
    var newsSports = MutableLiveData<List<Article>>()
    var newsEntertainment = MutableLiveData<List<Article>>()
    var newsWorld = MutableLiveData<List<Article>>()
    val compositeDisposable = CompositeDisposable()

    @VisibleForTesting
    constructor(model: Model) {
        this.model = model
    }

    fun getPreferenceTabs(context: Context): LiveData<List<Category>> {
        val tabs: MutableLiveData<List<Category>> = MutableLiveData()

        tabs.value = model.getPreferenceTabs(context)

        return tabs
    }

    fun loadTech(category: Category, context: Context): MutableLiveData<List<Article>>{

        model.loadArticles(category, "pt_BR", context)
        if(newsTech.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsTech?.postValue(it) })
        }
        return newsTech
    }

    fun loadBusiness(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsBusiness.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsBusiness?.postValue(it) })
        }
        return newsBusiness
    }

    fun loadPolitics(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsPolitics.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsPolitics?.postValue(it) })
        }
        return newsPolitics
    }

    fun loadWorld(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsWorld.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsWorld?.postValue(it) })
        }
        return newsWorld
    }

    fun loadEntertainment(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsEntertainment.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsEntertainment?.postValue(it) })
        }
        return newsEntertainment
    }

    fun loadSports(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsSports.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsSports?.postValue(it) })
        }
        return newsSports
    }

    fun loadBreaking(category: Category, context: Context): MutableLiveData<List<Article>> {
        if(newsBreaking.value == null) {
            compositeDisposable.add(model.loadArticles(category, "pt_BR", context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> newsBreaking?.postValue(it) })
        }
        return newsBreaking
    }
}
