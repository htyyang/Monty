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
        // Set cards amount to 3 initially
        monty = Monty(3)
    }

    // Start a new round
    fun newRound(){
        monty.newRound()
        _gameState.value = _gameState.value.copy(
            amount = monty.amount,
            roundNum = monty.roundNum,
            roundEnd = monty.roundEnd,
            winNum = monty.winNum,
            winID = monty.winID,
            cards = monty.cards,
            resultNotification = monty.resultNotification
        )
    }

    // Select a card in the game
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
                monty.resultNotification = "You Found Ace!"
            }
            else{
                monty.resultNotification = "You Failed."
            }
            monty.roundEnd = true
        }
        _gameState.value = _gameState.value.copy(
            winNum = monty.winNum,
            roundEnd = monty.roundEnd,
            resultNotification = monty.resultNotification,
            )
        Log.i("winnum","${monty.winNum}")
        Log.i("_gameState: selected card isFlipped: ","${_gameState.value.cards[selection].isFlipped}")
    }

    // Provide hint
    fun hintCard() {
        val cards = _gameState.value.cards.toMutableList() // Create a mutable copy
        val card: Card
        // Check if a hint can be provided
        if (cards.any { !it.isFlipped && !it.isAce } && cards.filter { it.isAce }.all { !it.isFlipped } && !monty.roundEnd){
            val nonFlippedNonAceCards = cards.filter { !it.isFlipped && !it.isAce }
            card = nonFlippedNonAceCards.random()
            cards[card.id] = card.copy(isFlipped = !card.isFlipped)
            _gameState.value = _gameState.value.copy(cards = cards)
            monty.cards = cards
            monty.resultNotification = "Hint provided."
            _gameState.value = _gameState.value.copy(
                resultNotification = monty.resultNotification,
            )
        }
    }

    fun clearStats(){
        monty.winNum = 0
        monty.roundNum = 0
        _gameState.value = _gameState.value.copy(
            winNum = monty.winNum,
            roundNum = monty.roundNum,
        )
    }
    fun roundNotFinished(){
        monty.roundNum -= 1
        _gameState.value = _gameState.value.copy(roundNum = monty.roundNum)
    }
    fun changeAmount(newAmount: Int){
        monty.amount = newAmount
        _gameState.value = _gameState.value.copy(amount = monty.amount)
    }

    fun enableHint(enable: Boolean){
        _gameState.value = _gameState.value.copy(enableHint = enable)
    }

}

data class GameState(
    val amount: Int = 0,
    val roundNum: Int = 0,
    val roundEnd: Boolean = false,
    val winNum: Int = 0,
    val winID: Int = -1,
    val cards: List<Card> = emptyList(),
    val resultNotification: String = "",
    val enableHint: Boolean = false,
)