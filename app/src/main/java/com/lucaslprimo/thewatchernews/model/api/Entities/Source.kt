package com.lucaslprimo.thewatchernews.model.api.Entities

import java.io.Serializable

class Source(var id:String,
             var name:String,
             var description:String,
             var url:String,
             var category:String,
             var language:String,
             var country:String) : Serializable