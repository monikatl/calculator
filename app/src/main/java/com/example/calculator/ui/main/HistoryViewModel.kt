package com.example.calculator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.Calculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val _history = MutableStateFlow<List<String>>(emptyList())
    val history: StateFlow<List<String>>
        get() = _history

    init {
        viewModelScope.launch {
            _history.emit(Calculator.history)
        }
    }
}