package com.example.androidcalculator

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.androidcalculator.Data.Symbol

class CalculatorViewModel {

    val state = mutableStateOf(
        Display(
            expression = "45x8",
            result = "360"
        )
    )

    fun processCommand(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when(command) {
            CalculatorCommand.Clear -> {
                state.value = Display("", "")
            }
            CalculatorCommand.Evaluate -> {}
            is CalculatorCommand.Input -> {}
        }
    }
}

sealed interface CalculatorCommand {
    data object Clear: CalculatorCommand
    data object Evaluate: CalculatorCommand
    data class Input(val symbol: Symbol): CalculatorCommand
}

data class Display (
    val expression: String,
    val result: String
)

