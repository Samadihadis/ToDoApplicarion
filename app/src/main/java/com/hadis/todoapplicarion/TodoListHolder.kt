package com.hadis.todoapplicarion

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class TodoListHolder(
    val todoList : PersistentList<Todo> = persistentListOf()
)
