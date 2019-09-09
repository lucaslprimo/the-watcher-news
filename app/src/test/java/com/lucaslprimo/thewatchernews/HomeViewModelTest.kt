package com.lucaslprimo.thewatchernews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucaslprimo.thewatchernews.model.NewsRepository
import com.lucaslprimo.thewatchernews.model.api.entities.Article
import com.lucaslprimo.thewatchernews.model.api.entities.ArticleSource
import com.lucaslprimo.thewatchernews.model.api.entities.Source
import com.lucaslprimo.thewatchernews.view.HomeViewModel
import io.reactivex.Observable
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

    lateinit var homeViewModel: HomeViewModel

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

        `when`(newsRepository.getLastNews()).thenReturn(observable)

        val topHeadlines = homeViewModel.getLastNews()

        assert(topHeadlines.value == list)
        verify(newsRepository).getLastNews()
    }
}