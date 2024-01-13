package com.example.calculator


object Calculator {

    val history = mutableListOf<String>()

    var calculationText = "0"
    var currentCalculationParameter = mutableListOf<Char>()
    private var parameters = mutableListOf<Double>()
    private var operators = mutableListOf<Operation?>()
    private var result = 0.0


    fun addChar(char: Char) {
        if(calculationText == "next") {
            if(char.isDigit()) {
                calculationText = char.toString()
            } else {
                calculationText = result.toString() + char.toString()
                println(result)
            }
        } else {
            if(calculationText == "0"){
                calculationText = if(char == '.') "0."
                else if(operationPredicate(char)) "0$char"
                else char.toString()
            } else {
                if (Operation.LIST.contains(calculationText.last()) && Operation.LIST.contains(char)) { println("Zabroniona operacja!")}
                else calculationText += char

            }
        }
    }

    fun setCalculationText() {
        calculationText += " = $result"
    }

    private fun resolveOperation(char: Char): Operation? {
        return when(char) {
            '+' -> Add()
            '-' -> Sub()
            '*' -> Mul()
            '/' -> Div()
            '^' -> Pow()
            else -> null
        }
    }

    fun getCalculation() = calculationText

    fun clear(option: Boolean = true) {
        calculationText = "0"
        if(option) currentCalculationParameter.clear()
        operators.clear()
        parameters.clear()
        result = 0.0
    }

    fun backLastDigit() {
        calculationText = if(calculationText.length == 1)
            "0"
        else
            calculationText.dropLast(1)
    }


    fun calculateResult(): Double {

        val listOfArgs = calculationText.split("+", "-", "/", "*", "^").map { it.toDouble() }.toMutableList()
        val listOfOperations = calculationText.toList().filter { operationPredicate(it) }.map { resolveOperation(it) }.toMutableList()

        println("ListOfArgs $listOfArgs")
        println("ListOfArgs $listOfOperations")

        parameters = listOfArgs
        operators = listOfOperations

        var calculationResult: Double? = 0.0

       while (parameters.size > 1 && operators.size > 0) {
           println(parameters)
           val operation = operators[0]
           val a = parameters[0]
           val b = parameters[1]
           calculationResult = operation?.calculate(a, b)
           println(operation!!.javaClass)
           repeat(2) { parameters.remove(parameters[0]) }
           operators.removeAt(0)
           parameters.add(0, calculationResult!!)
       }
        calculationText.toMutableList().addAll(calculationResult.toString().toCharArray().asSequence())
        println(calculationText)
        result = parameters[0]
        parameters.clear()
        return result
    }

    fun getResult() = result

    fun addCalculationToHistory() {
        history.add(calculationText)
        calculationText = "next"
    }

    fun operationPredicate(char: Char) =  char == '+' || char == '^' || char == '-' || char == '*' || char == '/'

}