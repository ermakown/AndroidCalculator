package com.example.androidcalculator

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme

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
                    text = "45x8",
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "360",
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
            val mathSymbols = listOf("√", "π", "^", "!")
            RowMathSymbols(listOfMathSymbols = mathSymbols)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val firstList = listOf("AC", "( )", "%", "÷")
                val secondList = listOf("7", "8", "9", "X")
                val thirdList = listOf("4", "5", "6", "-")
                val fourthList = listOf("1", "2", "3", "+")
                val fifthList = listOf("0", ",", "=")
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
    val blueSymbolBoxes = listOf("( )", "%", "÷", "X", "-", "+", "=")
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
