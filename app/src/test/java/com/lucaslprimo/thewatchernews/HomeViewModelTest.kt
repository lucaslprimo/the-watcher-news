package com.lucaslprimo.thewatchernews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucaslprimo.thewatchernews.model.NewsRepository
import com.lucaslprimo.thewatchernews.model.api.Entities.Article
import com.lucaslprimo.thewatchernews.model.api.Entities.ArticleSource
import com.lucaslprimo.thewatchernews.model.api.Entities.Source
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsRepository:NewsRepository

    lateinit var homeViewModel:HomeViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(newsRepository)
    }

    @Test
    fun getHeadlines_shouldBringHeadlineNews(){
        val list:MutableList<Article> = ArrayList()
        list.add(Article(ArticleSource("1","NBC"),"Good News","Paul","The God's Kingdom is Coming, come and see.","http://teste.com.br","","",""))
        list.add(Article(ArticleSource("1","NBC"),"Good News","Paul","The God's Kingdom is Coming, come and see.","http://teste.com.br","","",""))

        val observable = Observable.just(list.toList())

        `when`(newsRepository.getTopHeadlines("")).thenReturn(observable)

        val topHeadlines = homeViewModel.getTopHeadlines()

        assert(topHeadlines.value == list)
        verify(newsRepository).getTopHeadlines("")
    }

    @Test
    fun getGeneralNewsByQuery_shouldBringGeneralNews(){
        val list:List<Article> = listOf(
                Article(ArticleSource("1","NBC"),"Good News","Paul","The God's Kingdom is Coming, come and see.","http://teste.com.br","","",""),
                Article(ArticleSource("1","NBC"),"Good News","Paul","The God's Kingdom is Coming, come and see.","http://teste.com.br","","","")
        )

        val observable = Observable.just(list)
        `when`(newsRepository.getNewsByQuery("")).thenReturn(observable)

        val generalNews = homeViewModel.getGeneralNews()

        assert(generalNews.value == list)
        verify(newsRepository).getNewsByQuery("")
    }

    @Test
    fun getSources_shouldBringNewsSources(){
        val list:MutableList<Source> = ArrayList()
        list.add(Source("nbc","NBC", "News and Documentaries", "http://nbc.com","news","eng","us"))
        list.add(Source("nbc2","NBC 2", "News and Documentaries", "http://nbc.com","news","eng","us"))

        val observable = Observable.just(list.toList())

        `when`(newsRepository.getSources()).thenReturn(observable)

        val sources = homeViewModel.getSources()

        assert(sources.value == list)
        verify(newsRepository).getSources()
    }
}