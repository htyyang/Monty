package com.hyang57.monty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyang57.monty.R
import com.hyang57.monty.ui.NavDestination

object StatsDestination : NavDestination {
    override val route = "STATS"
    override val titleId = R.string.stats
}

@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    answers: List<Boolean>,
    onHome: (Int) -> Unit = {},
) {

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(48.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.game_results),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                modifier = modifier
            )
        }

        Column(
            modifier = Modifier.weight(4f)
        ) {
            ResultTable(answers = answers)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.weight(1f)
        ) {
            Button(
                onClick = newQuiz
            ) {
                Text(stringResource(id = R.string.new_quiz))
            }
        }
    }
}