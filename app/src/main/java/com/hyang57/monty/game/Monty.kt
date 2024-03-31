package com.hyang57.monty.game

import android.util.Log
import com.hyang57.monty.R
import kotlin.random.Random

// Class represents the whole game
class Monty(cardAmount: Int){
    // How many cards
    var amount: Int = 0
    // Card list
    var cards: MutableList<Card> = mutableListOf()
    // How many rounds
    var roundNum: Int = 0
    // Win how many times
    var winNum: Int = 0
    // Which card is ace
    var winID: Int = -1
    // Whether the round is end or not
    var roundEnd: Boolean = false
    // notification
    var resultNotification: String = ""
    init {
        amount = cardAmount
    }

    // Start a new round
    fun newRound(){
        resultNotification = ""
        roundEnd = false
        roundNum += 1
        var tempList: MutableList<Card> = mutableListOf()
        val cardImages = listOf(R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5, R.drawable.card6, R.drawable.card7).shuffled()

        for (i in 0..<amount) {
            tempList.add(Card(id = i, isAce = false, value = cardImages[i]))
        }
        // Randomly select one card to be an Ace
        val randomAce = Random.nextInt(0, amount)
        tempList[randomAce] = Card(id = tempList[randomAce].id, isAce = true, value = R.drawable.ace_of_spades)

        winID = randomAce
        cards = tempList
    }

    fun changeAmount(newAmount: Int){
        amount = newAmount
        newRound()
    }

    fun selectCard(selection: Int){
        cards[selection].isFlipped = !(cards[selection].isFlipped)
        //cards[selection].isFlipped = true
        if(selection == winID){
            winNum += 1
        }
        roundEnd = true
        Log.i("Monty.selectCard", "selection: $selection")
    }
}

// Class represents a card
data class Card(
    val id: Int, // Unique identifier for each card
    val isAce: Boolean, // True if the card is an Ace
    var isFlipped: Boolean = false, // Track if the card is flipped to show its face
    val value: Int,
)