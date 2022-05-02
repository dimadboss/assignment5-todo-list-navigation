package com.mmcs.todolist.todo_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mmcs.todolist.TodoModel
import java.util.*


class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val _todoItems = MutableLiveData<List<TodoModel>>(listOf())
    val todoItems: LiveData<List<TodoModel>>
        get() = _todoItems

    fun insertItem(item: TodoModel) {
        if (_todoItems.value?.any { it -> it.id == item.id } == true) {
            return
        }
        val newList: MutableList<TodoModel> = ArrayList()
        newList.add(item)
        newList.addAll(_todoItems.value ?: newList)
        _todoItems.value = newList.toList()
    }

    init {
        if (_todoItems.value.isNullOrEmpty()) {
            _todoItems.value = IntRange(0, 5).map {
                TodoModel(
                    id = UUID.randomUUID().toString(),
                    title = "Some title # $it",
                    description = "Item detail info # $it.",
                    checked = (it % 5 == 3 || it % 7 == 1)
                )
            }
        }
    }
}