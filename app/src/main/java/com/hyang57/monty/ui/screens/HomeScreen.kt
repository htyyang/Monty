package com.hyang57.monty.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyang57.monty.R
import com.hyang57.monty.ui.NavDestination

object HomeDestination : NavDestination {
    override val route = "HOME"
    override val titleId = R.string.home
}
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navToGameScreen: () -> Unit = {},
    navToSettingScreen: () -> Unit = {},
    navToStatsScreen: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(48.dp)
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(16.dp)
                .weight(5f)
        ) {
            Image(
                painterResource(
                    id = R.drawable.ace_of_spades
                ),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 42.sp
                ),
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = navToGameScreen
            ) {
                Text(stringResource(id = R.string.play))
            }
            Button(
                onClick = navToSettingScreen
            ) {
                Text(stringResource(id = R.string.setting))
            }
            Button(
                onClick = navToStatsScreen
            ) {
                Text(stringResource(id = R.string.stats))
            }
        }
    }
}