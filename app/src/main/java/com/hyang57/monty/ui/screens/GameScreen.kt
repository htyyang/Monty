package com.hyang57.monty.ui.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hyang57.monty.R
import com.hyang57.monty.game.Card
import com.hyang57.monty.ui.NavDestination
import com.hyang57.monty.ui.GameState


object GameDestination : NavDestination {
    override val route = "GAME"
    override val titleId = R.string.game
}

// Screen for the game
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameState: GameState,
    onSelect: (Card) -> Unit = {},
    onHome: (Int) -> Unit = {},
    onStats: (Int) -> Unit = {},
    onNewGame: (Int) -> Unit = {},
    onHint: (Boolean) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
            Text(
                text = gameState.resultNotification,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge
            )
        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                items(gameState.cards) { card ->
                    GameCard(card = card, onCardClick = onSelect, roundEnd = gameState.roundEnd)
                }
            }
        }

        // Buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Button(onClick = { onNewGame(1) }) {
                Text(stringResource(id = R.string.new_game))
            }
            Button(onClick = { onHint(true) }) {
                Text(stringResource(id = R.string.hint))
            }
            // Home button
            Button(onClick = { onHome(1) }) {
                Text(stringResource(id = R.string.home))
            }
            // Stats button
            Button(onClick = { onStats(1) }) {
                Text(stringResource(id = R.string.stats))
            }
        }
    }
}

// Card display
@Composable
fun GameCard(
    card: Card,
    onCardClick: (Card) -> Unit,
    roundEnd: Boolean = false
) {
    Log.i("GameCard:","ID: ${card.id}, isAce: ${card.isAce}, isFlipped: ${card.isFlipped}.")

    // Animation for flipping the card
    val rotation = animateFloatAsState(

        targetValue = if (card.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    ).value

    val scale = animateFloatAsState(
        targetValue = if (rotation > 90f && rotation < 270f) 0.95f else 1f,
        animationSpec = tween(durationMillis = 300)
    ).value

    val alpha = animateFloatAsState(
        targetValue = if (rotation > 90f && rotation < 270f) 0.7f else 1f,
        animationSpec = tween(durationMillis = 300)
    ).value

    Box(
        modifier = Modifier
            .graphicsLayer {

                // Rotation
                rotationY = rotation
                if (rotation > 90f) {
                    // Mirror it back
                    rotationY = rotation - 180f
                }

                // Scale
                scaleX = scale
                scaleY = scale

                // Alpha
                this.alpha = alpha

                // Camera distance
                cameraDistance = 8 * density
            }
            .clickable { onCardClick(card) }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Determine what to show
        val imageResource = when {
            !(card.isFlipped) -> R.drawable.card_back
            else ->card.value
        }
        Log.i("imageResource","${imageResource}")

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Card"
        )
    }
}