package com.hyang57.monty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hyang57.monty.R
import com.hyang57.monty.ui.GameState
import com.hyang57.monty.ui.NavDestination

object SettingDestination : NavDestination {
    override val route = "SETTING"
    override val titleId = R.string.setting
}

// Screen for the settings
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    gameState: GameState,
    changeAmount: (Int) -> Unit = {},
    enableHint: (Boolean) -> Unit = {},
    onHome: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(PaddingValues(16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text("How many cards do you want?\n", style = MaterialTheme.typography.headlineSmall)

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(
                    selected = gameState.amount == 3,
                    onClick = { changeAmount(3) }
                )
                Text(
                    text = "3",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(
                    selected = gameState.amount == 4,
                    onClick = { changeAmount(4) }
                )
                Text(
                    text = "4",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(
                    selected = gameState.amount == 5,
                    onClick = { changeAmount(5) }
                )
                Text(
                    text = "5",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column {
            Text("Do you want to to enable hint?\n", style = MaterialTheme.typography.headlineSmall)

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(
                    selected = gameState.enableHint,
                    onClick = { enableHint(true) }
                )
                Text(
                    text = "Yes",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(
                    selected = !gameState.enableHint,
                    onClick = { enableHint(false) }
                )
                Text(
                    text = "No",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onHome(1)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 90.dp)
        ) {
            Text(stringResource(id = R.string.home))
        }
    }
}