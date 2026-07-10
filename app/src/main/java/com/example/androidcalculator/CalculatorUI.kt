package com.example.androidcalculator

import android.R.attr.fontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcalculator.Data.Symbol
import com.example.androidcalculator.Data.Symbol.Companion.blueSymbolBoxes
import com.example.androidcalculator.Data.Symbol.Companion.fifthList
import com.example.androidcalculator.Data.Symbol.Companion.firstList
import com.example.androidcalculator.Data.Symbol.Companion.fourthList
import com.example.androidcalculator.Data.Symbol.Companion.mathSymbols
import com.example.androidcalculator.Data.Symbol.Companion.secondList
import com.example.androidcalculator.Data.Symbol.Companion.thirdList
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 40.dp,
                        bottomStart = 40.dp
                    )
                )
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .verticalScroll(scrollState),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 40.dp, start = 40.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                when(val currentState = state.value) {
                    is CalculatorState.Error -> {
                        Text(
                            text = currentState.error,
                            lineHeight = 36.sp,
                            fontSize = 36.sp,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "",
                            lineHeight = 17.sp,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    CalculatorState.Initial -> {}
                    is CalculatorState.Input -> {
                        Text(
                            text = currentState.expression,
                            lineHeight = 36.sp,
                            fontSize = 36.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = currentState.result,
                            lineHeight = 17.sp,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    is CalculatorState.Success -> {
                        Text(
                            text = currentState.result,
                            lineHeight = 36.sp,
                            fontSize = 36.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "",
                            lineHeight = 17.sp,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            RowMathSymbols(listOfMathSymbols = mathSymbols)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RowsCalcButton(listSymbols = firstList)
                RowsCalcButton(listSymbols = secondList)
                RowsCalcButton(listSymbols = thirdList)
                RowsCalcButton(listSymbols = fourthList)
                RowsCalcButton(listSymbols = fifthList)
            }
        }
    }
}

@Preview
@Composable
private fun CalculatorPreview() {
    AndroidCalculatorTheme {
        Calculator()
    }
}
@Composable
private fun MathSymbols(
    modifier: Modifier,
    symbol: Symbol,
    viewModel: CalculatorViewModel = viewModel()
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                viewModel.processCommand(CalculatorCommand.Input(symbol))
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol.displayText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowMathSymbols(
    listOfMathSymbols: List<Symbol>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for(i in listOfMathSymbols) {
            MathSymbols(
                modifier = Modifier
                    .weight(1f),
                symbol = i
            )
        }
    }
}

@Composable
private fun CalcButton(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel(),
    symbol: Symbol,
    boxColor: Color
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                val command = when(symbol) {
                    Symbol.CLEAR -> CalculatorCommand.Clear
                    Symbol.EVALUATE -> CalculatorCommand.Evaluate
                    Symbol.DELETE -> CalculatorCommand.Delete
                    else -> CalculatorCommand.Input(symbol)
                }
                viewModel.processCommand(command)
            }
            .background(boxColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = symbol.displayText,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowsCalcButton(
    modifier: Modifier = Modifier,
    listSymbols: List<Symbol>
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for(i in listSymbols){
            val color: Color = when (i) {
                in blueSymbolBoxes -> {
                    MaterialTheme.colorScheme.tertiary
                }
                Symbol.CLEAR -> {
                    MaterialTheme.colorScheme.secondary
                }
                else -> {
                    MaterialTheme.colorScheme.primary
                }
            }

            CalcButton(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1 / 1f),
                symbol = i,
                boxColor = color
            )
        }
    }
}



