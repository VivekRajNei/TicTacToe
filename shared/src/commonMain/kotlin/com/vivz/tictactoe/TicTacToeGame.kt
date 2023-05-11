package com.vivz.tictactoe

import com.freeletics.flowredux.dsl.ChangedState
import com.freeletics.flowredux.dsl.State

// Create a State Machine class
class GameReducer {
    internal fun handlePlayerTurnAction(
        action: GameAction,
        state: State<PlayerTurn>
    ): ChangedState<GameState> {
        val board = state.snapshot.board
        val currentPlayer = state.snapshot.player
        return when (action) {
            is MakeMove -> {
                if (board[action.row][action.col] == 0) {
                    board[action.row][action.col] = currentPlayer.id
                    if (checkWin(state)) {
                        state.override {
                            GameOver(currentPlayer)
                        }
                    } else {
                        state.override {
                            PlayerTurn(currentPlayer.switchPlayer())
                        }
                    }
                } else {
                    // TODO add logic if trying to update existing value
                    state.noChange()
                }
            }
            is RestartGame -> {
                state.snapshot.resetBoard()
                state.override {
                    PlayerTurn(Player.ONE)
                }
            }
        }
    }

    internal fun handleGameOverAction(
        action: GameAction,
        state: State<GameOver>
    ): ChangedState<GameState> {
        return when (action) {
            is RestartGame -> {
                state.snapshot.resetBoard()
                state.override {
                    PlayerTurn(Player.ONE)
                }
            }
            is MakeMove -> {
                throw IllegalStateException("Cannot move after game is over")
            }
        }
    }

    private fun checkWin(playerTurn: State<PlayerTurn>): Boolean {
        val currentPlayer = playerTurn.snapshot
        val player = currentPlayer.player.id
        val board = currentPlayer.board

        // Check rows
        for (i in 0..2) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true
            }
        }

        // Check columns
        for (j in 0..2) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true
        }

        return false
    }
}