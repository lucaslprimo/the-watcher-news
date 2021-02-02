package com.lucaslprimo.thewatchernews.data.objects

data class ArticleResponse(
    var _type:String,
    var webSearchUrl:String,
    var value:List<ArticleNews>
)