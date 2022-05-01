package com.mmcs.todolist.todo_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmcs.todolist.databinding.TodoItemBinding


class TodoListAdapter(private val clickListener: (Int, TodoModel) -> Unit) :
    ListAdapter<TodoModel, TodoListAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TodoModel>() {
            override fun areContentsTheSame(
                oldItem: TodoModel,
                newItem: TodoModel
            ) = oldItem.checked == newItem.checked
                    && oldItem.title == newItem.title
                    && oldItem.description == newItem.description

            override fun areItemsTheSame(
                oldItem: TodoModel,
                newItem: TodoModel
            ) = oldItem.id == newItem.id
        }
    }

    inner class ViewHolder(
        private val binding: TodoItemBinding,
        private val listener: (Int, TodoModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) {
            binding.item = item
            binding.executePendingBindings()
            val position = layoutPosition
            val element = getItem(position)
            binding.root.setOnClickListener {
                listener(position, element)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, this@TodoListAdapter.clickListener)
    }
}
