package com.hyang57.monty.ui.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hyang57.monty.R
import com.hyang57.monty.game.Card
import com.hyang57.monty.ui.NavDestination
import com.hyang57.monty.ui.GameState
import kotlin.math.round

object GameDestination : NavDestination {
    override val route = "GAME"
    override val titleId = R.string.game
}
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameState: GameState,
    onSelect: (Card) -> Unit = {},
    onHome: (Int) -> Unit = {},
    onStats: (Int) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Adjust based on your UI design
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(gameState.cards) { card ->
            GameCard(card = card, onCardClick = onSelect, roundEnd = gameState.roundEnd)
        }
        Log.i("GameCard","Construct")
    }

    Row(
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                onHome(1)
            }) {
            Text(stringResource(id = R.string.home))
        }
    }
    Row(
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            onClick = {
                onStats(1)
            }) {
            Text(stringResource(id = R.string.stats))
        }
    }
}

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

    Box(
        modifier = Modifier
            .graphicsLayer {
                // This will make the card flip around the Y axis
                rotationY = rotation

                if (rotation > 90f) {
                    // When the card is more than halfway flipped, we need to "mirror" it back
                    // so the front side is oriented correctly
                    rotationY = rotation - 180f
                }
                // This will change the camera distance to make the flip look more realistic
                cameraDistance = 8 * density
            }
            .clickable { onCardClick(card) }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Determine which image to show based on the card state
        val imageResource = when {
            !(card.isFlipped) -> R.drawable.card_back
            else ->card.value
            //else -> listOf(R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4).random()
        }
        Log.i("imageResource","${imageResource}")

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Card"
        )
    }
}