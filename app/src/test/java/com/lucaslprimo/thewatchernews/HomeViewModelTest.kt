package com.lucaslprimo.thewatchernews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucaslprimo.thewatchernews.model.NewsRepository
import com.lucaslprimo.thewatchernews.model.api.entities.*
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
        val list:MutableList<ArticleNews> = ArrayList()
        list.add(ArticleNews("","","",true, Image(Thumbnail("",0,0)),"",ArrayList(),""))
        list.add(ArticleNews("","","",true, Image(Thumbnail("",0,0)),"",ArrayList(),""))

        val observable = Observable.just(list.toList())

        `when`(newsRepository.getLastNews("")).thenReturn(observable)

        val topHeadlines = homeViewModel.getLastNews()

        assert(topHeadlines.value == list)
        verify(newsRepository).getLastNews("")
    }
}