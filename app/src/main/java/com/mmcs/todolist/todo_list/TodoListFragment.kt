package com.mmcs.todolist.todo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mmcs.todolist.R
import com.mmcs.todolist.databinding.TodolistFragmentBinding
import com.mmcs.todolist.item_detail_fragment.ItemDetailFragmentArgs

class TodoListFragment : Fragment() {
    private val viewModel: TodoListViewModel by lazy {
        ViewModelProvider(this)[TodoListViewModel::class.java]
    }
    private val adapter by lazy {
        TodoListAdapter { _, item ->
            findNavController().navigate(
                TodoListFragmentDirections.actionTodolistFragmentToItemDetailFragment(
                    item.title,
                    item.description
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: TodolistFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.todolist_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
        viewModel.todoItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.AddButton).setOnClickListener {
            findNavController().navigate(
                TodoListFragmentDirections.actionTodolistFragmentToAddItemFragment()
            )
        }
    }
}