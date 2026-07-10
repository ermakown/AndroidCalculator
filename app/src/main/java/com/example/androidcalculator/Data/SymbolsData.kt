package com.example.androidcalculator.Data

import android.icu.lang.UCharacter.DecompositionType.SUB
import android.icu.util.MeasureUnit.DOT
import org.mariuszgromada.math.mxparser.syntaxchecker.SyntaxCheckerConstants.FACTORIAL
import org.mariuszgromada.math.mxparser.syntaxchecker.SyntaxCheckerConstants.POWER

enum class Symbol(val displayText: String) {
    DIGIT_0("0"),
    DIGIT_1("1"),
    DIGIT_2("2"),
    DIGIT_3("3"),
    DIGIT_4("4"),
    DIGIT_5("5"),
    DIGIT_6("6"),
    DIGIT_7("7"),
    DIGIT_8("8"),
    DIGIT_9("9"),
    CLEAR("AC"),
    EVALUATE("="),
    ADD("+"),
    DELETE("⌫"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("÷"),
    PERCENT("%"),
    POWER("^"),
    FACTORIAL("!"),
    SQRT("√"),
    PI("π"),
    DOT(","),
    PARENTHESIS("( )");

    companion object {
        val mathSymbols = listOf(SQRT, PI, POWER, FACTORIAL)

        val firstList = listOf(CLEAR, PARENTHESIS, PERCENT, DIVIDE)
        val secondList = listOf(DIGIT_7, DIGIT_8, DIGIT_9, MULTIPLY)
        val thirdList = listOf(DIGIT_4, DIGIT_5, DIGIT_6, SUBTRACT)
        val fourthList = listOf(DIGIT_1, DIGIT_2, DIGIT_3, ADD)
        val fifthList = listOf(DIGIT_0, DOT, DELETE, EVALUATE)

        val blueSymbolBoxes = listOf(PARENTHESIS, PERCENT, DIVIDE, MULTIPLY, SUBTRACT, ADD, EVALUATE)
    }
}