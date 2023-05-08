package com.vivz.tictoe

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TicToeGameTest {

    @Test
    fun testBoardPreset() {
        val gameEngine = TicTacToeGameEngine()
        assertTrue(gameEngine.board.flatten().all { it == 0 }, "Board preset is not set to zero")
    }

    @Test
    fun testFirstPlayerTurn() {
        val gameEngine = TicTacToeGameEngine()
        assertEquals(gameEngine.currentState, GameState.Player1Turn, "First player is not Player1")
    }

    @Test
    fun testSecondPlayerTurn() {
        val gameEngine = TicTacToeGameEngine()
        gameEngine.handleAction(GameAction.MakeMove(0, 0))
        assertEquals(gameEngine.currentState, GameState.Player2Turn, "Second player is not Player2")
    }

    @Test
    fun testCanPlayerOverrideExistingValue() {
        val gameEngine = TicTacToeGameEngine()
        gameEngine.handleAction(GameAction.MakeMove(0, 0)) // Player 1
        gameEngine.handleAction(GameAction.MakeMove(0, 0)) // Player 2
        assertEquals(gameEngine.currentState, GameState.GameOver(2), "Player2 should be win")
    }

    @Test
    fun testGamePlayWonByPlayer1() {
        val gameEngine = TicTacToeGameEngine()
        gameEngine.handleAction(GameAction.MakeMove(0, 0)) // Player 1
        gameEngine.handleAction(GameAction.MakeMove(0, 1)) // Player 2
        gameEngine.handleAction(GameAction.MakeMove(1, 1)) // Player 1
        gameEngine.handleAction(GameAction.MakeMove(1, 2)) // Player 2
        gameEngine.handleAction(GameAction.MakeMove(2, 2)) // Player 1
        assertEquals(gameEngine.currentState, GameState.GameOver(1), "Player1 should be win")
    }

    @Test
    fun testGamePlayWonByPlayer2() {
        val gameEngine = TicTacToeGameEngine()
        gameEngine.handleAction(GameAction.MakeMove(0, 1)) // Player 1
        gameEngine.handleAction(GameAction.MakeMove(0, 0)) // Player 2
        gameEngine.handleAction(GameAction.MakeMove(1, 2)) // Player 1
        gameEngine.handleAction(GameAction.MakeMove(1, 1)) // Player 2
        gameEngine.handleAction(GameAction.MakeMove(1, 0)) // Player 2
        gameEngine.handleAction(GameAction.MakeMove(2, 2)) // Player 2
        assertEquals(gameEngine.currentState, GameState.GameOver(2), "Player2 should be win")
    }
}