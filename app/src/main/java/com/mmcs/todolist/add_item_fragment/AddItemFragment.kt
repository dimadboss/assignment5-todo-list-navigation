package com.mmcs.todolist.add_item_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.mmcs.todolist.R
import com.mmcs.todolist.TodoModel
import com.mmcs.todolist.databinding.AddItemFragmentBinding
import java.util.*

class AddItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: AddItemFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_item_fragment, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonComplete).setOnClickListener {
            val title = view.findViewById<TextInputEditText>(R.id.textInputTitle).text.toString()
            if (title.isEmpty()) {
                return@setOnClickListener
            }
            val description =
                view.findViewById<TextInputEditText>(R.id.textInputDescription).text.toString()
            val newItem = TodoModel(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description,
                checked = false,
            )
            findNavController(view).navigate(
                AddItemFragmentDirections.actionAddItemFragmentToTodolistFragment(newItem)
            )
        }
    }
}