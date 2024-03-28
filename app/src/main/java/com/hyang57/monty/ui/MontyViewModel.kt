package com.hyang57.monty.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.hyang57.monty.game.Card
import com.hyang57.monty.game.Monty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MontyViewModel(app: Application) : AndroidViewModel(app){
    private var monty: Monty
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState
    init{
        monty = Monty(3)
    }

    fun newRound(){
        monty.newRound()
        _gameState.value = _gameState.value.copy(
            amount = monty.amount,
            roundNum = monty.roundNum,
            roundEnd = monty.roundEnd,
            winNum = monty.winNum,
            winID = monty.winID,
            cards = monty.cards
        )
    }

    fun selectCard(selection: Int) {
        val cards = _gameState.value.cards.toMutableList() // Create a mutable copy
        val card = cards[selection]
        cards[selection] = card.copy(isFlipped = !card.isFlipped) // Toggle `isFlipped`
        // Update the gameState with the new cards list, ensuring Compose observes this change
        _gameState.value = _gameState.value.copy(cards = cards)
        monty.cards = cards
        if(!monty.roundEnd){
            if(selection == monty.winID)
            {
                monty.winNum += 1
            }
            monty.roundEnd = true
        }
        Log.i("winnum","${monty.winNum}")
        Log.i("_gameState: selected card isFlipped: ","${_gameState.value.cards[selection].isFlipped}")
    }

}

data class GameState(
    val amount: Int = 0,
    val roundNum: Int = 0,
    val roundEnd: Boolean = false,
    val winNum: Int = 0,
    val winID: Int = -1,
    val cards: List<Card> = emptyList(),
)