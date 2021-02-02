package com.lucaslprimo.thewatchernews.presentation.objects

data class Article (
    var category:String?,
    var title:String,
    var excerpt:String,
    var imageUrl:String?,
    var sourceName:String,
    var publishedDate:String,
    var contentUrl:String
)
