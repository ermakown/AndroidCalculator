package com.example.androidcalculator

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidcalculator.Data.Symbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class CalculatorViewModel: ViewModel() {

    private val _state: MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )

    val state = _state.asStateFlow()

    fun processCommand(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when(command) {
            CalculatorCommand.Clear -> {
                _state.value = CalculatorState.Initial
            }
            CalculatorCommand.Evaluate -> {
                when(Random.nextBoolean()) {
                    true -> _state.value = CalculatorState.Error("100/0")
                    false -> _state.value = CalculatorState.Success("100")
                }
            }
            is CalculatorCommand.Input -> {
                _state.value = CalculatorState.Input(
                    expression = command.symbol.name,
                    result = "100"
                )
            }
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
}