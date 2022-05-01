package com.mmcs.todolist.item_detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mmcs.todolist.R
import com.mmcs.todolist.databinding.ItemDetailFragmentBinding


class ItemDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: ItemDetailFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_detail_fragment, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments: ItemDetailFragmentArgs by navArgs()
        view.findViewById<TextView>(R.id.title).text = arguments.title
        view.findViewById<TextView>(R.id.description).text = arguments.description
    }
}