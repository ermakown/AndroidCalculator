package com.example.androidcalculator

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidcalculator.Data.Symbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.exp
import kotlin.random.Random

class CalculatorViewModel: ViewModel() {

    private val _state: MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )

    val state = _state.asStateFlow()

    private var expression = ""

    fun processCommand(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when(command) {
            CalculatorCommand.Clear -> {
                expression = ""
                _state.value = CalculatorState.Initial
            }
            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                if (result != null) {
                    _state.value = CalculatorState.Success(result)
                } else {
                    _state.value = CalculatorState.Error(expression)
                }
            }
            is CalculatorCommand.Input -> {
                val symbol = if (command.symbol != Symbol.PARENTHESIS) {
                    command.symbol.displayText
                } else {
                    getCorrectParenthesis()
                }
                expression += symbol
                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
            is CalculatorCommand.Delete -> {
                expression = delete()
                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
        }
    }

    private fun evaluate(): String? {
        return expression
            .replace('x', '*')
            .replace(',', '.')
            .let{ Expression(it) }
            .calculate()
            .takeIf { it.isFinite() } ?.toString()
    }

    private fun delete(): String {
        return expression.dropLast(1)
    }

    private fun getCorrectParenthesis(): String {
        val openCount = expression.count { it == '('}
        val closeCount = expression.count { it == ')' }
        return when {
            expression.isEmpty() -> "("
            !expression.last().isDigit() && expression.last() != ')' && expression.last() != 'π'
                -> "("
            openCount > closeCount -> ")"
            else -> "("
        }
    }
}

sealed interface CalculatorState {

    data object Initial: CalculatorState

    data class Input(
        val expression: String,
        val result: String,
    ): CalculatorState

    data class Error(val error: String): CalculatorState

    data class Success(val result: String): CalculatorState
}

sealed interface CalculatorCommand {
    data object Clear: CalculatorCommand
    data object Evaluate: CalculatorCommand
    data class Input(val symbol: Symbol): CalculatorCommand
    data object Delete: CalculatorCommand
}