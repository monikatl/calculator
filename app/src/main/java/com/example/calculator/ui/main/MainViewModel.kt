package com.example.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.Calculator

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    init {
        _result.value = "0"
    }

    fun onKeyClicked(char: Char) {
        Calculator.addChar(char)
        setResultValue()
    }

    fun clear() {
        _result.value = "0"
        Calculator.clear()
    }

    fun back() {
        Calculator.backLastDigit()
        setResultValue()
    }

    fun endOfCalculation() {
        if(Calculator.calculationText.last().isDigit()) {
            _result.value = Calculator.calculateResult().toString()
            Calculator.setCalculationText()
            Calculator.addCalculationToHistory()
        }
    }

    private fun setResultValue() {
        _result.value = Calculator.getCalculation()
    }

    fun convertToBin(){
        val value = result.value?.split(".")?.first()
        _result.value = Integer.toBinaryString(Integer.parseInt(value))
    }

    fun convertToHex() {
        val value = result.value?.split(".")?.first()
        _result.value = Integer.toHexString(Integer.parseInt(value))
    }

    fun convertToDec() {
        val value = result.value?.split(".")?.first()
        _result.value = value?.let { Integer.parseInt(it).toString() }
    }
}