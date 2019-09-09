package com.lucaslprimo.thewatchernews.model.api.entities

data class ArticleResponse(
    var _type:String,
    var webSearchUrl:String,
    var value:List<ArticleNews>
)