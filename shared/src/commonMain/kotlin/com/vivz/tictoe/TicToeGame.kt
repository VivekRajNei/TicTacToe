package com.vivz.tictoe

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData

//interface GameState {
//    fun getCurrentPlayer(): Int
//    fun getBoard(): Array<IntArray>
//    fun handleAction(action: GameAction)
//}
//
//sealed class GameAction {
//    data class MakeMove(val row: Int, val col: Int) : GameAction()
//    object RestartGame : GameAction()
//}

data class TicTacToeState(
    val board: Array<Array<Int>> = Array(3) { Array(3) { 0 } },
    val currentPlayer: Int = 1,
    val gameState: GameState = GameState.Player1Turn
)

//val ticTacToeReducer: Reducer<TicTacToeState, GameAction> = { state, action ->
//    when (action) {
//        is GameAction.MakeMove -> {
//            if (state.board[action.row][action.col] == 0) {
//                state.board[action.row][action.col] = state.currentPlayer
//                if (checkWin(state.currentPlayer, state.board)) {
//                    state.gameState = GameState.GameOver
//                } else {
//                    state.currentPlayer = if (state.currentPlayer == 1) 2 else 1
//                    state.gameState =
//                        if (state.currentPlayer == 1) GameState.Player1Turn else GameState.Player2Turn
//                }
//            }
//            state
//        }
//        is GameAction.RestartGame -> {
//            TicTacToeState()
//        }
//        else -> {
//
//        }
//    }
//}

sealed class GameState {
    object Player1Turn : GameState()
    object Player2Turn : GameState()
    object InvalidMove : GameState()
    data class GameOver(val winner: Int) : GameState()
}

// Define the actions
sealed class GameAction {
    data class MakeMove(val row: Int, val col: Int) : GameAction()
    object RestartGame : GameAction()
}

// Create a State Machine class
class TicTacToeGameEngine {
    val board = Array(3) { Array(3) { 0 } }
    var currentPlayer = 1
        private set
    var currentState: GameState = GameState.Player1Turn
        private set

    fun handleAction(action: GameAction) {
        when (currentState) {
            is GameState.Player1Turn -> handlePlayer1TurnAction(action)
            is GameState.Player2Turn -> handlePlayer2TurnAction(action)
            is GameState.InvalidMove -> handlePlayer2TurnAction(action)
            is GameState.GameOver -> handleGameOverAction(action)
        }
    }

    private fun handlePlayer1TurnAction(action: GameAction) {
        when (action) {
            is GameAction.MakeMove -> {
                if (board[action.row][action.col] == 0) {
                    board[action.row][action.col] = 1
                    if (checkWin(1)) {
                        currentState = GameState.GameOver(winner = 1)
                    } else {
                        currentPlayer = 2
                        currentState = GameState.Player2Turn
                    }
                }
            }
            is GameAction.RestartGame -> {
                resetBoard()
                currentPlayer = 1
                currentState = GameState.Player1Turn
            }
        }
    }

    private fun handlePlayer2TurnAction(action: GameAction) {
        when (action) {
            is GameAction.MakeMove -> {
                if (board[action.row][action.col] == 0) {
                    board[action.row][action.col] = 2
                    if (checkWin(2)) {
                        currentState = GameState.GameOver(winner = 2)
                    } else {
                        currentPlayer = 1
                        currentState = GameState.Player1Turn
                    }
                }
            }
            is GameAction.RestartGame -> {
                resetBoard()
                currentPlayer = 1
                currentState = GameState.Player1Turn
            }
        }
    }

    private fun handleGameOverAction(action: GameAction) {
        when (action) {
            is GameAction.RestartGame -> {
                resetBoard()
                currentPlayer = 1
                currentState = GameState.Player1Turn
            }
            is GameAction.MakeMove -> {
                throw IllegalStateException("Cannot move after game is over")
            }
        }
    }

    private fun checkWin(player: Int): Boolean {
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

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = 0
            }
        }
    }
}