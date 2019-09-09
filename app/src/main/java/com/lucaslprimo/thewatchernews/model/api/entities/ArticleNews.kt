package com.lucaslprimo.thewatchernews.model.api.entities

data class ArticleNews(
    val category: String,
    val datePublished: String,
    val description: String,
    val headline: Boolean,
    val image: Image,
    val name: String,
    val provider: List<Provider>,
    val url: String
)