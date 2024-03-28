package com.hyang57.monty.game

import android.util.Log

import com.hyang57.monty.R
import kotlin.math.round
import kotlin.random.Random

class Monty(cardAmount: Int){
    var amount: Int = 0
    var cards: MutableList<Card> = mutableListOf()
    var roundNum: Int = 0
    var winNum: Int = 0
    var winID: Int = -1
    var roundEnd: Boolean = false
    init {
        amount = cardAmount
    }

    fun newRound(){
        roundNum += 1
        var tempList: MutableList<Card> = mutableListOf()
        for (i in 0..<amount) {
           tempList.add(Card(id = i, isAce = false, value = listOf(R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4).random()))
        }

        // Randomly select one card to be an Ace
        val randomAce = Random.nextInt(0, amount) // Generates a random index within the range of 0 until A
        tempList[randomAce] = Card(id = tempList[randomAce].id, isAce = true, value = R.drawable.ace_of_spades)


        // Randomly choose one position to set to true
        winID = randomAce

        // Assign the temporary list to the cards property
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

data class Card(
    val id: Int, // Unique identifier for each card
    val isAce: Boolean, // True if the card is an Ace
    var isFlipped: Boolean = false, // Track if the card is flipped to show its face
    val value: Int,
)