package com.vivz.tictoe.android

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivz.tictoe.GameAction
import com.vivz.tictoe.GameState

//@Composable
//fun TicTacToeScreen(viewModel: TicTacToeViewModel) {
//    val gameState by viewModel.gameState.observeAsState(GameState.Player1Turn)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Tic Tac Toe") },
//                backgroundColor = MaterialTheme.colors.primary
//            )
//        },
//        content = {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                when (gameState) {
//                    is GameState.Player1Turn -> {
//                        Text("Player 1's turn")
//                        TicTacToeBoard(viewModel)
//                    }
//                    is GameState.Player2Turn -> {
//                        Text("Player 2's turn")
//                        TicTacToeBoard(viewModel)
//                    }
//                    is GameState.GameOver -> {
//                        Text("Game over")
//                        RestartButton(viewModel)
//                    }
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun TicTacToeBoard(viewModel: TicTacToeViewModel) {
////    val board = viewModel.
//
//    Column {
//        for (i in 0..2) {
//            Row {
//                for (j in 0..2) {
//                    Box(
//                        modifier = Modifier
//                            .size(64.dp)
//                            .padding(4.dp)
//                            .background(Color.White)
//                            .clickable {
//                                viewModel.handleAction(GameAction.MakeMove(i, j))
//                            }
//                    ) {
//                        when (board[i][j]) {
//                            1 -> {
//                                Text(
//                                    text = "X",
//                                    fontSize = 48.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black,
//                                    modifier = Modifier.align(Alignment.Center)
//                                )
//                            }
//                            2 -> {
//                                Text(
//                                    text = "O",
//                                    fontSize = 48.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black,
//                                    modifier = Modifier.align(Alignment.Center)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun RestartButton(viewModel: TicTacToeViewModel) {
    Button(
        onClick = { viewModel.handleAction(GameAction.RestartGame) },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Restart Game")
    }
}