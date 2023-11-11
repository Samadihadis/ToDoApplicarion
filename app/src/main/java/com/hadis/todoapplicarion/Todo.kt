package com.hadis.todoapplicarion

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    var title : String,
    var description : String,
    var time : String,
    var date : String,
    var isDone : Boolean
)
