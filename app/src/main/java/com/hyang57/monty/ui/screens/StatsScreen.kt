package com.hyang57.monty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.hyang57.monty.game.ResultTable
import com.hyang57.monty.ui.GameState
import com.hyang57.monty.ui.NavDestination

object StatsDestination : NavDestination {
    override val route = "STATS"
    override val titleId = R.string.stats
}

// Screen for statistics
@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    gameState: GameState,
    onHome: (Int) -> Unit = {},
    onClear: (Int) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(48.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.game_results),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
            )
        }

        Column(
            modifier = Modifier.weight(4f)
        ) {
            ResultTable(winResult = gameState.winNum, roundNum = gameState.roundNum)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = { onHome(1) }
            ) {
                Text(stringResource(id = R.string.home))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { onClear(1) }
            ) {
                Text(stringResource(id = R.string.clear))
            }
        }
    }
}