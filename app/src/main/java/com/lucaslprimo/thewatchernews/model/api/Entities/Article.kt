package com.lucaslprimo.thewatchernews.model.api.Entities

import java.io.Serializable

class Article(var source: ArticleSource,
              var title: String?,
              var author:String?,
              var description:String?,
              var url:String?,
              var urlToImage:String?,
              var publishedAt:String?,
              var content:String?) :Serializable