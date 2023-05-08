package com.vivz.tictactoe

import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.State

enum class Player(internal val id: Int = 1) {
    ONE(1),
    TWO(2);

    internal fun switchPlayer() = when(this) {
        ONE -> TWO
        TWO -> ONE
    }
}

// State definitions
sealed interface GameState
data class PlayerTurn(val player: Player) : GameState
data class GameOver(val winner: Player) : GameState

// Action definitions
sealed interface GameAction
data class MakeMove(internal val row: Int, internal val col: Int) : GameAction
object RestartGame : GameAction


internal class GameStateMachine(
    private val gameEngine: TicTacToeGameEngine
) : FlowReduxStateMachine<GameState, GameAction>(initialState = PlayerTurn(Player.ONE)) {

    init {
        spec {
            inState<PlayerTurn> {
                on<MakeMove> { action: MakeMove, state: State<PlayerTurn> ->
                    gameEngine.handlePlayerTurnAction(action, state)
                }

                on<RestartGame> { action: RestartGame, state: State<PlayerTurn> ->
                    gameEngine.handlePlayerTurnAction(action, state)
                }
            }

            inState<GameOver> {
                on<RestartGame> { action: RestartGame, state: State<GameOver> ->
                    gameEngine.handleGameOverAction(action, state)
                }
            }
        }
    }
}