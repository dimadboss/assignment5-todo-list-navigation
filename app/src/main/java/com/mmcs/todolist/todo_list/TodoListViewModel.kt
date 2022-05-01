package com.mmcs.todolist.todo_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.random.Random

data class TodoModel(
    val id: UUID = UUID.fromString(""),
    val title: String = "",
    val description: String = "",
    val checked: Boolean = false
)

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val _todoItems = MutableLiveData<List<TodoModel>>(listOf())
    val todoItems: LiveData<List<TodoModel>>
        get() = _todoItems

    init {
        val rnd = Random(System.currentTimeMillis())
        _todoItems.value = IntRange(0, 100).map {
            TodoModel(
                id = UUID.randomUUID(),
                title = "Some title # $it",
                description = "Item detail info # $it. \n" +
                        "Some data: ${System.currentTimeMillis() % (42 * it + 1)}${rnd.nextBytes(100)}",
                checked = rnd.nextBoolean(),
            )
        }
    }
}