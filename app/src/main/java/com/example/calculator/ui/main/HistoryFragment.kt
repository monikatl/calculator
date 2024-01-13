package com.example.calculator.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.calculator.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, viewModel.history.value.reversed())
        binding.historyList.adapter = arrayAdapter

        return binding.root
    }


}