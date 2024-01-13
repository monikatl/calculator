package com.example.calculator.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.R
import com.example.calculator.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.bin.setOnClickListener { viewModel.convertToBin() }
        binding.hex.setOnClickListener { viewModel.convertToHex() }
        binding.decimal.setOnClickListener { viewModel.convertToDec() }

        binding.history.setOnClickListener { goToHistoryFragment() }

        return binding.root
    }

    private fun goToHistoryFragment() {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.container, HistoryFragment())
        fragmentTransaction?.addToBackStack(null);
        fragmentTransaction?.commit()
    }
}