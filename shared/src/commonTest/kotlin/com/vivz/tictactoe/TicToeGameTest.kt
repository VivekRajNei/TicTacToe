package com.vivz.tictactoe

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TicToeGameTest {

    @Test
    fun testBoardPreset() {
        val gameEngine = TicTacToeGameEngine()
        assertTrue(gameEngine.board.flatten().all { it == 0 }, "Board preset is not set to zero")
    }

    @Test
    fun testFirstPlayerTurn() = runTest {
        val stateMachine = GameStateMachine(TicTacToeGameEngine())
        stateMachine.state.test {
            assertEquals(PlayerTurn(Player.ONE), awaitItem(), "First player is not Player1")
        }
    }

    @Test
    fun testSecondPlayerTurn() = runTest {
        val stateMachine = GameStateMachine(TicTacToeGameEngine())

        stateMachine.state.test {
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(0, 0))
            assertEquals(PlayerTurn(Player.TWO), awaitItem(), "Second player is not Player2")
        }
    }

    @Test
    fun testGamePlayWonByPlayer1() = runTest {
        val stateMachine = GameStateMachine(TicTacToeGameEngine())

        /**
         * Game board
         *
         * +---+---+---+
         * | 1 | 2 |   |
         * +---+---+---+
         * |   | 1 | 2 |
         * +---+---+---+
         * |   |   | 1 |
         * +---+---+---+
         */

        stateMachine.state.test {
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(0, 0)) // Player 1
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(MakeMove(0, 1)) // Player 2
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(1, 1)) // Player 1
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(MakeMove(1, 2)) // Player 2
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(2, 2)) // Player 1

            assertEquals(GameOver(Player.ONE), awaitItem(), "Player.ONE should win")
        }
    }

    @Test
    fun testGamePlayWonByPlayer2() = runTest {
        val stateMachine = GameStateMachine(TicTacToeGameEngine())

        /**
         * Game board
         *
         * +---+---+---+
         * | 2 | 1 |   |
         * +---+---+---+
         * | 1 | 2 | 1 |
         * +---+---+---+
         * |   |   | 2 |
         * +---+---+---+
         */

        stateMachine.state.test {
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(0, 1)) // Player 1
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(MakeMove(0, 0)) // Player 2
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(1, 2)) // Player 1
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(MakeMove(1, 1)) // Player 2
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(1, 0)) // Player 1
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(MakeMove(2, 2)) // Player 2

            assertEquals(GameOver(Player.TWO), awaitItem(), "Player.TWO should win")
        }
    }

    @Test
    fun testRestartGameAndCheckGameBoard() = runTest {
        val gameEngine = TicTacToeGameEngine()
        val stateMachine = GameStateMachine(gameEngine)

        stateMachine.state.test {
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            stateMachine.dispatch(MakeMove(0, 0))
            assertEquals(PlayerTurn(Player.TWO), awaitItem())
            stateMachine.dispatch(RestartGame)
            assertEquals(PlayerTurn(Player.ONE), awaitItem())
            assertTrue(gameEngine.board.flatten().all { it == 0 }, "Board is not reset after Game Restart")
        }
    }
}