package com.example.androidcalculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calculator
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme


val expression = mutableStateOf("45x8")
val expressionBottom = mutableStateOf("360")
@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
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
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 40.dp, start = 40.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = expression.value,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = expressionBottom.value,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RowMathSymbols(listOfMathSymbols = mathSymbols)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
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
    AndroidCalculatorTheme() {
        Calculator()
    }
}
@Composable
private fun MathSymbols(
    modifier: Modifier,
    symbol: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowMathSymbols(
    listOfMathSymbols: List<String>
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
    symbol: String,
    boxColor: Color
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                Log.d("Calculator", "Button $symbol is clicked")
                checkACButton(symbol)
            }
            .background(boxColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = symbol,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowsCalcButton(
    modifier: Modifier = Modifier,
    listSymbols: List<String>
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for(i in listSymbols){
            val color: Color
            if(i in blueSymbolBoxes) {
                color = MaterialTheme.colorScheme.tertiary
            }
            else if (i == "AC") {
                color = MaterialTheme.colorScheme.secondary
            }
            else {
                color = MaterialTheme.colorScheme.primary
            }

            CalcButton(
                modifier = Modifier
                    .weight(if(i == "0") 2f else 1f)
                    .aspectRatio(if(i == "0") 2f else 1 / 1f),
                symbol = i,
                boxColor = color
            )
        }
    }
}

private fun checkACButton(symbol: String) {
    when(symbol) {
        "AC" -> {
            expression.value = ""
            expressionBottom.value = ""
        }
    }
}

