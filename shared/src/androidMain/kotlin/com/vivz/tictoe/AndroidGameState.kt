package com.vivz.tictoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//actual class AndroidGameState : GameState {
//    private val ticTacToeFSM = TicTacToeFSM()
//    private val _currentState = MutableLiveData<GameState>()
//    actual val currentState: LiveData<GameState> = _currentState
//
//    init {
//        _currentState.value = AndroidGameStateWrapper(ticTacToeFSM.currentState.value!!)
//        ticTacToeFSM.currentState.observeForever { state ->
//            _currentState.postValue(AndroidGameStateWrapper(state))
//        }
//    }
//
//    override fun getCurrentPlayer(): Int {
//        return ticTacToeFSM.getCurrentPlayer()
//    }
//
//    override fun getBoard(): Array<IntArray> {
//        return ticTacToeFSM.getBoard()
//    }
//
//    override fun handleAction(action: GameAction) {
//        ticTacToeFSM.handleAction(action)
//    }
//}
//
//private class AndroidGameStateWrapper(private val state: GameState) : GameState {
//    override fun getCurrentPlayer(): Int {
//        return state.getCurrentPlayer()
//    }
//
//    override fun getBoard(): Array<IntArray> {
//        return state.getBoard()
//    }
//
//    override fun handleAction(action: GameAction) {
//        state.handleAction(action)
//    }
//}