package com.vivz.tictoe.android

import androidx.lifecycle.ViewModel
import com.vivz.tictoe.GameAction
import com.vivz.tictoe.GameState
import com.vivz.tictoe.TicTacToeGameEngine

class TicTacToeViewModel : ViewModel() {
    private val ticTacToeGameEngine = TicTacToeGameEngine()
    val gameState: GameState = ticTacToeGameEngine.currentState

    fun handleAction(action: GameAction) {
        ticTacToeGameEngine.handleAction(action)
    }
}