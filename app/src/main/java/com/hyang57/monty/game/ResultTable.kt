package com.hyang57.monty.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyang57.monty.R

@Composable
fun ResultRow(
    description: String,
    value: String,
) {
    val style = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 21.sp,
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(
            description, style = style
        )
        Text(
            value, style = style
        )
    }
}

@Composable
fun ResultTable(
    winResult: Int,
    roundNum: Int,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        ResultRow(
            description = stringResource(id = R.string.win_result),
            value = winResult.toString()
        )
        ResultRow(
            description = stringResource(id = R.string.round_num),
            value = roundNum.toString()
        )
    }
}